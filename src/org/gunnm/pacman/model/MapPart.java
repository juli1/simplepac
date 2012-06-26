package org.gunnm.pacman.model;

public class MapPart {
	private boolean leftBorder;
	private boolean rightBorder;
	private boolean topBorder;
	private boolean bottomBorder;
	
	
	public MapPart ()
	{
		leftBorder = false;
		rightBorder = false;
		topBorder = false;
		bottomBorder = false;
	}
	
	
	public boolean hasBorderTop ()
	{
		return this.topBorder;
	}
	
	public boolean hasBorderBottom ()
	{
		return this.bottomBorder;
	}
	
	public boolean hasBorderRight ()
	{
		return this.rightBorder;
	}
	
	public boolean hasBorderLeft ()
	{
		return this.leftBorder;
	}
	
	public void setBorderTop (boolean b)
	{
		this.topBorder = b;
	}
	
	public void setBorderBottom(boolean b)
	{
		this.bottomBorder = b;
	}
	
	public void setBorderLeft (boolean b)
	{
		this.leftBorder = b;
	}
	
	public void setBorderRight (boolean b)
	{
		this.rightBorder = b;
	}
}
