package com.saxion.shoque.playground.model;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.example.shoque.R;
import com.saxion.shoque.GameActivity;
import com.saxion.shoque.SeaShoque;
import com.saxion.shoque.SetUpActivity;
import com.saxion.shoque.playground.view.ShoqueGameBoardView;
import com.saxion.shoque.util.AI;
import com.saxion.shoque.util.Alive;
import com.saxion.shoque.util.AppState;
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
	
	// The AI
	private AI cpu;

	// Winner string
	private String winner = "";
	
	// Score variable
	private int score = 0;


	
	
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
		if (AppState.getInstance().getPlayerBoard() != null){
			Log.d(TAG, "PlayerBoard from AppState recieved");
			gameBoard = AppState.getInstance().getPlayerBoard();
			//Delete the PlayerBoard right after
			AppState.getInstance().setPlayerBoard(null);
		}
		else {
			Log.d(TAG, "PlayerBoard in AppState was null, new board created");
			gameBoard = (SeashoqueBoard) super.getGameBoard();
			registerShip(gameBoard, 0, 0, 0, 1);
			registerShip(gameBoard, 1, 2, 1, 1);
			registerShip(gameBoard, 2, 2, 7, 1);
			registerShip(gameBoard, 3, 7, 3, 0);
			registerShip(gameBoard, 4, 0, 4, 0);
		}
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
	 * Create a new game: delete all existing objects, collect the AI boats.
	 */
	public void newGame(){
		Log.d(TAG, "Start new GAME :D");
		if (gameover){
			removeShips();
		}
		
		gameover = false;
		currentplayer = 1;
		
		retrieveAIShips(cpu);
		
		gameBoard.updateView();
		enemyBoard.updateView();
		
		score = 0;
	}
	
	/**
	 * Remove all ships and update view.
	 */
	public void removeShips(){
		Log.d(TAG, "Remove ALL OBJECTS :D");
		getGameBoard().removeAllObjects();
		getEnemyBoard().removeAllObjects();
		gameBoard.updateView();
		enemyBoard.updateView();
	}
	
	/**
	 * 
	 * @param board		 The board where the ships should be placed.
	 * @param shipID	 ShipID, number of the ship: 0 carrier, 1 battleship, 2 cruiser, 3 submarine, 4 destroyer. 
	 * @param x			 X coordinate for the ships 'origin'
	 * @param y			 Y coordinate for the ships 'origin'
	 * @param horizontal Boolean Integer: if 1 then horizontal = true
	 */
	public void registerShip(SeashoqueBoard board, int shipID, int x, int y, int horizontal){
		Log.d(TAG,"Registers Ship in registerShip(): " + shipID + ", " + x + ", " + y + ", " + horizontal);
		if (horizontal == 1){
			switch (shipID){
				case 0: for (int i = 0; i < 5; i++){board.addGameObject(new Alive(this), x+i, y);}
				break;
				case 1: for (int i = 0; i < 4; i++){board.addGameObject(new Alive(this), x+i, y);} 
				break;
				case 2: for (int i = 0; i < 3; i++){board.addGameObject(new Alive(this), x+i, y);}
				break;
				case 3: for (int i = 0; i < 3; i++){board.addGameObject(new Alive(this), x+i, y);}
				break;
				case 4: for (int i = 0; i < 2; i++){board.addGameObject(new Alive(this), x+i, y);}
				break;
			}
		}
		if (horizontal != 1){
			switch (shipID){
				case 0: for (int i = 0; i < 5; i++){board.addGameObject(new Alive(this), x, y+i);}
				break;
				case 1: for (int i = 0; i < 4; i++){board.addGameObject(new Alive(this), x, y+i);} 
				break;
				case 2: for (int i = 0; i < 3; i++){board.addGameObject(new Alive(this), x, y+i);}
				break;
				case 3: for (int i = 0; i < 3; i++){board.addGameObject(new Alive(this), x, y+i);}
				break;
				case 4: for (int i = 0; i < 2; i++){board.addGameObject(new Alive(this), x, y+i);}
				break;
			}
		}
	}
	

	/**
	 *	Collect ships
	 *	@param cpu Collect from which AI?
	 */
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
	 * @return boolean
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
	 * Handles calls to recieve AI's shots and gives visual indication so the appropriate player will shoot.
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
	 * @return int DIM
	 */
	public int getDim() {
		return (getGameBoard()).getDim();
	}


	/**
	 * Most important function: this is what the game consists of. Shoots, calls or doesn't call nextPlayer() etc.
	 * 
	 * @param target
	 * @param x
	 * @param y
	 */
	public void shoot(SeashoqueBoard target, int x, int y){
		
		if ((target == getEnemyBoard() && currentplayer == 1)||(target == getGameBoard() && currentplayer == 0) && !gameover ){
			Log.d(TAG, "Shots fired at (" + target + ", " + x + ", " + y + ")");
			//Missed!
			if (target.isEmpty(x, y)){
				Log.d(TAG, "Missed!");
				target.addGameObject(new Missed(), x, y);
				//-4 points for missing
				if (currentplayer == 1){
					setScore(getScore()-4);
					gameactivity.setScoreLabel();}
					
					target.updateView();
					nextPlayer();
				}
			//HIT!
				else if (target.getObject(x, y) instanceof Alive){
					Log.d(TAG, "Hit!");
				//10 points for hit!
					if (currentplayer == 1){
						setScore(getScore()+10);
						gameactivity.setScoreLabel();}
						else{
							setScore(getScore()-2);
							gameactivity.setScoreLabel();}

							target.removeObject(target.getObject(x, y));
							Log.d(TAG, "Removed Object");

							target.addGameObject(new Hit(), x, y);
							Log.d(TAG, "Added Hit object");

							target.updateView();
							if (currentplayer == 0){
								cpu.doMove();
							}
						}
					}
					isGameOver();
				}

	/**
	 * isGameOver is called to check whether all ships of one board are dead.
	 * Should be called after every shot.
	 * @return boolean GameOver
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
				playAgainDialog();
			}
			else {
				winner = "Player";
				playAgainDialog();
			}
		}
		return !isPlayerAlive || !isCPUAlive;
	}


	/**
	 *	Ask the player if he wants to play again. 
	 */
	public void playAgainDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(gameactivity);

		if(winner.equals("CPU"))
		{
			builder.setMessage("Game over! " + winner + " has won with score:" + score + "." + "\n" + "Do you want to play again?");
			score = 0;
		}
		else
		{
			builder.setMessage(winner + " has won with score: " + score + "." + "\n" + "Do you want to play again?");
			score = 0;	
		}
		builder.setTitle("Game over!");
		//	builder.setMessage("Do you want to play again?");

		builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(gameactivity, SetUpActivity.class);
				gameactivity.startActivity(intent);

				dialog.dismiss();
				gameactivity.finish();
			}

		});

		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				Intent intent = new Intent(gameactivity, SeaShoque.class);
				gameactivity.startActivity(intent);

				dialog.dismiss();
				gameactivity.finish();
			}

		});

		AlertDialog alert = builder.create();
		alert.show();
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
	 * REMOVED from SeaShoqueGame: setscorelabel. now in other class
	 */
	public void setScoreLabel(){
	//		scoreTextView.setText(score);
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int point){
		if (point < 0){
			score = 0;
		}else {
			score = point;
		}
		
		
		
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
