package com.example.shoque;

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
	 * When getMove() is called, return an random field that has not yet been hit.
	 * @return
	 */
	public int[] getMove(){
		int i;
		int[] move = {-1, -1};
		boolean legal = false;
		
		while (!legal) {
			i = 1 + (int)Math.random()*100;
			if (game.getBoard().isEmpty(i)){
				legal = true;
			}
		}
		
		int x;
		int y;
		y = i % game.DIM;
		
		return move;
	}
}
