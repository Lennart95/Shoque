package com.saxion.shoque.playground.model;

import android.os.Parcel;
import android.util.Log;

import com.saxion.shoque.SetUpActivity;
import com.saxion.shoque.util.Hit;


/**
 *	Model class of one board. Keeps track of its contents (GameObjects). 
 *	Also the class that registers and processes the clicks.
 */
public class SeashoqueBoard extends GameBoard {
	public static final int DIM = 10;
	private boolean setupState = false;
	private SetUpActivity parent;
	
	/**
	 * Instantiate DIM^2 gameboard. DIM is a static final integer (10). DIM is defined in the superclass GameBoard.
	 */
	public SeashoqueBoard() {
		super(DIM, DIM);
	}
	
	/**
	 *	Called when an emppty field is touched. In the setupstate (Full game phase 1) saveBoatLocation is called to save the boat.
	 *	In-game (Quick game and Full Game Phase 1) the shoot method is called.
	 */
	@Override
	public void onEmptyTileClicked(int x, int y) {
		Log.d(GameBoard.TAG, "onemptytileclicked" + x + ", " + y);
		if (setupState){
			parent.saveBoatLocation(x, y);
		}
		else{
			((SeashoqueGame) getGame()).shoot(this, x, y);
		}
	}
	
	/**
	 *	@return DIM
	 */
	public int getDim(){
		return DIM;
	}
	


	/**
	 *	Called to see if field is empty.
	 *	@param x	X coordinate
	 *	@param y	Y coordinate
	 *	@return Boolean	
	 */
	public boolean isEmpty(int x, int y) {
		boolean result = true;
		if (getObject(x, y)!=null || x > DIM || y > DIM){
			result = false;
		}
		return result;
	}


	/**
	 *	Called to see if field contains Hit object
	 *	@param x X coordinate
	 *	@param y Y coordinate
	 *	@return Boolean
	 */
	public boolean isHit(int x, int y) {
		return getObject(x,y) instanceof Hit;
	}
	
	
	/**
	 *	Called to make sure methods in the SetUpActivity can be called. Saves the parent in the parent variable.	
	 */
	public void setParent(SetUpActivity p){
		parent = p;
	}


	/**
	 *	Set so the Empty Tile Click method can handle appropriate game phases
	 */
	public void setSetupState(boolean setupState) {
		this.setupState = setupState;
	}
}
