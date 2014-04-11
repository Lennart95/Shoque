package com.saxion.shoque;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.shoque.R;
import com.saxion.shoque.playground.model.SeashoqueBoard;
import com.saxion.shoque.playground.view.ShoqueGameBoardView;
import com.saxion.shoque.util.Alive;
import com.saxion.shoque.util.AppState;

/**
 * SetUpActivity functions as model and controller for the game feature: setting up ships.
 * @author Casper
 *
 */
public class SetUpActivity extends Activity implements OnClickListener {

	private static final String TAG = "SetUpActivity";
	private ShoqueGameBoardView gameViewPlayer;
	private SeashoqueBoard setupBoard;
	private ShoqueGameBoardView setupBoardView;
	
	private String textHorizontal = "Placing horizontal, press for vertical";
	private String textVertical = "Placing vertical, press for horizontal";
	
//	Context context = getApplicationContext();

//	private int[][] boats;

	/** Number of tiles in X-direction. */
	private int tileCountX = 10;

	/** Number of tiles in Y-direction. */
	private int tileCountY = 10;

	/** Size (in pixels) of the tiles. */
	private int mTileSize = 20;

	/**
	 * A variable to keep track of the selected boat
	 */
	private int selectedBoat = -1;

	/**
	 * Button for selecting a Carrier with selectedBoat = 0
	 */
	private Button buttonCarrier;
	private boolean carrierSet = false;

	/**
	 * Button for selecting a Battleship with selectedBoat = 1
	 */
	private Button buttonBattleship;
	private boolean battleshipSet = false;

	/**
	 * Button for selecting a Cruiser with selectedBoat = 2
	 */
	private Button buttonCruiser;
	private boolean cruiserSet = false;

	/**
	 * Button for selecting a Submarine with selectedBoat = 3
	 */
	private Button buttonSubmarine;
	private boolean submarineSet = false;

	/**
	 * Button for selecting a Destroyer with selectedBoat = 4
	 */
	private Button buttonDestroyer;
	private boolean destroyerSet = false;

	/**
	 * Button for the orientation of the ships, horizontal or vertical
	 */
	private Button buttonOrientation;

	/**
	 * Button to start the game
	 */

	private Button startButton;
	/**
	 * a variable to check if the boat is horizontal or not
	 */
	private boolean horizontal;

	/**
	 * a variable for the length of the boats
	 */
	private int length;

	/**
	 * Setup for the buttons and assigns a OnClickListener for each
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);
		
		setupBoardView = (ShoqueGameBoardView) findViewById(R.id.shoqueGameBoardView1);
		setupBoard = new SeashoqueBoard();
		setupBoard.setParent(this);
		setupBoard.setSetupState(true);
		
		//Link board and view to each other
		setupBoardView.setGameBoard(setupBoard);

		buttonCarrier = (Button) findViewById(R.id.buttonCarrier);
		buttonBattleship = (Button) findViewById(R.id.buttonBattleship);
		buttonCruiser = (Button) findViewById(R.id.buttonCruiser);
		buttonSubmarine = (Button) findViewById(R.id.buttonSubmarine);
		buttonDestroyer = (Button) findViewById(R.id.buttonDestroyer);
		buttonOrientation = (Button) findViewById(R.id.buttonOrientation);
		startButton = (Button) findViewById(R.id.buttonStartGame);

		buttonCarrier.setOnClickListener(new buttonCarrierListener());
		buttonBattleship.setOnClickListener(new buttonBattleshipListener());
		buttonCruiser.setOnClickListener(new buttonCruiserListener());
		buttonSubmarine.setOnClickListener(new buttonSubmarineListener());
		buttonDestroyer.setOnClickListener(new buttonDestroyerListener());
		buttonOrientation.setOnClickListener(new buttonOrientationListener());
		startButton.setOnClickListener(new buttonStartListener());

	}
	
	/**
	 * This method is called when onEmptyTile(x, y) is clicked on the board. It ignores the click if the location is invalid 
	 * (other boat is in the way, or overlaps the boarder of the board), but saves it when saving is ok. It requires 
	 * @param x
	 * @param y
	 */
	public void saveBoatLocation(int x, int y) {
		Log.d(TAG, "savedBoatLocation() is being executed: " + x + ", " + y );
		if(horizontal && isShipValidX(setupBoard, x, y) && !isBoatAlreadySet(selectedBoat)){
				for(int i = 0; i < length; i++){
					setupBoard.addGameObject(new Alive(),x + i, y);
				}
				setBoat(selectedBoat);
				setupBoard.updateView();
				setTextViewsInit();
				selectedBoat = -1;
				length = 0;
		}
		else if(!horizontal && isShipValidY(setupBoard, x, y) && !isBoatAlreadySet(selectedBoat)){
				for(int i = 0; i < length; i++){
					setupBoard.addGameObject(new Alive(),x, y + i);
				}
				
				setBoat(selectedBoat);
				setupBoard.updateView();
				setTextViewsInit();
				selectedBoat = -1;
				length = 0;
		}
		
	}
	
	/**
	 * Called to check if a boat has been set or not.
	 * @param i Index of the boat.
	 * @return	boolean, whether the boat has been set or not.
	 */
	private boolean isBoatAlreadySet(int i){
		Log.d(TAG, "Boats set: Carrier " + carrierSet + ", Battleship " + 
				battleshipSet + ", Cruiser " + cruiserSet + ", Submarine " 
				+ submarineSet + ", Destroyer " + destroyerSet);
		boolean result = false;
		switch (i){
			case 0: result = carrierSet;
				break;
			case 1: result = battleshipSet;
				break;
			case 2: result = cruiserSet;
				break;
			case 3: result = submarineSet;
				break;
			case 4: result = destroyerSet;
				break;
		}
		Log.d(TAG, "Boats set: Carrier " + carrierSet + ", Battleship " + 
				battleshipSet + ", Cruiser " + cruiserSet + ", Submarine " 
				+ submarineSet + ", Destroyer " + destroyerSet);
		return result;
	}
	
