package com.saxion.shoque.playground.model;

import java.util.Observable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * The game board, which is a rectangular array of GameObject.
 * 
 * You should subclass this for your own game.
 * There you will (among other things) implement
 *    public void onEmptyTileClicked(int x, int y);
 * which will be called when the user clicked on a tile which had no game object
 * on it.
 * 
 * @author Paul de Groot
 */
public abstract class GameBoard extends Observable {
	public static final String TAG = "GameBoard";

	/** The game this game board is a part of. */
	private Game game;
	
	/** The game objects on the board. */
	private GameObject[][] gameBoard;

	/**
	 * Create a new game board.
	 * 
	 * @param width   The width of the board, in tiles.
	 * @param height  The height of the board, in tiles.
	 */
	public GameBoard(int width, int height) {
		this.gameBoard = new GameObject[width][height];
	}

	public GameBoard(ClassLoader in) {
		//TODO: read serializable string of alive objects
	}

	/**
	 * Returns the number of tiles in the X-direction of the board.
	 */
	public int getWidth() {
		return gameBoard.length;
	}

	/**
	 * Returns the number of tiles in the Y-direction of the board.
	 */
	public int getHeight() {
		return gameBoard[0].length;
	}

	/**
	 * Add a game object to the board.
	 * 
	 * @param obj  The object to place on the board.
	 * @param x    The X-coordinate to place the object at.
	 * @param y    The Y-coordinate to place the object at.
	 *
	 * @throws IllegalArgumentException if (x,y) is not empty
	 */
	public void addGameObject(GameObject obj, int x, int y) {
		if( gameBoard[x][y] != null ) {
			throw new IllegalArgumentException("Destination already contains an object");
		}
		
		gameBoard[x][y] = obj;
		obj.setPosition(x,  y);
		Log.d(TAG, "Added game Object: " + obj.toString()); //Custom code in framework
	}

	/**
	 * Move an object on the board.
	 * 
	 * @param obj   The object to move.
	 * @param newX  The new X-coordinate of the object.
	 * @param newY  The new Y-coordinate of the object.
	 *
	 * @throws IllegalArgumentException if (newX,newY) is not empty
	 */
	public void moveObject(GameObject obj, int newX, int newY) {
		int oldX = obj.getPositionX();
		int oldY = obj.getPositionY();

		gameBoard[oldX][oldY] = null;
		
		if( gameBoard[newX][newY] != null ) {
			throw new IllegalArgumentException("Destination already contains an object");
		}
		
		gameBoard[newX][newY] = obj;
		obj.setPosition(newX, newY);
	}

	/**
	 * Retrieves the object at the location (x, y) on the board.
	 * 
	 * @param x  The X-coordinate of the tile
	 * @param y  The Y-coordinate of the tile
	 * @return   The GameObject at (x, y) or null if there was none.
	 */
	public GameObject getObject(int x, int y) {
		return gameBoard[x][y];
	}

	/**
	 * Call this to notify the game board view that it should redraw.
	 * You should call this any time you are done changing things on the board
	 * and want to make your changes visible.
	 */
	public void updateView() {
		Log.d(TAG, "Updating game view");

		setChanged();
		notifyObservers();
	}

	/**
	 * Remove an object from the board.
	 * 
	 * @param object  The object to remove from the board.
	 */
	public void removeObject(GameObject object) {
		int x = object.getPositionX();
		int y = object.getPositionY();
		gameBoard[x][y] = null;
	}
	
	/**
	 * Remove all objects from the game board.
	 */
	public void removeAllObjects() {
		for( int x = 0; x < getWidth(); x++ ) {
			for( int y = 0; y < getHeight(); y++ ) {
				gameBoard[x][y] = null;
			}
		}
	}

	/**
	 * Get a reference to the game class. You can use this to access the game
	 * state.
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Called if the user clicked on a tile, and no object was present on that tile.
	 * 
	 * @param x  The x-coordinate of the clicked tile
	 * @param y  The y-coordinate of the clicked tile
	 */
	public abstract void onEmptyTileClicked(int x, int y);

	/** Used by Game. */
	public void setGame(Game game) {
		this.game = game;
	}

	
}
