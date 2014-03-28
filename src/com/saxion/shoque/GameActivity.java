package com.saxion.shoque;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
		
		gameViewPlayer.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if (game.isGameOver()){
					game.newGame();
				}
			}
		});
		gameViewCPU.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if (game.isGameOver()){
					game.newGame();
				}
			}
		});
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
				Toast.LENGTH_LONG).show();
	}

	
}
