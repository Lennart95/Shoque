package com.saxion.shoque.util;

import android.util.Log;

import com.saxion.shoque.playground.model.GameBoard;
import com.saxion.shoque.playground.model.GameObject;
import com.saxion.shoque.playground.model.SeashoqueBoard;
import com.saxion.shoque.playground.model.SeashoqueGame;

public class Alive extends GameObject {
	private SeashoqueGame game;
	public static final String ALIVE_IMAGE = "color_alive";
	public static final String ALIVE_ANIMATION = "alive_anim";
	
	public Alive(SeashoqueGame game){
		super();
		this.game = game;
	}
	
	@Override
	public String getImageId() {
		return ALIVE_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		game.shoot( (SeashoqueBoard) gameBoard, getPositionX(), getPositionY());
		Log.d(gameBoard.TAG, "Touched alive");
	}

}
