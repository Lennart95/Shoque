package com.saxion.shoque.playground.model;

public class SeashoqueBoard extends GameBoard {

	private boolean isValid = false;
	private static final int DIM = 10;
	
	
	/**
	 * Instantiate 10X10 gameboard
	 */
	public SeashoqueBoard() {
		super(DIM, DIM);
	}

	@Override
	public void onEmptyTileClicked(int x, int y) 
	{
		//TODO: schiet als degene aan de beurt was
		
	}
	
	public int getDim(){
		return DIM;
	}
	
}
