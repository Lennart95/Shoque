package com.saxion.shoque.util;

import android.util.Log;

import com.saxion.shoque.playground.model.GameBoard;
import com.saxion.shoque.playground.model.GameObject;

public class Missed extends GameObject {
	private static final String TAG = "GameObject";
	public static final String MISS_IMAGE = "color_missed";
	public static final String MISS_ANIMATION = "miss_anim";
	
	public Missed(){
		super();
	}
	
	@Override
	public String getImageId() {
		return MISS_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		//NOTHING SHOULD HAPPEN
		Log.d(TAG, "Touched Missed");
	}

}
