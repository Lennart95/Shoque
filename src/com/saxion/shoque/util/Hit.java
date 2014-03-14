package com.saxion.shoque.util;

import com.saxion.shoque.playground.model.GameBoard;
import com.saxion.shoque.playground.model.GameObject;

public class Hit extends GameObject {

	@Override
	public String getImageId() {
		// TODO return bright orange: #9e0b0f
		return null;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		//NOTHING SHOULD HAPPEN
	}

}
