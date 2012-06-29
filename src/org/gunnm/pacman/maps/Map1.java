package org.gunnm.pacman.maps;

import org.gunnm.pacman.model.MapInterface;

public class Map1 implements MapInterface
{
	private final static int NB_ENNEMIES = 5;
	private final static int MAP_WIDTH = 10;
	private final static int MAP_HEIGHT = 11;
	private final static int NB_BONUSES  = 6;
	
	private final static int borderLeft        = 0x01;
	private final static int borderRight       = 0x02;
	private final static int borderTop         = 0x04;
	private final static int borderBottom      = 0x08;
	private final static int hasPoint          = 0x10;
	private final static int hasSuperPoint     = 0x20;
	private final static int hasEnnemy         = 0x40;
	
	
	
	int[][] ennemiesTable = new int[NB_ENNEMIES][2];
	int[][] bonusesTable = new int[NB_BONUSES][2];
	int[][] map = new int[][]
	{
		{borderTop | borderLeft ,  borderTop                   , borderTop|borderBottom                    , borderTop   ,    borderTop,borderTop,borderTop,borderTop,borderTop,borderTop|borderRight},
		{borderLeft|borderRight ,  borderLeft|borderRight      , borderTop | borderLeft |borderRight       , borderLeft | borderRight,borderLeft,0,0,0,0,borderRight},
		{borderLeft|borderRight ,  borderLeft|borderRight      , borderLeft | borderRight                  , borderLeft |borderRight,borderLeft,0,0,hasEnnemy,0,borderRight},
		{borderLeft|borderRight ,  borderLeft|borderRight      , borderLeft | borderRight | borderBottom   ,borderLeft |borderRight,borderLeft,0,hasSuperPoint,0,0,borderRight},
		{borderLeft|borderRight ,  0                           , borderTop                                 ,0,0,0,0,0,0,borderRight},
		{borderLeft|borderRight ,  borderBottom                ,borderBottom                               ,hasSuperPoint,hasSuperPoint,0,0,0,0,borderRight},
		{borderLeft|borderBottom,  borderTop                   ,borderTop                                  ,hasEnnemy,0,0,hasPoint,0,0,borderRight},
		{borderLeft|borderTop   ,  0                           ,hasEnnemy                                  ,0,0,0,hasPoint,hasEnnemy,0,borderRight},
		{borderLeft,0,hasSuperPoint,0,0,0,hasPoint,0,0,borderRight},
		{borderLeft,0,hasEnnemy,0,hasSuperPoint,0,0,hasSuperPoint,0,borderRight},
		{borderLeft|borderBottom,borderBottom,borderBottom,borderBottom,borderBottom,borderBottom,borderBottom,borderBottom,borderBottom,borderRight|borderBottom},
	};
	
	
	public Map1()
	{
		int ind;

		/*
		 * Bonuses table definition
		 */
		ind = 0;
		for (int x = 0 ; x < MAP_WIDTH ; x++)
		{
			for (int y = 0 ; y < MAP_WIDTH ; y++)
			{
				if ((map[y][x] & hasSuperPoint ) != 0)
				{
					bonusesTable[ind][0] = x;
					bonusesTable[ind][1] = y;
					ind = (ind + 1) % NB_BONUSES;
				}
			}			
		}
		

		/*
		 * Ennemy table definition
		 */
		ind = 0;
		for (int x = 0 ; x < MAP_WIDTH ; x++)
		{
			for (int y = 0 ; y < MAP_WIDTH ; y++)
			{
				
				if ((map[y][x] & hasEnnemy ) != 0)
				{
					ennemiesTable[ind][0] = x;
					ennemiesTable[ind][1] = y;
					ind = (ind + 1) % NB_ENNEMIES;
				}
			}			
		}
		
	}
	
	public int getWidth ()
	{
		return MAP_WIDTH;
	}
	
	public int getHeight ()
	{
		return MAP_HEIGHT;
	}
	
	public boolean hasBorderLeft(int x, int y)
	{
		if ( (map[y][x] & borderLeft) != 0)
		{
			return true;
		}
		return false;
	}

	public boolean hasPoint(int x, int y) 
	{
		if ( (map[y][x] & hasPoint) != 0)
		{
			return true;
		}
		return false;
	}
	
	public boolean hasBorderRight(int x, int y) 
	{
		if ( (map[y][x] & borderRight) != 0)
		{
			return true;
		}
		return false;
	}

	public boolean hasBorderTop(int x, int y)
	{
		if ( (map[y][x] & borderTop) != 0)
		{
			return true;
		}
		return false;
	}

	public boolean hasBorderBottom(int x, int y)
	{
		if ( (map[y][x] & borderBottom) != 0)
		{
			return true;
		}
		return false;
	}

	public int[] getEnnemyPosition(int index)
	{
		return ennemiesTable[index];
	}

	public int getNbEnnemies()
	{
		
		return NB_ENNEMIES;
	}

	public int getNbBonuses()
	{
		return NB_BONUSES;
	}

	public int[] getBonusPosition(int index)
	{
		return bonusesTable[index];
	}

	public int[] getHeroPosition()
	{
		return new int[]{3,3};
	}
	
	public int getUnvulnerableCounter ()
	{
		return 50;
	}

}
