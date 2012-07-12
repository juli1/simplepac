package org.gunnm.simplepac.model;

public interface MapInterface
{
	public boolean hasBorderLeft (int x, int y);
	public boolean hasBorderRight (int x, int y);
	public boolean hasBorderTop (int x, int y);
	public boolean hasBorderBottom (int x, int y);
	public boolean hasPoint (int x, int y);
	public int[] getEnnemyPosition (int index);
	public int getNbEnnemies ();
	public int getNbSuperPoints ();
	public int[] getSuperPointPosition (int index);
	
	public int getNbSpecialSmall ();
	public int[] getSpecialSmallPosition (int index);
	
	public int getNbSpecialMedium ();
	public int[] getSpecialMediumPosition (int index);
	
	public int getNbSpecialBig ();
	public int[] getSpecialBigPosition (int index);
	
	public int[] getHeroPosition ();
	public int getWidth();
	public int getHeight ();
	
	public int getUnvulnerableCounter ();
}
