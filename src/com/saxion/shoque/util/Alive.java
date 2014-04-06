package com.saxion.shoque.util;

import android.util.Log;

import com.saxion.shoque.playground.model.GameBoard;
import com.saxion.shoque.playground.model.GameObject;
import com.saxion.shoque.playground.model.SeashoqueBoard;
import com.saxion.shoque.playground.model.SeashoqueGame;

public class Alive extends GameObject {
	private SeashoqueGame game = null;
	public static final String ALIVE_IMAGE = "color_alive";
	public static final String ALIVE_ANIMATION = "alive_anim";
	
	public Alive(SeashoqueGame game){
		super();
		this.game = game;
	}
	
	//Constructor below is just for using the setupboard.
	public Alive(){
		super();
	}
	
	@Override
	public String getImageId() {
		return ALIVE_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		if (game != null){
			game.shoot( (SeashoqueBoard) gameBoard, getPositionX(), getPositionY());
			Log.d(GameBoard.TAG, "Touched alive");
		}
	}

}
