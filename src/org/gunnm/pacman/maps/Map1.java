package org.gunnm.pacman.maps;

import org.gunnm.pacman.model.MapInterface;

public class Map1 implements MapInterface
{
	private final static int NB_ENNEMIES 			= 5;
	private final static int MAP_WIDTH 				= 10;
	private final static int MAP_HEIGHT 			= 11;
	private final static int NB_SUPERPOINTS  		= 6;
	private final static int NB_SPECIAL_SMALL  		= 4;
	private final static int NB_SPECIAL_MEDIUM  	= 2;
	private final static int NB_SPECIAL_BIG  		= 1;
	
	private final static int borderLeft        = 0x0001;
	private final static int borderRight       = 0x0002;
	private final static int borderTop         = 0x0004;
	private final static int borderBottom      = 0x0008;
	private final static int hasPoint          = 0x0010;
	private final static int hasSuperPoint     = 0x0020;
	private final static int hasEnnemy         = 0x0040;
	private final static int hasSpecialSmall   = 0x0080;
	private final static int hasSpecialMedium  = 0x0100;
	private final static int hasSpecialBig     = 0x0200;
	
	private final static int BL = borderLeft;
	private final static int BR = borderRight;
	private final static int BT = borderTop;
	private final static int BB = borderBottom;
	private final static int HE = hasEnnemy;
	private final static int HP = hasPoint;
	private final static int HB = hasSuperPoint;
	private final static int SS = hasSpecialSmall;
	private final static int SM = hasSpecialMedium;
	private final static int SB = hasSpecialMedium;
	
	int[][] ennemiesTable 		= new int[NB_ENNEMIES][2];
	int[][] superPointsTable 	= new int[NB_SUPERPOINTS][2];
	int[][] specialSmallTable 	= new int[NB_SPECIAL_SMALL][2];
	int[][] specialMediumTable 	= new int[NB_SPECIAL_MEDIUM][2];
	int[][] specialBigTable 	= new int[NB_SPECIAL_BIG][2];
	int[][] map 				= new int[][]
	{ 
		{BT| BL    , BT|SS       ,   BT|BB       , BT          ,      BB    ,BT|BB          ,    BT | BB       ,    BT|BB      ,    BT|BB     ,   BT|BR},
		{BL| BR    , BL|BR    ,   BT|BL|BR    , BL | BR     ,   BL|BT    ,   BT|BB       ,    BT|BB         ,    BT         ,    BT|BR|BB  ,   BL|  BR},
		{BL| BR | HE    , BL|BR    ,   BL|BR       , BL |BR      ,   BL       ,   BT|BB       ,    BT|BB         ,   HE          ,   BB|BT      ,   BR},
		{BL| BR    , BL|BR    ,   BL|BR| BB   , BL |BR      ,   BL |BB   ,   BB|BT       ,    BB|HB|BR|BT   ,   BL|BB          ,   BT|BR      ,   BL| BR},
		{BL| BR    , BL |SM      ,   BT|BB       , BB |SS      ,  SB| BT       ,   BT |BB      ,      BT          ,   BT |BR      ,   BB |BL     ,   BR},
		{    BR    , BB|BR|BL ,   BL|BB|BT    , HB|BB|BT    ,   HB|BB|BR ,   BL |BT      ,      BR          ,   BL|BR       ,   BL|BT      ,   0},
		{BL| BB    , BT       ,   BT          , HE|BT|BB    ,   BT|BB    ,   BR          ,      BL|BR|HP    ,   BL|BR       ,   BL|BR      ,   BL|BR},
		{BL| BT|BR , BL|BR    ,   HE|BL|BB    , BT|BB       ,   BT|BB    ,   BR  |SS     ,   BL|BR|HP       ,   BL|HE|BR    ,   BL|BR      ,   BL|BR},
		{BL| BR    , BL|BR    ,   BL|HB|BT    , BB | BT     ,   BB|BT    ,   BR          ,  BL|BR|HP        ,   BL|BR       ,   BL|BR      ,   BL|BR},
		{BL| BR    , BL|SS       ,   BT|BB|HE    , BT | BB     ,   HB |BB|BT   ,   BB          ,   BB|BR          ,  BL| HB |BB   ,   BB |BR     ,   BL|   BR},
		{BL| BB    ,   BB |SM    ,   BT|BB       , BT|BB       , BT         ,  BT| BB       ,   BT|BB          ,   BT|BB       ,  BT| BB      ,   BR|BB   },
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
					superPointsTable[ind][0] = x;
					superPointsTable[ind][1] = y;
					ind = (ind + 1) % NB_SUPERPOINTS;
				}
			}			
		}
		
		
		/*
		 * Bonuses table definition
		 */
		ind = 0;
		for (int x = 0 ; x < MAP_WIDTH ; x++)
		{
			for (int y = 0 ; y < MAP_WIDTH ; y++)
			{
				if ((map[y][x] & hasSpecialSmall ) != 0)
				{
					specialSmallTable[ind][0] = x;
					specialSmallTable[ind][1] = y;
					ind = (ind + 1) % NB_SPECIAL_SMALL;
				}
			}			
		}
		
		/*
		 * Bonuses table definition
		 */
		ind = 0;
		for (int x = 0 ; x < MAP_WIDTH ; x++)
		{
			for (int y = 0 ; y < MAP_WIDTH ; y++)
			{
				if ((map[y][x] & hasSpecialMedium ) != 0)
				{
					specialMediumTable[ind][0] = x;
					specialMediumTable[ind][1] = y;
					ind = (ind + 1) % NB_SPECIAL_MEDIUM;
				}
			}			
		}
		
		/*
		 * Bonuses table definition
		 */
		ind = 0;
		for (int x = 0 ; x < MAP_WIDTH ; x++)
		{
			for (int y = 0 ; y < MAP_WIDTH ; y++)
			{
				if ((map[y][x] & hasSpecialBig ) != 0)
				{
					specialBigTable[ind][0] = x;
					specialBigTable[ind][1] = y;
					ind = (ind + 1) % NB_SPECIAL_BIG;
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

	public int getNbSuperPoints()
	{
		return NB_SUPERPOINTS;
	}

	public int[] getSuperPointPosition(int index)
	{
		return superPointsTable[index];
	}

	public int[] getHeroPosition()
	{
		return new int[]{3,3};
	}
	
	public int getUnvulnerableCounter ()
	{
		return 50;
	}
	public int getNbSpecialSmall ()
	{
		return NB_SPECIAL_SMALL;
	}
	
	public int[] getSpecialSmallPosition (int index)
	{
		return specialSmallTable[index];
	}
	
	public int getNbSpecialMedium ()
	{
		return NB_SPECIAL_MEDIUM;
	}
	
	public int[] getSpecialMediumPosition (int index)
	{
		return specialMediumTable[index];
	}
	
	public int getNbSpecialBig ()
	{
		return NB_SPECIAL_BIG;
	}
	
	public int[] getSpecialBigPosition (int index)
	{
		return specialBigTable[index];
	}
}
