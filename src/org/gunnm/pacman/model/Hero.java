package org.gunnm.pacman.model;

import android.util.Log;

public class Hero extends Entity{
	private int score;
	private final static String TAG = "Hero";
	private int lifes;
	private int state;
	public final static int STATE_VULNERABLE = 1;
	public final static int STATE_UNVULNERABLE = 2;
	
	public Hero()
	{
		super ();
		this.state = STATE_VULNERABLE;
		this.score = 0;
		this.lifes = 3;
	}
	
	public boolean isVulnerable ()
	{
		return (state == STATE_VULNERABLE);
	}
	
	public void setVulnerable ()
	{
		state = STATE_VULNERABLE;
	}
	
	public void setUnVulnerable ()
	{
		state = STATE_UNVULNERABLE;
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
		this.addPoints (1);
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

	public void addPoints (int i)
	{
		this.score = this.score + i;
		Log.i(TAG, "Current score=" + this.score);
	}
}
