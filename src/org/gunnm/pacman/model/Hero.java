package org.gunnm.pacman.model;

import android.util.Log;

public class Hero extends Entity{
	private int score;
	private final static String TAG = "Hero";
	private int lifes;
	
	public Hero()
	{
		super ();
		this.score = 0;
		this.lifes = 3;
	}
	
	public void setLifes (int l)
	{
		this.lifes = l;
	}
	
	public String getName() {
		return "Hero";
	}
	
	public void addPoint ()
	{
		this.score++;
		Log.i(TAG, "Current score=" + this.score);
	}

	public void removeLife() {
		this.lifes = this.lifes - 1;
	}

	public int getLifes()
	{
		return this.lifes;
	}

	public boolean isAlive() 
	{
		return (this.lifes > 0);
	}
}
