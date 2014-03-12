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
	
	/**
	 * Variables containing the 2 boards maintained by Game
	 */
	private SeashoqueBoard gameBoard;
	private SeashoqueBoard enemyBoard;
	
	/**
	 * Integer to keep track of current player
	 */
	private int turn = 1;
	
	/**
	 * ??
	 */
	private int shots = 0; //what does this mean?
	
	/**
	 * Should be true if the game is over, false if the game is still running.
	 */
	private boolean gameover;

	/**
	 * Called when you create a new game.
	 * @param gameBoard
	 */
	public Game() {
		this.gameBoard = new SeashoqueBoard();
		this.enemyBoard = new SeashoqueBoard();
		gameBoard.setGame(this);
		enemyBoard.setGame(this);
	}

	/** 
	 * Returns the gameboard of the human player. 
	 */
	public SeashoqueBoard getGameBoard() {
		return gameBoard;
	}
	
	/** 
	 * Returns the gameboard of the AI. 
	 */
	public SeashoqueBoard getEnemyBoard() {
		return enemyBoard;
	}
	
	/**
	 * Handles stuff when next player should do it's thingey. 
	 * @param t
	 */
	public void nextPlayer(int t) {
		if (t == 1){
			turn = 2;
		} else {
			turn = 1;
		}
		//TODO: update some visual to alert the next player it's his turn
	}

	//------------ ------------- ------------- ------------- -------------//
	//------------ ------------- ------------- ------------- -------------//
	//------------ ------------- ------------- ------------- -------------//
	//----   TODO: some part where the game asks the user for ships  -----//
	//------------ ------------- ------------- ------------- -------------//
	//------------ ------------- ------------- ------------- -------------//
	//------------ ------------- ------------- ------------- -------------//
	
	
	/**
	 * Return true if the all the fields of the given board have a gameObject. Return false if an empty field has been found.
	 * @return
	 */
	public boolean isFull(SeashoqueBoard board) {
		boolean result = true;
		for (int x = 0; x<board.getDim(); x++){
			for (int y = 0; y<board.getDim(); y++){
				if (board.getObject(x, y)==null){
					result = false;
				}
			}
		}
		return result;
	}
	
	/**
	 * Get the dimensions of the board
	 * @return
	 */
	public int getDim(){
		return gameBoard.getDim();
	}
	
	/**
	 * isFirstShot checks if the first shot has been fired. 
	 * @param shots
	 */
	public void isFirstShot(int shots){ //There are better solutions -casper
		if (shots == 0){
			// TODO: ??
		}	
	}
	
	/**
	 * Most important function: this is what the game consists of.
	 * @param target
	 * @param x
	 * @param y
	 */
	public void shoot(SeashoqueBoard target, int x, int y){
		//TODO: check if opposite of target is allowed to shoot yet
		//TODO: check hit/miss
		//TODO: update board
		//TODO: on hit: ask for another move, on miss: call nextPlayer();
	}
	
	/**
	 * isGameOver is called to check whether all ships of one board are dead. Should be called after every shot.
	 */
	public boolean isGameOver(){
		//TODO: check is there is a board with no alive gameObjects.
		return false;
	}
	
	/**
	 * Run the game, keep turns, accept moves etc.
	 */
	public void run(){
		while(!isFull(gameBoard) ||!isFull(enemyBoard) || !gameover){
			//TODO: constant check for input (is this necessary in android?)
		}
	}
	
}
