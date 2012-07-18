package org.gunnm.simplepac.maps;

import org.gunnm.simplepac.model.MapInterface;

public class Map3 implements MapInterface
{
	private final static int NB_ENNEMIES 			= 5;
	private final static int MAP_WIDTH 				= 10;
	private final static int MAP_HEIGHT 			= 11;
	private final static int NB_SUPERPOINTS  		= 4;
	private final static int NB_SPECIAL_SMALL  		= 2;
	private final static int NB_SPECIAL_MEDIUM  	= 2;
	private final static int NB_SPECIAL_BIG  		= 3;
	
	
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
	private final static int SB = hasSpecialBig;
	
	int[][] ennemiesTable 		= new int[NB_ENNEMIES][2];
	int[][] superPointsTable 	= new int[NB_SUPERPOINTS][2];
	int[][] specialSmallTable 	= new int[NB_SPECIAL_SMALL][2];
	int[][] specialMediumTable 	= new int[NB_SPECIAL_MEDIUM][2];
	int[][] specialBigTable 	= new int[NB_SPECIAL_BIG][2];
 
 
	int[][] map = new int[][]
	{ 
		{ BT | BL | HP ,  BT | HP           , BT| BB | HP  , BT | BB | HP ,  BB  |HP       ,  BB | BT | HP    ,  BB | BT | HP   , BT | BB | HP  ,  BT | BB | HP       ,  BT | BR | HP  },
		{ BL | BR | HP ,  BL | BB | HP      , BB| BT | HP  , BT | BB | HP ,  BB | BT | HE  ,  BT | BB | SS    ,  BT |  BB | HB  , BT | BB | 0   ,  BT | BR | HP       ,  BL | BR | HP  },
		{ BL | BR | HP ,  BL | BT | BB | SB , BB| BT | HE  , BT | BB | 0  ,  BB | BT | HP  ,  BT      | HP    ,  BT |  BB | HP  , BT | BB | HP  ,  BB | BR | HP       ,  BL | BR | HP  },
		{ BL | HP      ,  BT | BB | HP      , BB| BT | HP  , BT | BB      ,  BB | BT | HP  ,  BB      | HP    ,  BT |  BB | 0   , BT | BB | SM  ,  BT | BR | 0       ,  BL | BR | HP  },
		{ BL | HB      ,  BT | BB | HP      , BT | HP      , BT | BB | HP ,  BB | BT | HP  ,  BT | BB | HP    ,  BT |  BB | HP  , BT | BB | HP  ,  BB | BR | HP       ,  BL | BR | HP  },
		{ BR | HP      ,  BL | BB | BT | HP , BB | HP      , BT | BB | HE ,  BB | BT | SB  ,  BT | BB | HP    ,  BT | HP        , BT | BB | HP  ,  BT | BB | HP       , HP          },
		{ BL | BR | HP ,  BL | BT | BB | HP , BT | HP      , BT | BB | HP ,  BB | BT | HP  ,  BT | BB | 0     ,  BB | HP        , BT | BB | HP  ,  BT | BB | BR | HB  ,  BL | BR | HP  },
		{ BL | BR | HP ,  BL | BT | BB | HP , BB | HP      , BT | HP      ,  BB | BT | HP  ,  BT | BB | 0     ,  BT |  BB | HE  , BT | BB | 0   ,  BT | BB | BR | SB  ,  BL | BR | HP  },
		{ BL | BR | HP ,  BL | BT | BB | SS , BB| BT | HP  , BB | HP      ,  BB | BT | HP  ,  BT | BB | HP    ,  BT |  BB | 0   , BT | BB | HP  ,  BT | BR | 0        ,  BL | BR | HP  },
		{ BL | BR | HP ,  BL | BT | BB| SM  , BB| BT | HE   , BT | BB | 0 ,  BT      | HP  ,  BT | BB | HP    ,  BT |  BB | 0   , BT | BB | 0   ,  BB | BR | HP       ,  BL | BR | HB  },
		{ BL | BB | HP ,  BT | BB | HP      , BB| BT | HP  , BT | BB | HP ,  HP            ,  BT | BB | HP    ,  BT | BB  |HP   , BT | BB | HP  ,  BT | BB | HP       ,  BR | BB | HP  },
	};
	
	
	 
	
	
	
	public Map3()
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
