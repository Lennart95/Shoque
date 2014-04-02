package com.saxion.shoque.playground.model;

import android.app.Activity;

import com.saxion.shoque.util.Hit;
import com.saxion.shoque.SetUpActivity;

public class SeashoqueBoard extends GameBoard  {
	private static final int DIM = 10;
	private boolean setupState = false;
	private SetUpActivity parent;
	
	/**
	 * Instantiate 10X10 gameboard
	 */
	public SeashoqueBoard() {
		super(DIM, DIM);
	}
	
	@Override
	public void onEmptyTileClicked(int x, int y) {
		if (setupState){
			parent.saveBoatLocation(x, y);
		}
		else{
			((SeashoqueGame) getGame()).shoot(this, x, y);
		}
	}
	
	public int getDim(){
		return DIM;
	}
	


	public boolean isEmpty(int x, int y) {
		boolean result = true;
		if (getObject(x, y)!=null){
			result = false;
		}
		return result;
	}

	public boolean isHit(int x, int y) {
		return getObject(x,y) instanceof Hit;
	}
	
	public void setParent(SetUpActivity p){
		parent = p;
	}

	public void setSetupState(boolean setupState) {
		this.setupState = setupState;
	}
	
}
