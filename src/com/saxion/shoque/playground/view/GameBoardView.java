package com.saxion.shoque.playground.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import com.saxion.shoque.playground.model.GameBoard;
import com.saxion.shoque.playground.model.GameObject;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * View that draws a grid of images, representing the game board. This class
 * handles the resizing of the grid. Two possible options exist: a) a fixed
 * grid, where the number of tiles remains the same, but tiles may become
 * smaller or larger. b) a relative grid, where the tile size remains the same,
 * but the number of tiles drawn may change.
 * 
 * This class also handles the resizing of the bitmaps, so the image quality
 * remains good.
 * 
 * @author Jan Stroet
 * @author Paul de Groot
 */
public abstract class GameBoardView extends View implements Observer {
	private static final String TAG = "GameBoardView";

	/** When true, the number of tiles in the view remains the same. */
	private boolean fixedGrid = true;

	/** Number of tiles in X-direction. */
	private int tileCountX = 10;

	/** Number of tiles in Y-direction. */
	private int tileCountY = 10;

	/** Size (in pixels) of the tiles. */
	private int mTileSize = 20;

	/**
	 * There is a border around the tile grid. This is the border size in pixels
	 * in the X-direction.
	 */
	protected int borderSizeX;

	/**
	 * There is a border around the tile grid. This is the border size in pixels
	 * in the Y-direction.
	 */
	protected int borderSizeY;

	/** The image ID to use for empty tiles (no game object on them). */
	private String emptyTile;

	/** A cache of all images that can be used. */
	private HashMap<String, TileImage> images = new HashMap<String, TileImage>();

	/** The game board to draw. */
	private GameBoard board;

	/** A two-dimensional array that stores the bitmaps to draw. */
	private TileImage[][] mTileGrid;

	/** Object that can paint this view. */
	private final Paint mPaint = new Paint();

	/**
	 * A single image that can be used in the representation of a game object.
	 * This is not the actual image per se: a bitmap of the exact size of the
	 * tiles is created.
	 * 
	 * @author Paul de Groot
	 */
	private class TileImage {
		/** The resourceID of the source image, e.g. R.drawable.wombat */
		int resourceID;
		/** The bitmap created from the original image. */
		Bitmap bitmap;

		/**
		 * Creates a structure that store the resourceID and a bitmap of the
		 * size of a tile in the view.
		 * 
		 * @param resourceID
		 *            The resource ID, e.g. R.drawable.wombat.
		 * @param tileSize
		 *            The size of a tile in pixels.
		 * 
		 * @throws Resources.NotFoundException
		 *             when the resource was not found.
		 */
		public TileImage(int resourceID, int tileSize) {
			this.resourceID = resourceID;
			createBitmap(tileSize);
		}

		/**
		 * Loads the image identified by the given resourceID and creates a
		 * bitmap the size of a tile from that.
		 * 
		 * @param tileSize
		 *            The tile size.
		 */
		public void createBitmap(int tileSize) {
			// Find the resource specified
			Resources resources = GameBoardView.this.getContext()
					.getResources();
			Drawable drawable = resources.getDrawable(resourceID);

			// Create a bitmap of the exact tile size and draw the drawable on
			// there
			bitmap = Bitmap.createBitmap(tileSize, tileSize,
					Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, tileSize, tileSize);
			drawable.draw(canvas);
		}
	}

