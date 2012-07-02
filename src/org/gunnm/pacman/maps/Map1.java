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
	
	private final static int BL = borderLeft;
	private final static int BR = borderRight;
	private final static int BT = borderTop;
	private final static int BB = borderBottom;
	private final static int HE = hasEnnemy;
	private final static int HP = hasPoint;
	private final static int HB = hasSuperPoint;
	
	int[][] ennemiesTable = new int[NB_ENNEMIES][2];
	int[][] bonusesTable = new int[NB_BONUSES][2];
	int[][] map = new int[][]
	{ 
		{BT| BL    , BT       ,   BT|BB       , BT          ,      BB    ,BT|BB          ,    BT | BB       ,    BT|BB      ,    BT|BB     ,   BT|BR},
		{BL| BR    , BL|BR    ,   BT|BL|BR    , BL | BR     ,   BL|BT    ,   BT|BB       ,    BT|BB         ,    BT         ,    BT|BR|BB  ,   BL|  BR},
		{BL| BR    , BL|BR    ,   BL|BR       , BL |BR      ,   BL       ,   BT|BB       ,    BT|BB         ,   HE          ,   BB|BT      ,   BR},
		{BL| BR    , BL|BR    ,   BL|BR| BB   , BL |BR      ,   BL |BB   ,   BB|BT       ,    BB|HB|BR|BT   ,   BL          ,   BT|BR      ,   BL| BR},
		{BL| BR    , BL       ,   BT|BB       , BB          ,   BT       ,   BT |BB      ,      BT          ,   BT |BR      ,   BB |BL     ,   BR},
		{    BR    , BB|BR|BL ,   BL|BB|BT    , HB|BB|BT    ,   HB|BB|BR ,   BL |BT      ,      BR          ,   BL|BR       ,   BL|BT      ,   0},
		{BL| BB    , BT       ,   BT          , HE|BT|BB    ,   BT|BB    ,   BR          ,      BL|BR|HP    ,   BL|BR       ,   BL|BR      ,   BL|BR},
		{BL| BT|BR , BL|BR    ,   HE|BL|BB    , BT|BB       ,   BT|BB    ,   BR          ,   BL|BR|HP       ,   BL|HE|BR    ,   BL|BR      ,   BL|BR},
		{BL| BR    , BL|BR    ,   BL|HB|BT    , BB | BT     ,   BB|BT    ,   BR          ,  BL|BR|HP        ,   BL|BR       ,   BL|BR      ,   BL|BR},
		{BL| BR    , BL       ,   BT|BB|HE    , BT | BB     ,   HB |BB   ,   BB          ,   BB|BR          ,  BL| HB |BB   ,   BB |BR     ,   BL|   BR},
		{BL| BB    ,   BB     ,   BT|BB       , BT|BB       , BT         ,  BT| BB       ,   BT|BB          ,   BT|BB       ,  BT| BB      ,   BR|BB   },
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
