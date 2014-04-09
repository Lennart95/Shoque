package com.saxion.shoque.playground.model;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoque.R;
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

	// Save gameview so we can change background!
	private ShoqueGameBoardView gameViewPlayer;
	private ShoqueGameBoardView gameViewCPU;
	
	// Save boards so we can call shoot();
	private SeashoqueBoard gameBoard;
	private SeashoqueBoard enemyBoard;
	
	
	private AI cpu;

	private String winner = "";
	

	private int score;


	
	
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

		gameViewPlayer = activity.getGameBoardView();
		gameBoard = (SeashoqueBoard) super.getGameBoard();
		gameBoard.setGame(this);
		gameViewPlayer.setGameBoard(gameBoard);
		
		
		gameViewCPU = activity.getEnemyGameBoardView();
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
		
		retrieveAIShips(cpu);
		// Hard code setup ships -------------------------------//
//		// Player board
//		getGameBoard().addGameObject(new Alive(this), 3, 3);
//		getGameBoard().addGameObject(new Alive(this), 3, 4);
//		getGameBoard().addGameObject(new Alive(this), 3, 5);
//		getGameBoard().addGameObject(new Alive(this), 3, 6);
//		getGameBoard().addGameObject(new Alive(this), 3, 7);
//
//		getGameBoard().addGameObject(new Hit(), 5, 5);
//		getGameBoard().addGameObject(new Hit(), 5, 6);
//
//		getGameBoard().addGameObject(new Missed(), 8, 6);
//		getGameBoard().addGameObject(new Missed(), 8, 7);
//
//		// CPU board
//
//		getEnemyBoard().addGameObject(new Alive(this), 0, 0);
//		getEnemyBoard().addGameObject(new Alive(this), 0, 1);
//		
//
//		getEnemyBoard().addGameObject(new Hit(), 5, 5);
//		getEnemyBoard().addGameObject(new Hit(), 5, 6);
//
//		getEnemyBoard().addGameObject(new Missed(), 8, 6);
//		getEnemyBoard().addGameObject(new Missed(), 8, 7);

		///Hard code setup ships -------------------------------//
		
	}
	
	/** Register ships*/
	public void registerShip(SeashoqueBoard board, int shipID, int x, int y, int orientation){
		switch (shipID){
		case 0: 
			break;
		case 1: 
			break;
		case 2: 
			break;
		case 3: 
			break;
		case 4: 
			break;
		}
	}
	
	public void retrieveAIShips(AI cpu){
		int[][] shiplist = cpu.getShips();
		for (int i = 0; i < 5; i++){
			registerShip(enemyBoard, shiplist[i][0], shiplist[i][1], shiplist[i][2], shiplist[i][3]);
		}
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
		// Call doMove() from AI
		cpu.doMove();
		
		// Change backgrounds of the views
		if (currentplayer == 1){
			gameViewCPU.setBackgroundResource(R.drawable.background_grid_active);
			gameViewPlayer.setBackgroundResource(R.drawable.background_grid);
		}
		else {
			gameViewCPU.setBackgroundResource(R.drawable.background_grid);
			gameViewPlayer.setBackgroundResource(R.drawable.background_grid_active);
		}
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
		
		if ((target == getEnemyBoard() && currentplayer == 1)||(target == getGameBoard() && currentplayer == 0)){
		Log.d(TAG, "Shots fired at (" + target + ", " + x + ", " + y + ")");
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
				//10 points for hit!
				if (currentplayer == 1)
				{
					score+= 10;
					setScoreLabel();
					
				}
				else if (currentplayer == 0 && score > 1)
				{
					score-= 2;
					setScoreLabel();
				}else 
				{
					score = 0;
					setScoreLabel();
					
				}
				
				
				
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
				if(winner.equals("CPU"))
				{
					gameactivity.toast("Game over! " + winner + " has won with score:" + score + "\n" + "sPress anywhere to start new game.");
					score = 0;
				}
				else
				{
					gameactivity.toast(winner + " has won with score: " + score + "\n" +  
									   "Press anywhere to start a new game.");
					score = 0;	
				}
			}
		}
	}
	
	/**
	 * isGameOver is called to check whether all ships of one board are dead.
	 * Should be called after every shot.
	 */
	public boolean isGameOver() {
		boolean isPlayerAlive = false;
		boolean isCPUAlive = false;
		
		// Check if there is one or more Alive instances on the player board
		// If not, isPlayerAlive remains false, thus player is Dead;
		for (int i = 0; i < (getGameBoard().getDim()*getGameBoard().getDim()); i++){
			isPlayerAlive = isPlayerAlive | (getGameBoard().getObject(intToX(i), intToY(i)) instanceof Alive);
		}
		// If there is one or more instance of alive on playerboard, check CPU board.
		// If not, isCPUAlive remains false, thus cpu is Dead;
		for (int i = 0; i < (getEnemyBoard().getDim()*getEnemyBoard().getDim()); i++){
			isCPUAlive = isCPUAlive | (getEnemyBoard().getObject(intToX(i), intToY(i)) instanceof Alive);
		}
		// Check is there is a board with no alive gameObjects.
		gameover = !isPlayerAlive || !isCPUAlive;
		Log.d(TAG, "Player alive? " + isPlayerAlive + ". CPU Alive? " + isCPUAlive + "." + "Game Over: " + gameover);
		
		// Update variable 'winner' with the winner of the game.
		if (gameover){
			if (!isPlayerAlive){
				winner = "CPU";
			}
			else {
				winner = "Player";
			}
		}
		return !isPlayerAlive || !isCPUAlive;
	}

	/**
	 * Convert indices and coordinates 
	 * @param i
	 * @return x coordinate or y coordinate or index.
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

	/**
	 * Method to set the scoreTextView
	 */
	public void setScoreLabel(){
//		scoreTextView.setText(score);
	}
	
	public int getScore() {
		return score;
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