	/**
	 * Constructor.
	 */
	public GameBoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFocusable(true);
	}

	/**
	 * Constructor.
	 */
	public GameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);
	}

	/**
	 * Sets this view to 'fixed grid mode'. This means that the number of tiles
	 * in the grid will always be the same, but the tile size is changed to
	 * match the size of the view.
	 * 
	 * @param tilesX
	 *            The number of tiles in X-direction.
	 * @param tilesY
	 *            The number of tiles in Y-direction.
	 */
	public void setFixedGridSize(int tilesX, int tilesY) {
		fixedGrid = true;
		tileCountX = tilesX;
		tileCountY = tilesY;

		recalculateGrid();
	}

	/**
	 * Sets this view to 'variable grid mode'. This means that the grid size
	 * will always be the same, and the number of tiles in view will be changed
	 * according to the size of the view.
	 * 
	 * @param tileSize
	 *            The size in pixels of a tile.
	 */
	public void setVariableGridSize(int tileSize) {
		fixedGrid = false;
		mTileSize = tileSize;

		recalculateGrid();
	}

	/**
	 * Sets a reference to the game board that will be drawn by this view.
	 * Changes to this board will be noticed by calls to notifyObserver.
	 * 
	 * @param board
	 *            The game board.
	 */
	public void setGameBoard(GameBoard board) {
		this.board = board;
		determineGridBitmaps();
		board.addObserver(this);
	}

	/**
	 * Use this method to 'load' the images in the application. Loading means
	 * that a bitmap is created that contains the image scaled to the size of a
	 * tile. Also, the given 'key' is associated with that image. This means
	 * that if a game object tells this view (by way of getImageId()) to use a
	 * certain image, the correct bitmap can be found.
	 * 
	 * @param key
	 *            The string that will be associated with this image
	 * @param resourceID
	 *            The resourceID of the drawable to load, e.g.
	 *            R.drawable.wombat.
	 * 
	 * @throws Resources.NotFoundException
	 *             if the resource could not be found.
	 */
	public void loadTile(String key, int resourceID) {
		// Store this in our hash map
		images.put(key, new TileImage(resourceID, mTileSize));
	}

	/**
	 * Set the image ID (as given to loadTile()) of the image that should be
	 * used to draw tiles on which no GameObject is present.
	 * 
	 * @param key
	 *            The image to use for empty tiles.
	 */
	public void setEmptyTile(String key) {
		emptyTile = key;
	}

	/**
	 * Handles the basic update: the visualization is updated according to
	 * objects on the game board.
	 */
	public void update(Observable o, Object arg) {
		determineGridBitmaps();
		invalidate();
	}

	/**
	 * Called when the user touches the game board. Determines which tile was
	 * clicked and which object was on that place of the board. If an object was
	 * present, GameObject.onTouched() will be called. If no object was there,
	 * GameBoard.onEmptyTileClicked() will be called.
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN: {
			int x = (int) ((event.getX() - borderSizeX) / mTileSize);
			int y = (int) ((event.getY() - borderSizeY) / mTileSize);
			if (x < tileCountX && y < tileCountY

			&& (event.getX() - borderSizeX) >= 0
					&& (event.getY() - borderSizeY) >= 0) {
				Log.d(TAG, "Touched (" + x + ", " + y + ")\n");

				// Who you gonna call?
				if (board != null) {
					if ((x < board.getWidth()) && (y < board.getHeight())) {

						// Determine the object clicked
						GameObject object = board.getObject(x, y);

						// Call the listener
						if (object != null) {
							object.onTouched(board);
						} else {
							board.onEmptyTileClicked(x, y);
						}
					}
				}
			}
			break;
		}
		}

		return super.onTouchEvent(event);
	}

	/** Called when the view size changed. */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// Recalculate the grid and tile sizes
		recalculateGrid();
	}

	/**
	 * Recalculates the size of the tiles and the number of tiles in view.
	 */
	private void recalculateGrid() {
		// Get size of view
		// If size is invalid, do nothing
		int w = getWidth();
		int h = getHeight();
		if (w == 0 || h == 0)
			return;

		// Determine tile size and number of tiles
		if (fixedGrid) {
			// Fixed grid: number of tiles stays the same, tile size calculated
			mTileSize = Math.min((int) Math.floor(w / tileCountX),
					(int) Math.floor(h / tileCountY));
			Log.d(TAG, "onSizeChanged: tile size is now " + mTileSize);
		} else {
			// Variable grid: tileSize is static, tile count changes
			tileCountX = (int) Math.floor(w / mTileSize);
			tileCountY = (int) Math.floor(h / mTileSize);
			Log.d(TAG, "onSizeChanged: tile count is now " + tileCountX + ", "
					+ tileCountY);
		}

		// Tiles may not use the whole view, leaving a border. Calculate the
		// size
		// of it. (The game board is centered within the view).
		borderSizeX = ((w - (mTileSize * tileCountX)) / 2);
		borderSizeY = ((h - (mTileSize * tileCountY)) / 2);
		Log.d(TAG, "onSizeChanged: border is now " + borderSizeX + ", "
				+ borderSizeY);

		// If the number of cells in the grid is fixed, cell size may have
		// changed.
		// Reload all the bitmaps
		if (fixedGrid) {
			for (Map.Entry<String, TileImage> i : images.entrySet()) {
				String imageName = i.getKey();
				TileImage image = i.getValue();

				Log.d(TAG, "onSizeChanged: reloading bitmap " + imageName);
				image.createBitmap(mTileSize);
			}
			invalidate();
		}

		// Update the array of the bitmaps, since that may have now changed
		determineGridBitmaps();
	}

	/**
	 * Update the array that contains which bitmap should be drawn where. This
	 * is done every time the game board changed.
	 */
	private void determineGridBitmaps() {
		mTileGrid = new TileImage[tileCountX][tileCountY];
		if (board == null)
			return;

		// For each tile...
		for (int x = 0; x < tileCountX; x++) {
			for (int y = 0; y < tileCountY; y++) {
				// ... get the game object
				GameObject object = null;
				if ((x < board.getWidth()) && (y < board.getHeight())) {
					object = board.getObject(x, y);
				}

				// Get the imageID
				// if no object present, use the emptyTile ID
				// otherwise, ask object for the imageID to use
				String imageID;
				if (object == null) {
					imageID = emptyTile;
				} else {
					imageID = object.getImageId();
				}

				// Find the associated bitmap
				TileImage img = null;
				if (imageID != null) {
					img = images.get(imageID);
				}
				mTileGrid[x][y] = img;
			}
		}
	}

	/**
	 * Called to draw the view.
	 */
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		for (int x = 0; x < tileCountX; x++) {
			for (int y = 0; y < tileCountY; y++) {
				if (mTileGrid[x][y] != null) {
					canvas.drawBitmap(mTileGrid[x][y].bitmap, borderSizeX + x
							* mTileSize, borderSizeY + y * mTileSize, mPaint);
				}
			}
		}
	}
}
