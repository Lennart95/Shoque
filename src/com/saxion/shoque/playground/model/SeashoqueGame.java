package com.saxion.shoque.playground.model;

import android.util.Log;

import com.saxion.shoque.GameActivity;
import com.saxion.shoque.playground.view.ShoqueGameBoardView;
import com.saxion.shoque.util.AI;
import com.saxion.shoque.util.Alive;
import com.saxion.shoque.util.Hit;
import com.saxion.shoque.util.Missed;
import com.saxion.shoque.util.SimpleAI;

public class SeashoqueGame extends Game {
	/** Tag used in Logcat */
	public static final String TAG = "SeaShoqueGame";
	
	/** Reference to the main menu */
	private GameActivity gameactivity;
	
	/**
	 * Integer to keep track of current player
	 * 1 is human player, 0 is AI
	 */
	public int currentplayer = 1;
	
	/**
	 * Should be true if the game is over, false if the game is still running.
	 */
	private boolean gameover = false;
	
	private SeashoqueBoard gameBoard;
	private SeashoqueBoard enemyBoard;
	
	
	private AI cpu;

	/**
	 * Constructor
	 * Called when you create a new game.
	 * @param gameBoard
	 */
	public SeashoqueGame(GameActivity activity) {
		// Create 2 boards
		super(new SeashoqueBoard(), new SeashoqueBoard());
		
		// Create reference to the main menu
		this.gameactivity = activity;

		ShoqueGameBoardView gameViewPlayer = activity.getGameBoardView();
		gameBoard = (SeashoqueBoard) super.getGameBoard();
		gameBoard.setGame(this);
		gameViewPlayer.setGameBoard(gameBoard);
		
		ShoqueGameBoardView gameViewCPU = activity.getEnemyGameBoardView();
		enemyBoard = (SeashoqueBoard) super.getEnemyBoard();
		enemyBoard.setGame(this);
		gameViewCPU.setGameBoard(enemyBoard);
		
		// Initiate the AI
		cpu = new SimpleAI(this);
		
		// Initialise new game
		newGame();
	}

	/**
	 * Create a new game: delete all existing objects and put in some new ones.
	 */
	public void newGame(){
		gameover = false;
		currentplayer = 1;
		getGameBoard().removeAllObjects();
		getEnemyBoard().removeAllObjects();
		
		// Hard code setup ships -------------------------------//
		// Player board
		getGameBoard().addGameObject(new Alive(this), 3, 3);
		getGameBoard().addGameObject(new Alive(this), 3, 4);
		getGameBoard().addGameObject(new Alive(this), 3, 5);
		getGameBoard().addGameObject(new Alive(this), 3, 6);
		getGameBoard().addGameObject(new Alive(this), 3, 7);

		getGameBoard().addGameObject(new Hit(), 5, 5);
		getGameBoard().addGameObject(new Hit(), 5, 6);

		getGameBoard().addGameObject(new Missed(), 8, 6);
		getGameBoard().addGameObject(new Missed(), 8, 7);

		// CPU board

		getEnemyBoard().addGameObject(new Alive(this), 0, 0);
		getEnemyBoard().addGameObject(new Alive(this), 0, 1);
		

		getEnemyBoard().addGameObject(new Hit(), 5, 5);
		getEnemyBoard().addGameObject(new Hit(), 5, 6);

		getEnemyBoard().addGameObject(new Missed(), 8, 6);
		getEnemyBoard().addGameObject(new Missed(), 8, 7);

		///Hard code setup ships -------------------------------//
		
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
	public void nextPlayer() {
		Log.d(TAG, "Next player!");
		this.currentplayer = (currentplayer+1) % 2;
		// TODO: update some visual to alert the next player it's his turn

		// Call doMove() from AI
		cpu.doMove();
	}

	/**
	 * Get the dimensions of the board
	 * @return
	 */
	public int getDim() {
		return (getGameBoard()).getDim();
	}


	/**
	 * Most important function: this is what the game consists of.
	 * 
	 * @param target
	 * @param x
	 * @param y
	 */
	public void shoot(SeashoqueBoard target, int x, int y){
		Log.d(TAG, "Shots fired at (" + target + ", " + x + ", " + y + ")");
		
		if ((target == getEnemyBoard() && currentplayer == 1)||(target == getGameBoard() && currentplayer == 0)){
			//Missed!
			if (target.isEmpty(x, y)){
				Log.d(TAG, "Missed!");
				target.addGameObject(new Missed(), x, y);
				
				target.updateView();
				nextPlayer();
			}
			//HIT!
			else if (target.getObject(x, y) instanceof Alive){
				Log.d(TAG, "Hit!");
				
				target.removeObject(target.getObject(x, y));
				Log.d(TAG, "Removed Object");
				
				target.addGameObject(new Hit(), x, y);
				Log.d(TAG, "Added Hit object");

				target.updateView();
				if (currentplayer == 0){
					cpu.doMove();
				}
			}
		
			if (isGameOver()){
				Log.d(TAG, "GameOver!");
				newGame();
				gameactivity.toast("You might have won! Let's start over!");
			}
		}
	}

	/**
	 * isGameOver is called to check whether all ships of one board are dead.
	 * Should be called after every shot.
	 */
	public boolean isGameOver() {
		boolean result = false;
		
		// Check if there is one or more Alive instances on the player board
		for (int i = 0; i < (((SeashoqueBoard) getGameBoard()).getDim()^2); i++){
			result = result | (getGameBoard().getObject(intToX(i), intToY(i)) instanceof Alive);
		}
		//If there is one or more instance of alive on playerboard, check CPU board.
		if (result) {
			result = false;
			for (int i = 0; i < (((SeashoqueBoard) getGameBoard()).getDim()^2); i++){
				result = result | (getEnemyBoard().getObject(intToX(i), intToY(i)) instanceof Alive);
			}
		}
		// TODO: check is there is a board with no alive gameObjects.
		gameover = result;
		return result;
	}

	/**
	 * Convert indices and coordinates 
	 * @param i
	 * @return
	 */
	public int intToX(int i){
		return i%((SeashoqueBoard) getGameBoard()).getDim();
	}
	public int intToY(int i){
		return (int) i/((SeashoqueBoard) getGameBoard()).getDim();
	}
	public int toIndex(int x, int y){
		return ((y*((SeashoqueBoard) getGameBoard()).getDim())+x);
	}

	@Override
	public SeashoqueBoard getGameBoard(){
		return gameBoard;
	}
	
	@Override
	public SeashoqueBoard getEnemyBoard(){
		return enemyBoard;
	}


}
