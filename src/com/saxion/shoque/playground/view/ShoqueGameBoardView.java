package com.saxion.shoque.playground.view;



import java.util.Observable;

import com.example.shoque.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;


public class ShoqueGameBoardView extends GameBoardView {
	private static final String TAG = "GameBoardView";

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

//		Want a cookie? Fuck you. Remove those comment-slahses and have a laugh.
//		loadTile("alive", R.drawable.color_alive);
//	 	setEmptyTile("alive");

		// Add a player object
		loadTile("color_alive", R.drawable.color_alive);
		loadTile("color_hit", R.drawable.color_hit);
		loadTile("color_missed", R.drawable.color_missed);
	}
}