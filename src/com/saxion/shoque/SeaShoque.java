package com.saxion.shoque;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.shoque.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class SeaShoque extends Activity implements OnClickListener {
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
	private Button playGame;
	private Button stats;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_start_menu);
		playGame = (Button) findViewById(R.id.button_playgame);
		stats = (Button) findViewById(R.id.button_stats);

		playGame.setOnClickListener(this);
		stats.setOnClickListener(this);
		
		//--- Use Custom Font ---// 
		//TODO:CRASHES ON SET FONT
		//setCustomFont("fonts/ChunkFive.ttf", R.id.button_playgame);
		//setCustomFont("fonts/ChunkFive.ttf", R.id.button_stats);
		
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
		delayedHide(100);
	}

	/**
	 * Touch listener to use for in-layout UI controls to delay hiding the
	 * system UI. This is to prevent the jarring behavior of controls going away
	 * while interacting with activity UI.
	 */
	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
		}
	};

	/**
	 * Schedules a call to hide() in [delay] milliseconds, canceling any
	 * previously scheduled calls.
	 */
	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}
	
	public void setCustomFont(String fontPath, int id){
        Button txt = (Button) findViewById(id);
		// Loading Font Face          
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);  
        // Applying font
        txt.setTypeface(tf);
	}
	
	private void startGame(){
	    Intent intent = new Intent(this, GameActivity.class);
	    startActivity(intent);
	}

	
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.button_playgame:
			startGame();
			break;
//		case R.id.button_settings:
//			//settings();
//			break;
//		case R.id.button_stats:
//			//stats();
//			break;
		}
		
	}
}
