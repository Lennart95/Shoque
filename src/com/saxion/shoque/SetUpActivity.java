package com.saxion.shoque;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import com.example.shoque.R;
import com.saxion.shoque.playground.view.ShoqueGameBoardView;

public class SetUpActivity extends Activity implements OnClickListener {

	private ShoqueGameBoardView gameViewPlayer;
	private int[][] boats;
	
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

		buttonCarrier = (Button) findViewById(R.id.buttonCarrier);
		buttonBattleship = (Button) findViewById(R.id.buttonBattleship);
		buttonCruiser = (Button) findViewById(R.id.buttonCruiser);
		buttonSubmarine = (Button) findViewById(R.id.buttonSubmarine);
		buttonDestroyer = (Button) findViewById(R.id.buttonDestroyer);
		buttonOrientation = (Button) findViewById(R.id.buttonOrientation);

		buttonCarrier.setOnClickListener(new buttonCarrierListener());
		buttonBattleship.setOnClickListener(new buttonBattleshipListener());
		buttonCruiser.setOnClickListener(new buttonCruiserListener());
		buttonSubmarine.setOnClickListener(new buttonSubmarineListener());
		buttonDestroyer.setOnClickListener(new buttonDestroyerListener());
		buttonOrientation.setOnClickListener(new buttonOrientationListener());

	
	}
	public void saveBoatLocation(int x, int y){
		boats[x][y] = x + y;
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
			buttonCarrier.setTextColor(Color.BLUE);
			buttonBattleship.setTextColor(Color.BLACK);
			buttonCruiser.setTextColor(Color.BLACK);
			buttonSubmarine.setTextColor(Color.BLACK);
			buttonDestroyer.setTextColor(Color.BLACK);
			length = 5;
			selectedBoat = 0;

		}

	}

	private class buttonBattleshipListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			buttonCarrier.setTextColor(Color.BLACK);
			buttonBattleship.setTextColor(Color.BLUE);
			buttonCruiser.setTextColor(Color.BLACK);
			buttonSubmarine.setTextColor(Color.BLACK);
			buttonDestroyer.setTextColor(Color.BLACK);
			length = 4;
			selectedBoat = 1;
		}
	}

	private class buttonCruiserListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			buttonCarrier.setTextColor(Color.BLACK);
			buttonBattleship.setTextColor(Color.BLACK);
			buttonCruiser.setTextColor(Color.BLUE);
			buttonSubmarine.setTextColor(Color.BLACK);
			buttonDestroyer.setTextColor(Color.BLACK);
			length = 3;
			selectedBoat = 2;
		}
	}

	private class buttonSubmarineListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			buttonCarrier.setTextColor(Color.BLACK);
			buttonBattleship.setTextColor(Color.BLACK);
			buttonCruiser.setTextColor(Color.BLACK);
			buttonSubmarine.setTextColor(Color.BLUE);
			buttonDestroyer.setTextColor(Color.BLACK);
			length = 3;
			selectedBoat = 3;
		}
	}

	private class buttonDestroyerListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			buttonCarrier.setTextColor(Color.BLACK);
			buttonBattleship.setTextColor(Color.BLACK);
			buttonCruiser.setTextColor(Color.BLACK);
			buttonSubmarine.setTextColor(Color.BLACK);
			buttonDestroyer.setTextColor(Color.BLUE);
			length = 2;
			selectedBoat = 4;
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

}
