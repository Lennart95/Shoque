package com.saxion.shoque.playground.model;

import com.saxion.shoque.util.Alive;
import com.saxion.shoque.util.Hit;
import com.saxion.shoque.util.Missed;


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
	private GameBoard enemyBoard;

	/**
	 * Called when you create a new game.
	 * @param gameBoard
	 */
	public Game(GameBoard gameBoard, GameBoard enemyBoard) {
		this.gameBoard = gameBoard;
		this.enemyBoard = enemyBoard;
	}
	/** Returns a reference to the game board. */
	public SeashoqueBoard getGameBoard() {
		return (SeashoqueBoard) gameBoard;
	}	
	
	/** Returns a reference to the game board. */
	public SeashoqueBoard getEnemyBoard() {
		return (SeashoqueBoard) enemyBoard;
	}
}