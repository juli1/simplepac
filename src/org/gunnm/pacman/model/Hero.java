package org.gunnm.pacman.model;

import android.util.Log;

public class Hero extends Entity{
	private int score;
	private final static String TAG = "Hero";
	
	public Hero()
	{
		super ();
		this.score = 0;
	}
	
	
	public void addPoint ()
	{
		this.score++;
		Log.i(TAG, "Current score=" + this.score);
	}
}
