package com.saxion.shoque.util;

import java.util.Observable;

import com.saxion.shoque.playground.model.Game;
import com.saxion.shoque.playground.model.SeashoqueGame;

public class SimpleAI extends Observable{
	
	private SeashoqueGame game;
	
	/**
	 * Instantieert SimpleAI zodat deze aangesproken kan worden. 
	 * Ook wordt game gezet welk game dit object moet aanspreken.
	 * @param game
	 */
	public SimpleAI(SeashoqueGame game){
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
	 * When doMove() is called, return an random field that has not yet been hit and notify's observers.
	 * Also calls game.shoot
	 * @return
	 */
	public void doMove(){
		int i = 0;
		
		boolean legal = false;
		
		// Board must not be full (so there are no empty spots) 
		// and the loop must stop as soon as it has found a legal spot
		while (!legal && !game.isFull(game.getGameBoard())) {
			i = (int)Math.random()*99;			//Generate Random between 0 and 99
			if (game.getGameBoard().isEmpty(game.intToX(i), game.intToY(i))){	//Check if random is emtpy
				legal = true;					//If so, break the loop and submit empty spot
			}
		}
		
		int x = 0;
			x = i % game.getDim();
		int y = 0;
			y = i / game.getDim();
		
		int[] move = {x, y};
		game.shoot(game.getGameBoard(), x, y);
		
		setChanged();
		notifyObservers();
		
		
	}

	public void update(Observable arg0, Object arg1) {
		//Todo: update with Userinterface.
		
	}
}
