package com.saxion.shoque.util;

import com.saxion.shoque.playground.model.GameBoard;
import com.saxion.shoque.playground.model.GameObject;

public class Alive extends GameObject {

	@Override
	public String getImageId() {
		// TODO return bright orange: #ff3900
		return null;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		//NOTHING SHOULD HAPPEN
	}

}
