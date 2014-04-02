package com.saxion.shoque;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.shoque.R;
import com.saxion.shoque.playground.model.SeashoqueGame;
import com.saxion.shoque.playground.view.ShoqueGameBoardView;
import com.saxion.shoque.util.Alive;

public class SetUpActivity extends Activity implements OnClickListener {

	private ShoqueGameBoardView gameViewPlayer;
	
	private SeashoqueGame game;
	
	Context context = getApplicationContext();

	private int[][] boats;

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

	/**
	 * Button for selecting a Battleship with selectedBoat = 1
	 */
	private Button buttonBattleship;

	/**
	 * Button for selecting a Cruiser with selectedBoat = 2
	 */
	private Button buttonCruiser;

	/**
	 * Button for selecting a Submarine with selectedBoat = 3
	 */
	private Button buttonSubmarine;

	/**
	 * Button for selecting a Destroyer with selectedBoat = 4
	 */
	private Button buttonDestroyer;

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
	 * setup for the buttons and assigns a OnClickListener for each
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		gameViewPlayer = (ShoqueGameBoardView) findViewById(R.id.shoqueGameBoardView1);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);
		
		game = new SeashoqueGame(null);

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

	public void saveBoatLocation(int x, int y) {
		if(horizontal){
			if(placeHorizontal(x)){
				boats[x][y] = x + y;
				game.getGameBoard().addGameObject(
						new Alive(game), x, y);
			}
			else{
				Toast toast = Toast.makeText(context, "Not valid", Toast.LENGTH_LONG);
				toast.show();
			}
		}
		else{
			if(placeVertical(y)){
				boats[x][y] = x + y;
				game.getGameBoard().addGameObject(
						new Alive(game), x, y);
			}
			else{
				Toast toast = Toast.makeText(context, "Not valid", Toast.LENGTH_LONG);
				toast.show();
			}
		}
		
	}


	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) ((event.getX()) / mTileSize);
		int y = (int) ((event.getY()) / mTileSize);
		if (x < tileCountX && y < tileCountY

		&& (event.getX()) >= 0
				&& (event.getY()) >= 0) {
			// Log.d(TAG, "Touched (" + x + ", " + y + ")\n");
			saveBoatLocation(x, y);
		}
		return super.onTouchEvent(event);
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
				buttonOrientation.setText("Horizontal");

			} else if (horizontal == false) {
				horizontal = true;
				buttonOrientation.setText("Vertical");
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
	
	private class buttonStartListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(SetUpActivity.this, GameActivity.class);
		    startActivity(intent);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// getGameBoard().addGameObject(new Alive(this), 3, 3);
		// Log.d(TAG, "Added Alive on (3,3)");
	}
	
	public boolean placeHorizontal(int x){
		if(x + length < 10){
			return true;
		}
		return false;
	}
	
	public boolean placeVertical(int y){
		if(y + length < 10){
			return true;
		}
		return false;
	}
	
	

	public void setTextViewsBlack() {
		buttonCarrier.setTextColor(Color.BLACK);
		buttonBattleship.setTextColor(Color.BLACK);
		buttonCruiser.setTextColor(Color.BLACK);
		buttonSubmarine.setTextColor(Color.BLACK);
		buttonDestroyer.setTextColor(Color.BLACK);
	}

	public void setSelectedBoat(int SelectedBoat) {
		switch (SelectedBoat) {
		case 0:
			setTextViewsBlack();
			buttonCarrier.setTextColor(Color.BLUE);
			length = 5;
			selectedBoat = 0;
			break;

		case 1:
			setTextViewsBlack();
			buttonBattleship.setTextColor(Color.BLUE);
			length = 4;
			selectedBoat = 1;
			break;

		case 2:
			setTextViewsBlack();
			buttonCruiser.setTextColor(Color.BLUE);
			length = 3;
			selectedBoat = 2;
			break;

		case 3:
			setTextViewsBlack();
			buttonSubmarine.setTextColor(Color.BLUE);
			length = 3;
			selectedBoat = 3;
			break;

		case 4:
			setTextViewsBlack();
			buttonDestroyer.setTextColor(Color.BLUE);
			length = 2;
			selectedBoat = 4;
			break;

		}

		
	}

}
