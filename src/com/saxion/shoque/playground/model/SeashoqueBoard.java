package com.saxion.shoque.playground.model;

import com.saxion.shoque.StartGame;

import android.widget.Toast;

public class SeashoqueBoard extends GameBoard  {

	private boolean isValid = false;
	private static final int DIM = 10;
	private StartGame parentactivity;
	
	/**
	 * Instantiate 10X10 gameboard
	 */
	public SeashoqueBoard() {
		super(DIM, DIM);
	}
	
	/**
	 * Set fields as 'alive' from array containing info on which ships are to be set.
	 */
	public void setShips(int[][] list){
		//TODO: verwerk arrayarray
	}
	
	@Override
	public void onEmptyTileClicked(int x, int y) 
	{
		game.shoot(game.getEnemyBoard(), x, y);
		//TODOxxx: schiet als degene aan de beurt was
		
	}
	
	public int getDim(){
		return DIM;
	}
	


	public boolean isEmpty(int x, int y) {
		// TODO: is the given index empty?
		return false;
	}
	
}
