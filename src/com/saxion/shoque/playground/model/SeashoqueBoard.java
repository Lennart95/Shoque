package com.saxion.shoque.playground.model;

public class SeashoqueBoard extends GameBoard {


	private int turn;
	private boolean isValid = false;
	private static final int DIM = 10;
	
	
	public SeashoqueBoard() 
	{
		super(DIM, DIM);
		
	}

	@Override
	public void onEmptyTileClicked(int x, int y) 
	{
		
		
	}
	
	public int getDim(){
		return DIM;
	}
	
}
