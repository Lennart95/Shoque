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
	private GameBoard gameBoard;
	private GameBoard enemyBoard; //Custom code in framework

	/**
	 * Called when you create a new game.
	 * @param gameBoard
	 * @param enemyBoard 	 //Custom code in framework
	 */
	public Game(GameBoard gameBoard, GameBoard enemyBoard) {
		this.gameBoard = gameBoard;
		this.enemyBoard = enemyBoard;	 //Custom code in framework
	}
	/** Returns a reference to the game board. */
	public GameBoard getGameBoard() {
		return (GameBoard) gameBoard;
	}	
	
	/** Returns a reference to the game board. */	 //Custom code in framework
	public GameBoard getEnemyBoard() {				 //Custom code in framework
		return (GameBoard) enemyBoard;				 //Custom code in framework
	}												 //Custom code in framework
}