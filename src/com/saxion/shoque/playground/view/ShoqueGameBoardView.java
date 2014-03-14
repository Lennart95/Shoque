package com.saxion.shoque.playground.view;


import android.content.Context;
import android.util.AttributeSet;

import com.example.shoque.R;
import com.saxion.shoque.playground.model.Game;
import com.saxion.shoque.playground.model.GameBoard;
import com.saxion.shoque.playground.model.GameObject;

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
//		// Add some rocks
//		board.addGameObject(new Rock(false), 3, 3);
//		board.addGameObject(new Rock(false), 2, 7);
//		board.addGameObject(new Rock(true),  8, 5);
//		board.addGameObject(new Rock(true),  1, 4);
//		board.addGameObject(new Rock(false), 6, 10);
//
//		// Add some leafs
//		board.addGameObject(new Leaf(), 7, 7);
//		board.addGameObject(new Leaf(), 9, 5);
//		board.addGameObject(new Leaf(), 3, 6);
//		board.addGameObject(new Leaf(), 4, 7);
//		board.addGameObject(new Leaf(), 1, 9);
//		
		// Load the images for the GameObjects
		//loadTile(Leaf.LEAF_IMAGE, R.drawable.leaf);
		//loadTile(Rock.ROCK_IMAGE, R.drawable.rock);
		//loadTile(Rock.RED_ROCK_IMAGE, R.drawable.rock2);
		//loadTile(Wombat.WOMBAT_IMAGE, R.drawable.wombat);
	}	
}