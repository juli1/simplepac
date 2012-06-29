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
	private int internalStepValueX;
	private int internalStepValueY;
	private boolean active;
	
	
	public Entity ()
	{
		this (0,0);
		this.state = 0;
		this.internalStepValueX = 0;
		this.internalStepValueY = 0;
		this.active = true;
	}
	
	public boolean canMove ()
	{
		return this.active;
	}
	
	public void canMove (boolean b)
	{
		this.active = b;
	}
	
	public int getInternalStepValueX()
	{
		return this.internalStepValueX;
	}
	
	public int getInternalStepValueY()
	{
		return this.internalStepValueY;
	}
	
	public void setInternalStepValueX (int x)
	{
		this.internalStepValueX = x;
	}
	
	public void setInternalStepValueY (int y)
	{
		this.internalStepValueY = y;
	}
	
	
	public void react ()
	{
		this.state = (this.state + 1) % 4;
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
		if (this.canMove ())
		{	
			this.direction = d;
		}
	}
	
	public void setPositionX (int x)
	{
		this.positionX = x;
	}
	
	public void setPositionY (int y)
	{
		this.positionY = y;
	}
	
	public void setPosition (int[] tab)
	{
		this.positionX = tab[0];
		this.positionY = tab[1];
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
