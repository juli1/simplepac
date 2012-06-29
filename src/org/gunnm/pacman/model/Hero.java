package org.gunnm.pacman.model;

import android.util.Log;

public class Hero extends Entity{
	private int score;
	private final static String TAG = "Hero";
	private int lifes;
	private int vulnerable;
	private boolean dying;
	public final static int STATE_VULNERABLE = 1;
	public final static int STATE_UNVULNERABLE = 2;
	
	public Hero()
	{
		super ();
		this.vulnerable = STATE_VULNERABLE;
		this.score = 0;
		this.lifes = 3;
	}
	
	public boolean isVulnerable ()
	{
		return (vulnerable == STATE_VULNERABLE);
	}
	
	public void setVulnerable ()
	{
		vulnerable = STATE_VULNERABLE;
	}
	
	public void setUnVulnerable ()
	{
		vulnerable = STATE_UNVULNERABLE;
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
		Log.i(TAG, "Number of lifes" + this.lifes);
		if (this.lifes > 0)
		{
			this.lifes = this.lifes - 1;
		}
		
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
	
	public boolean isDying ()
	{
		return this.dying;
	}
	
	public void isDying (boolean b)
	{
		this.state = 0;
		dying = b;
	}
}
