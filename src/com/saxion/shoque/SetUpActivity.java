package com.saxion.shoque;




import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.shoque.R;
import com.saxion.shoque.playground.view.ShoqueGameBoardView;


public class SetUpActivity extends Activity implements OnClickListener {

	private ShoqueGameBoardView gameViewPlayer;
	private Button buttonCarrier;
	private Button buttonBattleship;
	private Button buttonCruiser;
	private Button buttonSubmarine;
	private Button buttonDestroyer;
	private Button buttonOrientation;
	private boolean horizontal;
	private int length;

	
	
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
	
	private class buttonOrientationListener implements View.OnClickListener{

		@Override
		public void onClick(View arg0) {
			if(horizontal){
				horizontal = false; 
				buttonOrientation.setText("Horizontal");
				
			}else if (horizontal == false){
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
		
//		getGameBoard().addGameObject(new Alive(this), 3, 3);
//		Log.d(TAG, "Added Alive on (3,3)");
	}
	/**
	 * edits the orientation of the ships (vertical or horizontal)
	 */
	
	public void Orientation(){
		
		
	}
}
