package com.saxion.shoque.playground.view;


import android.content.Context;
import android.util.AttributeSet;

import com.example.shoque.R;
import com.saxion.shoque.playground.model.GameObject;
import com.saxion.shoque.util.Hit;
import com.saxion.shoque.util.Missed;

public class ShoqueGameBoardView extends GameBoardView {
	public static final String ALIVE_IMAGE = "Alive";
	private static final GameObject Alive = null;

	public ShoqueGameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}
	
	public ShoqueGameBoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameView();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    int size = 0;
	    int width = getMeasuredWidth();
	    int height = getMeasuredHeight();
	 
	    if (width > height) {
	        size = height;
	    } else {
	        size = width;
	    }
	    setMeasuredDimension(size, size);
	}
	
	private void initGameView()	{
		// Load the 'empty' cell bitmap and tell the tile view that this is the
		// image to use for cells without GameObject
		
		loadTile("empty", R.drawable.cell);
		setEmptyTile("empty");

	
// Dit moet er wss staan, dit stond ook in de voorbeelden van school, je kunt hier volgens mij de de 
// plaatjes laden en plaatsten (door zoals hier onder staat	board.addGameObject(Alive, 3, 3);)
		
//		
//		GameBoard board = 
//				
//				
//		board.removeAllObjects();
//
//		
//		// Add a player object
		loadTile(ALIVE_IMAGE, R.drawable.color_alive);
		
//
//		// Add some leafs
//		board.addGameObject(new Leaf(), 7, 7);
//		
		// Load the images for the GameObjects
		loadTile("alive", R.drawable.color_alive);
		loadTile("hit", R.drawable.color_hit);
		loadTile("missed", R.drawable.color_missed);
	}	
}