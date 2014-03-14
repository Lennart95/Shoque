package com.saxion.shoque.util;

import com.saxion.shoque.playground.model.GameBoard;
import com.saxion.shoque.playground.model.GameObject;

public class Missed extends GameObject {

	@Override
	public String getImageId() {
		// TODO black image transparency: 80% 
		return null;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		//NOTHING SHOULD HAPPEN
	}

}
