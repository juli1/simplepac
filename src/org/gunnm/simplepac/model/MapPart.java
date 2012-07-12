package org.gunnm.simplepac.model;

public class MapPart {
	private boolean leftBorder;
	private boolean rightBorder;
	private boolean topBorder;
	private boolean bottomBorder;
	private boolean hasPoint;
	private boolean hasSuperPoint;
	private boolean hasSpecialSmall;
	private boolean hasSpecialMedium;
	private boolean hasSpecialBig;
	
	public MapPart ()
	{
		leftBorder 			= false;
		rightBorder 		= false;
		topBorder 			= false;
		bottomBorder 		= false;
		hasPoint 			= false;
		hasSuperPoint 		= false;
		hasSpecialSmall 	= false;
		hasSpecialMedium 	= false;
		hasSpecialBig   	= false;
	}
	
	
	public boolean hasBorderTop ()
	{
		return this.topBorder;
	}
	
	public boolean hasPoint ()
	{
		return this.hasPoint;
	}
	
	public boolean hasSuperPoint ()
	{
		return this.hasSuperPoint;
	}
	
	public boolean hasSpecialSmall ()
	{
		return this.hasSpecialSmall;
	}
	
	public boolean hasSpecialMedium ()
	{
		return this.hasSpecialMedium;
	}
	
	public boolean hasSpecialBig ()
	{
		return this.hasSpecialBig;
	}
	
	public void setPoint (boolean b)
	{
		this.hasPoint = b;
	}
	
	public void setSuperPoint (boolean b)
	{
		this.hasSuperPoint = b;
	}
	
	public void enablePoint ()
	{
		this.setPoint (true);
		this.disableSuperPoint();
	}

	public void disablePoint ()
	{
		this.setPoint (false);
	}

	public void enableSuperPoint ()
	{
		this.setSuperPoint (true);
		this.disablePoint();
	}

	public void disableSuperPoint ()
	{
		this.setSuperPoint (false);
	}
	
	public void setSpecialSmall (boolean b)
	{
		this.hasSpecialSmall = b;
	}
	
	public void enableSpecialSmall ()
	{
		this.setSpecialSmall (true);
	}

	public void disableSpecialSmall ()
	{
		this.setSpecialSmall (false);
	}

	
	public void setSpecialMedium (boolean b)
	{
		this.hasSpecialMedium = b;
	}
	
	public void enableSpecialMedium ()
	{
		this.setSpecialMedium (true);
	}

	public void disableSpecialMedium ()
	{
		this.setSpecialMedium (false);
	}

	
	
	public void setSpecialBig (boolean b)
	{
		this.hasSpecialBig = b;
	}
	
	public void enableSpecialBig ()
	{
		this.setSpecialBig (true);
	}

	public void disableSpecialBig ()
	{
		this.setSpecialBig (false);
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
