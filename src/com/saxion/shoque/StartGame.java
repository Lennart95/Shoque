package com.saxion.shoque;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shoque.R;
import com.saxion.shoque.playground.model.Game;
import com.saxion.shoque.playground.view.GameBoardView;

public class StartGame extends Activity {

	private Game game;
	private GameBoardView gameView;
	private GameBoardView gameAiView;
	private TextView zettenLabel;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playground);
		
		gameView = (GameBoardView) findViewById(R.id.shoqueGameBoardView1);
		gameAiView = (GameBoardView) findViewById(R.id.shoqueGameBoardAIView1);
		zettenLabel = (TextView) findViewById(R.id.textViewAantalZettenGetal);
	}
	
	
}
