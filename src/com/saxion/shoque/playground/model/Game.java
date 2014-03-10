package com.saxion.shoque.playground.model;

/**
 * Superclass for all games. 
* 
 * You should subclass this for your own game. In that subclass you will keep
 * track of all game-related state, like the score, who's turn it is, etc.
 * 
 * @author Paul de Groot
 */
public abstract class Game {
	private SeashoqueBoard gameBoard;
	private int turn = 1;
	private int shots = 0;

	/**
	 * Called when you create a new game.
	 * @param gameBoard
	 */
	public Game() {
		this.gameBoard = new SeashoqueBoard();
		gameBoard.setGame(this);
	}

	/** Returns a reference to the game board. */
	public GameBoard getGameBoard() {
		return gameBoard;
	}
	
	public int nextPlayer(int t) {
		if (t == 1){
			turn = 2;
		}
		else {
			turn = 1;
		}
		return turn;
	}
	
	public static boolean validTurn (boolean isValid){
		isValid = false;
		
		
		
		return isValid;
	}

	public boolean isFull() {
		// TODO: Auto-generated method stub
		return false;
	}
	public int getDim(){
		return gameBoard.getDim();
	}
	
	public int shoot(int shots){
		if (shots = 0){
			
		}
		
		
		
	}
	
	
	
	
	
}