	/**
	 * Set a boolean accordingly if a ship has been set.
	 * @param selectedBoat2
	 */
	private void setBoat(int s) {
		switch (s){
		case 0: carrierSet = true;
			break;
		case 1: battleshipSet = true;
			break;
		case 2: cruiserSet = true;
			break;
		case 3: submarineSet = true;
			break;
		case 4: destroyerSet = true;
			break;
		}
		
	}

	/**
	 * a method to select the orientation for the boat.
	 * 
	 */
	private class buttonOrientationListener implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			if (horizontal) {
				horizontal = false;
				buttonOrientation.setText(textVertical);

			} else if (horizontal == false) {
				horizontal = true;
				buttonOrientation.setText(textHorizontal);
			}

		}

	}

	private class buttonCarrierListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			setSelectedBoat(0);
		}

	}

	private class buttonBattleshipListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			setSelectedBoat(1);
		}
	}

	private class buttonCruiserListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			setSelectedBoat(2);
		}
	}

	private class buttonSubmarineListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			setSelectedBoat(3);
		}
	}

	private class buttonDestroyerListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			setSelectedBoat(4);
		}
	}
	
	//STARTS INDENT ONLY IF SHIPS ARE ALL SET
	private class buttonStartListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			Log.d(TAG, "StartGameClicked");
			if (carrierSet && battleshipSet && cruiserSet && submarineSet && destroyerSet){
				Log.d(TAG,"All ships are set. Get ready to sail!");
				AppState holder = AppState.getInstance();
				holder.setPlayerBoard(setupBoard);
				Log.d(TAG,"PlayerBoard in AppState set from SetUpActivity");
				
				//Start the new activity
			    Intent intent = new Intent(SetUpActivity.this, GameActivity.class);
			    startActivity(intent);
			    finish();
			}
		}
	}


	@Override
	public void onClick(View v) {
		//Do nothing
	}
	
	/**
	 * Check if the ship remains within board boundaries
	 * @param x
	 * @return
	 */
	public boolean isShipValidX(SeashoqueBoard board, int x, int y){
		boolean invalid = false;

		invalid = (horizontal && (x + length > SeashoqueBoard.DIM));
		//Check for horizontal
		if (horizontal && !invalid){
			for (int i = 0; i<length; i++){
				invalid = invalid || !setupBoard.isEmpty(x+i, y);
				Log.d(TAG, "Check isEmpty on coord: " + (x+i) + ", " + y + ". Result is " + invalid);
			}
		}
		return !invalid;
	}
	
	public boolean isShipValidY(SeashoqueBoard board, int x, int y){
		boolean invalid = false;

		invalid = (y + length > SeashoqueBoard.DIM);
		//Check for Vertical
		if (!horizontal && !invalid){
			for (int i = 0; i<length; i++){
				invalid = invalid || !setupBoard.isEmpty(x, y+i);
				Log.d(TAG, "Check isEmpty on coord: " + (x) + ", " + y+i + ". Result is " + invalid);
			}
		}
		return !invalid;
	}
	
	
	//Set all text textviews black
	public void setTextViewsInit() {
		buttonCarrier.setTextColor(Color.BLACK);
		buttonBattleship.setTextColor(Color.BLACK);
		buttonCruiser.setTextColor(Color.BLACK);
		buttonSubmarine.setTextColor(Color.BLACK);
		buttonDestroyer.setTextColor(Color.BLACK);
		
		//Set Red if already set
		if (carrierSet) {
			buttonCarrier.setTextColor(Color.RED);}
		if (battleshipSet) {
			buttonBattleship.setTextColor(Color.RED);}
		if (cruiserSet) {
			buttonCruiser.setTextColor(Color.RED);}
		if (submarineSet) {
			buttonSubmarine.setTextColor(Color.RED);}
		if (destroyerSet) {
			buttonDestroyer.setTextColor(Color.RED);}
		
	}

	public void setSelectedBoat(int s) {
		switch (s) {
		case 0:
			setTextViewsInit();
			if (!carrierSet){
				buttonCarrier.setTextColor(Color.BLUE);
				length = 5;
				selectedBoat = 0;
			}
			break;

		case 1:
			setTextViewsInit();
			if (!battleshipSet){
			buttonBattleship.setTextColor(Color.BLUE);
			length = 4;
			selectedBoat = 1;
			}
			break;

		case 2:
			setTextViewsInit();
			if (!cruiserSet){
			buttonCruiser.setTextColor(Color.BLUE);
			length = 3;
			selectedBoat = 2;
			}
			break;

		case 3:
			setTextViewsInit();
			if (!submarineSet){
			buttonSubmarine.setTextColor(Color.BLUE);
			length = 3;
			selectedBoat = 3;
			}
			break;

		case 4:
			setTextViewsInit();
			if (!destroyerSet){
			buttonDestroyer.setTextColor(Color.BLUE);
			length = 2;
			selectedBoat = 4;
			}
			break;

		}
		Log.d(TAG, "setSelectedBoat is called " + s);
	}
	
	public void onBackPressed()
	{
		 Intent intent = new Intent(this, SeaShoque.class);
		    startActivity(intent);
		    finish();
	}
}
