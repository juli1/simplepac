package org.gunnm.pacman.model;

public interface MapInterface
{
	public boolean hasBorderLeft (int x, int y);
	public boolean hasBorderRight (int x, int y);
	public boolean hasBorderTop (int x, int y);
	public boolean hasBorderBottom (int x, int y);
	public boolean hasPoint (int x, int y);
	public int[] getEnnemyPosition (int index);
	public int getNbEnnemies ();
	public int getNbBonuses ();
	public int[] getBonusPosition (int index);
	public int[] getHeroPosition ();
	public int getWidth();
	public int getHeight ();
	
	public int getUnvulnerableCounter ();
}
