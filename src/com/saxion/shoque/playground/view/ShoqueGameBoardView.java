package com.saxion.shoque.playground.view;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.example.shoque.R;
import com.saxion.shoque.playground.model.GameObject;

public class ShoqueGameBoardView extends GameBoardView {
	private static final String TAG = "GameBoardView";
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
		Log.d(TAG, "Loading all images");

		// Add a player object
		loadTile(ALIVE_IMAGE, R.drawable.color_alive);
		loadTile("alive", R.drawable.color_alive);
		loadTile("hit", R.drawable.color_hit);
		loadTile("missed", R.drawable.color_missed);
	}	
}