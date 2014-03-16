package com.saxion.shoque.util;

import android.util.Log;

import com.saxion.shoque.playground.model.GameBoard;
import com.saxion.shoque.playground.model.GameObject;

public class Missed extends GameObject {
	public static final String MISS_IMAGE = "miss";
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
		Log.d(gameBoard.TAG, "Touched Missed");
	}

}
