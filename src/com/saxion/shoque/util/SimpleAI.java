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
	 * Set the ships: result [[ship],[ship id , position (x),position (y), ship direction]]
	 * Ship:					Direction:
	 * Flightdeckship = 0	5	From origin west = 1 (horizontal)
	 * Battleship = 1		4	
	 * Submarine = 2		3
	 * Torpedohunter = 3	3
	 * Minesweeper = 4		2
	 */
	public int[][] getShips(){
		int r = (int) Math.random() * 6;
		int[][] result = null;
		switch (r){
		case 0:  int[][] result1 = {{0,1,0,1},{1,8,1,0},{2,3,3,1},{3,4,9,1},{4,2,5,0}}; 
			result = result1;
			break;
		case 1: int[][] result2 = {{0,1,1,0},{1,8,3,0},{2,3,1,1},{3,1,8,1},{4,3,5,0}}; 
			result = result2;
			break;
		case 2: int[][] result3 = {{0,4,1,0},{1,5,0,1},{2,1,0,1},{3,4,7,1},{4,9,8,0}}; 
			result = result3;
			break;
		case 3: int[][] result4 = {{0,0,0,0},{1,0,6,0},{2,2,9,1},{3,6,9,1},{4,8,0,1}}; 
			result = result4;
			break;
		case 4: int[][] result5 = {{0,0,2,0},{1,7,1,0},{2,1,1,1},{3,1,7,1},{4,7,6,0}}; 
			result = result5;
			break;
		case 5: int[][] result6 = {{0,8,2,0},{1,1,3,0},{2,4,4,0},{3,5,7,1},{4,2,7,1}}; 
			result = result6;
			break;
		}
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
		}, 700);
	}
}