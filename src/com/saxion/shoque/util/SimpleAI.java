package com.saxion.shoque.util;

import android.os.Handler;
import android.util.Log;

import com.saxion.shoque.playground.model.SeashoqueGame;

public class SimpleAI implements AI{
	
	private static final String TAG = "SIMPLEAI";
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
	 * Set the ships: result [[i],[position (index), ship,direction]]
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
	 * When doMove() is called, return an random field that has not yet been hit.
	 * Also calls game.shoot
	 * @return
	 */
	public void doMove(){
		//--Vertraging!!--//
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
		
				Log.d(TAG, "Go do a move");
				int i = 0;
				int x = -1;
				int y = -1;
				boolean legal = false;

				Log.d(TAG, "Init legal:" + legal);
				// Board must not be full (so there are no empty spots) 
				// and the loop must stop as soon as it has found a legal spot
				while (!legal && !game.isFull(game.getGameBoard())) {
					i = (int) (Math.random()*99);			//Generate Random between 0 and 99
					Log.d(TAG, "Random: " + i);			//TODO: RANDOM NOT WORKING
					x = i % game.getDim();
					y = i / game.getDim();
					if (game.getGameBoard().getObject(x, y) instanceof Alive || game.getGameBoard().isEmpty(x, y)){	//Check if random is emtpy
						legal = true;					//If so, break the loop and submit empty spot
					}
				}
				Log.d(TAG, "Is that random actually empty? " + game.getGameBoard().isEmpty(x, y));
				
				int[] move = {x, y};
				game.shoot(game.getGameBoard(), x, y);
				Log.d(TAG, "I just shot all over the place at (" + x + ", " + y + ")");
				
			}
		}, 1500);
	}
}