package com.saxion.shoque;

public class SimpleAI {
	
	private Game game;
	
	/**
	 * 
	 * @param game
	 */
	public SimpleAI(Game game){
		this.game = game;
	}
	
	/**
	 * Set the ships: result [[index],[ship,direction]]
	 * Ship:					Direction:
	 * Flightdeckship = 0		From origin west = 0
	 * Battleship = 1			From origin south = 1
	 * Submarine = 2
	 * Torpedohunter = 3
	 * Minesweeper = 4
	 */
	public int[][] setShips(){
		int[][] result = {{0,0,1},{1,1,1},{2,2,1},{3,3,1},{4,4,1}};
		
		
		return result;
	}
	
	/**
	 * When getMove() is called, return an random field that has not yet been hit.
	 * @return
	 */
	public int[] getMove(){
		int i;
		
		boolean legal = false;
		
		// Board must not be full (so there are no empty spots) 
		// and the loop must stop as soon as it has found a legal spot
		while (!legal && !game.isFull()) {
			i = (int)Math.random()*99;			//Generate Random between 0 and 99
			if (game.getBoard().isEmpty(i)){	//Check if random is emtpy
				legal = true;					//If so, break the loop and submit empty spot
			}
		}
		
		int x = 0;
			x = i % game.DIM;
		int y = 0;
			y = i / game.DIM;
		
		int[] move = {x, y};
		return move;
	}
}
