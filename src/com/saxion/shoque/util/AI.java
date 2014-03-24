package com.saxion.shoque.util;

public interface AI {
	
	/**
	 * Execute a shot on the Player Board (GameBoard).
	 * @return index
	 */
	public void doMove();
	
	/**
	 * Called to set ships on the EnemyBoard.
	 * Set the ships: result [[i],[position (index), ship,direction]]
	 * Ship:					Direction:
	 * Flightdeckship = 0		From origin west = 0
	 * Battleship = 1			From origin south = 1
	 * Submarine = 2
	 * Torpedohunter = 3
	 * Minesweeper = 4
	 * 
	 * @return int[][]: int[i][position (index), ship, orientation]
	 */
	public int[][] setShips();
}
