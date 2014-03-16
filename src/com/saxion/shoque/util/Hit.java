package com.saxion.shoque.util;

import android.util.Log;

import com.saxion.shoque.playground.model.GameBoard;
import com.saxion.shoque.playground.model.GameObject;

public class Hit extends GameObject {
	public static final String HIT_IMAGE = "hit";
	public static final String HIT_ANIMATION = "hits_anim";
	
	public Hit(){
		super();
	}
	
	@Override
	public String getImageId() {
		return HIT_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		//NOTHING SHOULD HAPPEN
		Log.d(gameBoard.TAG, "Touched Hit");
	}

}
