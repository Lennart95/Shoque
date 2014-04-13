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
	 * Button for selecting with selectedBoat
	 */
	private Button buttonCarrier;
	private Button buttonBattleship;
	private Button buttonCruiser;
	private Button buttonSubmarine;
	private Button buttonDestroyer;
	private int carrierSet = 0;
	private int battleshipSet = 0;
	private int cruiserSet = 1;
	private int submarineSet = 2;
	private int destroyerSet = 0;

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

		setTextViewsInit();
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
		}
		else if(!horizontal && isShipValidY(setupBoard, x, y) && !isBoatAlreadySet(selectedBoat)){
				for(int i = 0; i < length; i++){
					setupBoard.addGameObject(new Alive(),x, y + i);
				}
				setBoat(selectedBoat);
				setupBoard.updateView();
				setTextViewsInit();
				setSelectedBoat(selectedBoat);
		}
		
	}
	
	/**
	 * Called to check if a boat has been set or not.
	 * @param i Index of the boat.
	 * @return	boolean, whether the boat has been set or not.
	 */
	private boolean isBoatAlreadySet(int i){
			boolean result = false;
		switch (i){
			case 0: result = (carrierSet>=1);
				break;
			case 1: result = (battleshipSet>=2);
				break;
			case 2: result = (cruiserSet>=3);
				break;
			case 3: result = (submarineSet>=3);
				break;
			case 4: result = (destroyerSet>=4);
				break;
		}
			return result;
	}
	
	/**
	 * Set a boolean accordingly if a ship has been set.
	 * @param selectedBoat2
	 */
	private void setBoat(int s) {
		switch (s){
		case 0: carrierSet += 1;
			break;
		case 1: battleshipSet += 1;
			break;
		case 2: cruiserSet +=1;
			break;
		case 3: submarineSet += 1;
			break;
		case 4: destroyerSet += 1;
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
			if (isBoatAlreadySet(1) && isBoatAlreadySet(2) &&isBoatAlreadySet(3) && isBoatAlreadySet(4) &&isBoatAlreadySet(0)){
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
				//TODO: adjacent check!!!
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
				//TODO: adjacent check!!!
				Log.d(TAG, "Check isEmpty on coord: " + (x) + ", " + y+i + ". Result is " + invalid);
			}
		}
		return !invalid;
	}
	
	
	//Set all text textviews
	public void setTextViewsInit() {
		buttonCarrier.setTextColor(Color.BLACK);
		buttonBattleship.setTextColor(Color.BLACK);
		buttonCruiser.setTextColor(Color.BLACK);
		buttonSubmarine.setTextColor(Color.BLACK);
		buttonDestroyer.setTextColor(Color.BLACK);
		
		//Set Red if already set and set text accordingly
		if (isBoatAlreadySet(0)) {
			buttonCarrier.setTextColor(Color.RED);}
		if (isBoatAlreadySet(1)) {
			buttonBattleship.setTextColor(Color.RED);}
		if (isBoatAlreadySet(2)) {
			buttonCruiser.setTextColor(Color.RED);}
		if (isBoatAlreadySet(3)) {
			buttonSubmarine.setTextColor(Color.RED);}
		if (isBoatAlreadySet(4)) {
			buttonDestroyer.setTextColor(Color.RED);}
		//set correct text amount 
		for (int i = 0; i<5; i++){
			setTexViewAmount(i);}
	}

	public void setSelectedBoat(int s) {
		setTextViewsInit();
		switch (s) {
		case 0:
			if (carrierSet<1){
				buttonCarrier.setTextColor(Color.BLUE);
				length = 5;
				selectedBoat = 0;
			}
			break;

		case 1:
			if (battleshipSet<2){
			buttonBattleship.setTextColor(Color.BLUE);
			length = 4;
			selectedBoat = 1;
			}
			break;

		case 2:
			if (cruiserSet<3){
			buttonCruiser.setTextColor(Color.BLUE);
			length = 3;
			selectedBoat = 2;
			}
			break;

		case 3:
			if (submarineSet<3){
			buttonSubmarine.setTextColor(Color.BLUE);
			length = 3;
			selectedBoat = 3;
			}
			break;

		case 4:
			if (destroyerSet<4){
			buttonDestroyer.setTextColor(Color.BLUE);
			length = 2;
			selectedBoat = 4;
			}
			break;

		}
		Log.d(TAG, "setSelectedBoat is called " + s);
	}
	
	public void setTexViewAmount(int boat){
		switch (boat){
			case 0: buttonCarrier.setText(getText(R.string.ship_carrier) + " (" + (1-carrierSet) + ")");
				break;
			case 1: buttonBattleship.setText(getText(R.string.ship_battleship) + " (" + (2-battleshipSet) + ")");
				break;
			case 2: buttonCruiser.setText(getText(R.string.ship_cruiser) + " (" + (3-cruiserSet) + ")");
				break;
			case 3: buttonSubmarine.setText(getText(R.string.ship_submarine) + " (" + (3-submarineSet) + ")");
				break;
			case 4: buttonDestroyer.setText(getText(R.string.ship_destroyer) + " (" + (4-destroyerSet) + ")");
				break;
		}
	}
	
	public void onBackPressed()
	{
		 Intent intent = new Intent(this, SeaShoque.class);
		    startActivity(intent);
		    finish();
	}
}
