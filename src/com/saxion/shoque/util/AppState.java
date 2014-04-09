package com.saxion.shoque.util;

import com.saxion.shoque.playground.model.*;

/**
 * Singleton pattern class to hold cross-activity data without serializable.
 * @author Casper
 *
 */
public class AppState {
	// Singleton instance
	private static AppState instance;
	
	// PlayerBoard to save from SetUpActivity and load it in GameActivity
	private SeashoqueBoard playerBoard;
	
	
	/**
	 *  Singelton constructor
	 */
	private AppState(){
	}
	/**
	 * Make the instance if it doesn't exist. Return the instance.
	 * @return instance
	 */
	public static AppState getInstance(){
		if (instance == null) {
			instance = new AppState();
		}
	return instance;
	}
	
	/**
	 * Returns the playerBoard. Warning: can be null. Called in GameActivity.
	 * @return playerBoard
	 */
	public SeashoqueBoard getPlayerBoard() {
		return playerBoard;
	}
	
	/**
	 * Sets the playerboard. Called in SetUpActivity.
	 * @param playerBoard
	 */
	public void setPlayerBoard(SeashoqueBoard playerBoard) {
		this.playerBoard = playerBoard;
	}
}
