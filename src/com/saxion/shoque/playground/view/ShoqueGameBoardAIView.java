package com.saxion.shoque.playground.view;


import android.content.Context;
import android.util.AttributeSet;

import com.example.shoque.R;

public class ShoqueGameBoardAIView extends GameBoardView {

	public ShoqueGameBoardAIView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}
	
	public ShoqueGameBoardAIView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameView();
	}

	private void initGameView()
	{
		// Load the 'empty' cell bitmap and tell the tile view that this is the
		// image to use for cells without GameObject
		
		loadTile("empty", R.drawable.cell);
		setEmptyTile("empty");
		
		
		
		// Load the images for the GameObjects
		//loadTile(Leaf.LEAF_IMAGE, R.drawable.leaf);
		//loadTile(Rock.ROCK_IMAGE, R.drawable.rock);
		//loadTile(Rock.RED_ROCK_IMAGE, R.drawable.rock2);
		//loadTile(Wombat.WOMBAT_IMAGE, R.drawable.wombat);
	}
	
}
