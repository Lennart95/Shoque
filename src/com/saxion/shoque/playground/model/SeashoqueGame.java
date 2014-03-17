package com.saxion.shoque.playground.model;

import android.util.Log;

import com.saxion.shoque.GameActivity;
import com.saxion.shoque.playground.view.ShoqueGameBoardView;
import com.saxion.shoque.util.Alive;
import com.saxion.shoque.util.Hit;
import com.saxion.shoque.util.Missed;

public class SeashoqueGame extends Game {
	/** Tag used in Logcat */
	public static final String TAG = "SeaShoqueGame";
	
	/** Reference to the main menu */
	private GameActivity gameactivity;
	
	/**
	 * Integer to keep track of current player
	 * 1 is human player, 2 is AI
	 */
	private int currentplayer = 1;
	
	/**
	 * Should be true if the game is over, false if the game is still running.
	 */
	private boolean gameover = false;
	

	/**
	 * Constructor
	 * Called when you create a new game.
	 * @param gameBoard
	 */
	public SeashoqueGame(GameActivity activity) {
		// Create 2 boards
		super(new SeashoqueBoard(), new SeashoqueBoard());
		
		// Create links from boards to Game
		getGameBoard().setGame(this);
		getEnemyBoard().setGame(this);
		
		// Create reference to the main menu
		this.gameactivity = activity;

		ShoqueGameBoardView gameViewPlayer = activity.getGameBoardView();
		GameBoard gameBoard = getGameBoard();
		gameViewPlayer.setGameBoard(gameBoard);
		
		ShoqueGameBoardView gameViewCPU = activity.getEnemyGameBoardView();
		GameBoard enemyBoard = getEnemyBoard();
		gameViewCPU.setGameBoard(enemyBoard);
		

		
		// Initialise new game
		newGame();
	}

	
	public void newGame(){
		gameover = false;
		currentplayer = 1;
		getGameBoard().removeAllObjects();
		getEnemyBoard().removeAllObjects();
		
		// Hard code setup ships -------------------------------//
		// Player board
		getGameBoard().addGameObject(new Alive(), 3, 3);
		Log.d(TAG, "Added Alive on (3,3)");
		getGameBoard().addGameObject(new Alive(), 3, 4);
		Log.d(TAG, "Added Alive on (3,4)");
		getGameBoard().addGameObject(new Alive(), 3, 5);
		Log.d(TAG, "Added Alive on (3,5)");
		getGameBoard().addGameObject(new Alive(), 3, 6);
		Log.d(TAG, "Added Alive on (3,6)");
		getGameBoard().addGameObject(new Alive(), 3, 7);
		Log.d(TAG, "Added Alive on (3,7)");
		
		getGameBoard().addGameObject(new Alive(), 5, 1);
		getGameBoard().addGameObject(new Alive(), 2, 1);
		getGameBoard().addGameObject(new Alive(), 3, 1);
		getGameBoard().addGameObject(new Alive(), 4, 1);

		getGameBoard().addGameObject(new Alive(), 6, 6);
		getGameBoard().addGameObject(new Alive(), 6, 8);
		getGameBoard().addGameObject(new Alive(), 6, 7);

		getGameBoard().addGameObject(new Alive(), 9, 9);
		getGameBoard().addGameObject(new Alive(), 8, 9);
		getGameBoard().addGameObject(new Alive(), 7, 9);

		getGameBoard().addGameObject(new Alive(), 0, 0);
		getGameBoard().addGameObject(new Alive(), 0, 1);


		// CPU board
		getEnemyBoard().addGameObject(new Alive(), 3, 4);
		getEnemyBoard().addGameObject(new Alive(), 3, 5);
		getEnemyBoard().addGameObject(new Alive(), 3, 6);
		getEnemyBoard().addGameObject(new Alive(), 3, 7);
		
		getEnemyBoard().addGameObject(new Alive(), 1, 1);
		getEnemyBoard().addGameObject(new Alive(), 2, 1);
		getEnemyBoard().addGameObject(new Alive(), 3, 1);
		getEnemyBoard().addGameObject(new Alive(), 4, 1);

		getEnemyBoard().addGameObject(new Alive(), 6, 9);
		getEnemyBoard().addGameObject(new Alive(), 6, 8);
		getEnemyBoard().addGameObject(new Alive(), 6, 7);

		getEnemyBoard().addGameObject(new Alive(), 9, 9);
		getEnemyBoard().addGameObject(new Alive(), 8, 9);
		getEnemyBoard().addGameObject(new Alive(), 7, 9);

		getEnemyBoard().addGameObject(new Alive(), 0, 0);
		Log.d(TAG, "Added Alive on Enemy Board (0,0)");
		getEnemyBoard().addGameObject(new Alive(), 0, 1);
		Log.d(TAG, "Added Alive on Enemy Board (0,1)");

		// Hard code setup ships -------------------------------//
		
	}
	
	/** Register ships*/
	public void registerShip(SeashoqueBoard board, int shipID, int x, int y){
		//TODO: Go and register ships, m8
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
		
		if (target.getObject(x, y) == null){
			target.addGameObject(new Missed(), x, y);
		}
		
//		if ((target == getEnemyBoard() && currentplayer == 2)||(target == getGameBoard() && currentplayer == 1)){
//			if (target.isEmpty(x, y)){
//				target.addGameObject(new Missed(), x, y);
//			}
//			else if (target.getObject(x, y) instanceof Alive){
//				target.removeObject(target.getObject(x, y));
//				target.addGameObject(new Hit(), x, y);
//				nextPlayer(currentplayer);
//			}
//		}
//		Log.d(TAG, "Shoot! " + x + " " + y);
//		if (isGameOver()){
//			Log.d(TAG, "GameOver!");
//		}
		getGameBoard().updateView();
		getEnemyBoard().updateView();
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
		boolean result = false;
		
		// Check if there is one or more Alive instances on the player board
		for (int i = 0; i < (getGameBoard().getDim()*getGameBoard().getDim()); i++){
			result = result | (getGameBoard().getObject(intToX(i), intToY(i)) instanceof Alive);
		}
		//If there is one or more instance of alive on playerboard, check CPU board.
		if (result) {
			result = false;
			for (int i = 0; i < (getGameBoard().getDim()*getGameBoard().getDim()); i++){
				result = result | (getEnemyBoard().getObject(intToX(i), intToY(i)) instanceof Alive);
			}
		}
		// TODO: check is there is a board with no alive gameObjects.
		gameover = !result;
		return !result;
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
