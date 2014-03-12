package com.saxion.shoque;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shoque.R;
import com.saxion.shoque.playground.model.SeashoqueBoard;
import com.saxion.shoque.playground.view.GameBoardView;



public class StartGame extends Activity {

	private SeashoqueBoard game;
	private GameBoardView gameView;
	private GameBoardView gameAiView;
	private TextView setsLabel;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playground);
		
		Intent intent = getIntent();
		
		gameView = (GameBoardView) findViewById(R.id.shoqueGameBoardView1);
		gameAiView = (GameBoardView) findViewById(R.id.shoqueGameBoardAIView1);
		setsLabel = (TextView) findViewById(R.id.textViewAantalZettenGetal);
		
		game = new SeashoqueBoard();
	}
	
	
}
