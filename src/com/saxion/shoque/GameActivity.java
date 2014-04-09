package com.saxion.shoque;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoque.R;
import com.saxion.shoque.playground.model.SeashoqueGame;
import com.saxion.shoque.playground.view.ShoqueGameBoardView;



public class GameActivity extends Activity {

	private SeashoqueGame game;
	private ShoqueGameBoardView gameViewPlayer;
	private ShoqueGameBoardView gameViewCPU;
	private TextView scoreTextView; 
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playground);
				
		gameViewPlayer = (ShoqueGameBoardView) findViewById(R.id.shoqueGameBoardView1);
		gameViewCPU = (ShoqueGameBoardView) findViewById(R.id.shoqueGameBoardView2);
		scoreTextView = (TextView)findViewById(R.id.textViewScoreCijfer);
		
		// Hide boats on enemy board.
		//gameViewCPU.hideBoats();
		
		game = new SeashoqueGame(this);
	}
	
	/**
	 * Returns the view on the own game board view.
	 */
	public ShoqueGameBoardView getGameBoardView() {
		return gameViewPlayer;
	}
	
	/**
	 * Method to set the score textview
	 */
	
	public void setScoreLabel(){
		scoreTextView.
		setText(Integer.toString(game.getScore()));
	}
	
	/**
	 * Returns the view on the enemy game board view.
	 */
	public ShoqueGameBoardView getEnemyGameBoardView() {
		return gameViewCPU;
	}
	
	public void toast(String msg){
		Toast.makeText(getApplicationContext(), msg,
				Toast.LENGTH_LONG).show();
	}

	private static final int TIME_INTERVAL = 2000;
	private long BackPressed;

	@Override
	public void onBackPressed()
	{
	    if (BackPressed + TIME_INTERVAL > System.currentTimeMillis()) 
	    { 
	      Intent intent = new Intent(this, SeaShoque.class);
	      startActivity(intent);
	      
	    	
	    	
	    }
	    else { Toast.makeText(getBaseContext(), "Tap back button again to go to home screen", Toast.LENGTH_SHORT).show(); }

	    BackPressed = System.currentTimeMillis();
	}
}