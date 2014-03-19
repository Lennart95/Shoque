package com.saxion.shoque;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.shoque.R;

public class SetUpActivity extends Activity implements OnClickListener {

	private Button buttonCarrier;
	private Button buttonBattleship;
	private Button buttonCruiser;
	private Button buttonSubmarine;
	private Button buttonDestroyer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);

		// buttonCarrier = (Button) findViewById(R.id.buttonCarrier);
		// buttonBattleship = (Button) findViewById(R.id.buttonBattleship);
		// buttonCruiser = (Button) findViewById(R.id.buttonCruiser);
		// buttonSubmarine = (Button) findViewById(R.id.buttonSubmarine);
		// buttonDestroyer = (Button) findViewById(R.id.buttonDestroyer);
		//
		//
		// buttonCarrier.setOnClickListener(new buttonCarrierListener());
		// buttonBattleship.setOnClickListener(new buttonBattleshipListener());
		// buttonCruiser.setOnClickListener(new buttonCruiserListener());
		// buttonSubmarine.setOnClickListener(new buttonSubmarineListener());
		// buttonDestroyer.setOnClickListener(new buttonDestroyerListener());

	}

	private class buttonCarrierListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			buttonCarrier.setTextColor(Color.BLUE);
			buttonBattleship.setTextColor(Color.BLACK);
			buttonCruiser.setTextColor(Color.BLACK);
			buttonSubmarine.setTextColor(Color.BLACK);
			buttonDestroyer.setTextColor(Color.BLACK);
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
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
