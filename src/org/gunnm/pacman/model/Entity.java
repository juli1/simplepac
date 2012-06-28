package org.gunnm.pacman.model;

public abstract class Entity {
	private int positionX;
	private int positionY;
	private int direction;
	
	public static final int DIRECTION_UP 		= 1;
	public static final int DIRECTION_DOWN		= 2;
	public static final int DIRECTION_LEFT 		= 3;
	public static final int DIRECTION_RIGHT 	= 4;
	public static final int DIRECTION_NONE		= 5;
	protected int state;
	
	
	
	public Entity ()
	{
		this (0,0);
		this.state = 0;
	}
	
	public void react ()
	{
		this.state = (this.state + 1) % 6;
	}
	
	public int getState ()
	{
		return this.state;
	}
	
	public abstract String getName ();
	
	public Entity (int x, int y)
	{
		this.positionX = x;
		this.positionY = y;
		this.direction = DIRECTION_NONE;
	}
	
	public int getDirection ()
	{
		return this.direction;
	}
	
	public void setDirection (int d)
	{
		this.direction = d;
	}
	
	public void setPositionX (int x)
	{
		this.positionX = x;
	}
	
	public void setPositionY (int y)
	{
		this.positionY = y;
	}
	
	public int getPositionX()
	{
		return this.positionX;
	}
	
	public int getPositionY()
	{
		return this.positionY;
	}
}
