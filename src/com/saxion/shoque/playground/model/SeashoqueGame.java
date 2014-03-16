package com.saxion.shoque.playground.model;

import com.saxion.shoque.util.Alive;
import com.saxion.shoque.util.Hit;
import com.saxion.shoque.util.Missed;

public class SeashoqueGame extends Game {

	/**
	 * Integer to keep track of current player
	 * 1 is human player, 2 is AI
	 */
	private int currentplayer = 1;
	
	/**
	 * Should be true if the game is over, false if the game is still running.
	 */
	private boolean gameover;
	

	/**
	 * Called when you create a new game.
	 * 
	 * @param gameBoard
	 */
	public SeashoqueGame() {
		super(new SeashoqueBoard(), new SeashoqueBoard());
	}

	/**
	 * Return true if the all the fields of the given board have a gameObject.
	 * Return false if an empty field has been found.
	 * 
	 * @return
	 */
	public boolean isFull(SeashoqueBoard board) {
		boolean result = true;
		for (int x = 0; x < board.getDim(); x++) {
			for (int y = 0; y < board.getDim(); y++) {
				if (board.getObject(x, y) == null) {
					result = false;
				}
			}
		}
		return result;
	}
	
	/**
	 * Handles stuff when next player should do it's thingey.
	 * 
	 * @param t
	 */
	public void nextPlayer(int current) {
		if (current == 1){
			currentplayer = 2;
		} else {
			currentplayer = 1;
		}
		// TODO: update some visual to alert the next player it's his turn
	}

	/**
	 * Get the dimensions of the board
	 * 
	 * @return
	 */
	public int getDim() {
		return ((SeashoqueBoard) getGameBoard()).getDim();
	}


	/**
	 * Most important function: this is what the game consists of.
	 * 
	 * @param target
	 * @param x
	 * @param y
	 */
	public void shoot(SeashoqueBoard target, int x, int y){
		
		if ((target == getEnemyBoard() && currentplayer == 2)||(target == getGameBoard() && currentplayer == 1)){
			if (target.isEmpty(x, y)){
				target.addGameObject(new Missed(), x, y);
			}
			else if (target.getObject(x, y) instanceof Alive){
				target.removeObject(target.getObject(x, y));
				target.addGameObject(new Hit(), x, y);
				nextPlayer(currentplayer);
			}
		}
		isGameOver();
	}
//	x	TODO: check if opposite of target is allowed to shoot yet 
//	x	TODO: check hit/miss
//		TODO: update board
//	x	TODO: on hit: ask for another move, on miss: call nextPlayer();


	/**
	 * isGameOver is called to check whether all ships of one board are dead.
	 * Should be called after every shot.
	 */
	public boolean isGameOver() {
		boolean result = true;
		for (int i = 0; i < (getGameBoard().getDim()^2); i++){
			if (getGameBoard().getObject(intToX(i), intToY(i)) instanceof Alive){
				result = false;
			}
			if (getEnemyBoard().getObject(intToX(i), intToY(i)) instanceof Alive){
				result = false;
			}
		}
		// TODO: check is there is a board with no alive gameObjects.
		return result;
	}

	/**
	 * Convert indices and coordinates 
	 * @param i
	 * @return
	 */
	public int intToX(int i){
		return i%getGameBoard().getDim();
	}
	public int intToY(int i){
		return (int) i/getGameBoard().getDim();
	}
	public int toIndex(int x, int y){
		return ((y*getGameBoard().getDim())+x);
	}



}
