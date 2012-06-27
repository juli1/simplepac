package org.gunnm.pacman.model;

import android.util.Log;

public class Ennemy extends Entity{
	
	private final static String TAG = "Ennemy";
	public final static int STATE_DEAD = 1;
	public final static int STATE_ALIVE = 2;
	private int state;
	
	public Ennemy()
	{
		super ();
		state = STATE_ALIVE;
	}
	
	public String getName() {
		return "Ennemy";
	}
	
	public void died ()
	{
		this.state = STATE_DEAD;
	}
	
	public boolean isDead ()
	{
		return (this.state == STATE_DEAD);
	}
	
	public boolean isAlive ()
	{
		return (this.state == STATE_ALIVE);
	}
	
}
