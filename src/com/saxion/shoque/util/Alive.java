package com.saxion.shoque.util;

import android.util.Log;

import com.saxion.shoque.playground.model.GameBoard;
import com.saxion.shoque.playground.model.GameObject;

public class Alive extends GameObject {
	private static final String TAG = "GameObject";
	public static final String ALIVE_IMAGE = "color_alive";
	public static final String ALIVE_ANIMATION = "alive_anim";
	
	public Alive(){
		super();
	}
	
	@Override
	public String getImageId() {
		return ALIVE_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		//NOTHING SHOULD HAPPEN
		Log.d(gameBoard.TAG, "Touched alive");
	}

}
