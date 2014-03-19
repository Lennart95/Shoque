package com.saxion.shoque;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shoque.R;
import com.saxion.shoque.playground.model.SeashoqueGame;
import com.saxion.shoque.playground.view.ShoqueGameBoardView;



public class GameActivity extends Activity {

	private SeashoqueGame game;
	private ShoqueGameBoardView gameViewPlayer;
	private ShoqueGameBoardView gameViewCPU;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playground);
				
		gameViewPlayer = (ShoqueGameBoardView) findViewById(R.id.shoqueGameBoardView1);
		gameViewCPU = (ShoqueGameBoardView) findViewById(R.id.shoqueGameBoardView2);
		
		game = new SeashoqueGame(this);
	}
	
	/**
	 * Returns the view on the own game board view.
	 */
	public ShoqueGameBoardView getGameBoardView() {
		return gameViewPlayer;
	}
	/**
	 * Returns the view on the enemy game board view.
	 */
	public ShoqueGameBoardView getEnemyGameBoardView() {
		return gameViewCPU;
	}
	
	public void toast(String msg){
		Toast.makeText(getApplicationContext(), msg,
				Toast.LENGTH_SHORT).show();
	}

	
}
