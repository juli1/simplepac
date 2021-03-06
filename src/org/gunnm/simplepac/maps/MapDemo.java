package org.gunnm.simplepac.maps;

import org.gunnm.simplepac.model.MapInterface;

public class MapDemo implements MapInterface
{
	private final static int NB_ENNEMIES 			= 5;
	private final static int MAP_WIDTH 				= 10;
	private final static int MAP_HEIGHT 			= 11;
	private final static int NB_SUPERPOINTS  		= 6;
	private final static int NB_SPECIAL_SMALL  		= 0;
	private final static int NB_SPECIAL_MEDIUM  	= 0;
	private final static int NB_SPECIAL_BIG  		= 0;
	
	
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
	int[][] map = new int[][]
	{ 
		{BT| BL |HP        , BT|BB|HP        ,   BT|HP          , BT|BB|HP           ,      BB|HP       ,   BT|HP |BR         ,    BL|BT  |HP       ,   BT|BB|HP       ,    BT  |BB |HP  ,   BT|BR|HP      },
		{BL| BR |HP        ,BL|BR|BT|BB   ,   BL|BR       , BL  | BT | BB   ,  BB | BT | BR ,  BL|HP           ,   BL |BR         , BL|BT|BB      ,    BT|BR|BB  ,   BL|  BR    },
		{BL |HP            , BB|BT        ,  0 |HP           , BT |HP               ,   BB |BT|HP         ,   BB|HP          ,    BB            ,   BT|HE          ,   BB|BT|HP      ,   BR  |HP       },
		{BL |BB |HP            , BT|BR|HP           ,   BL|BR       , BL |BR          ,   BL |BT         ,   BB|BT       ,    HB|BR|BT|BB      ,   BL  |BB        ,   BT|BR      ,   BL| BR|HP     },
		{ BB |BT |BR    , BL|HP           ,   BB|BR       , BL|BB           ,   0|HP           ,   BT |BB |HP     ,      BT|BB       ,   BT |BR      ,   BB |BL     ,   BR  |HP       },
		{ BT|BB |HP        , BB |HP|BR           ,   BL|BT       , HB|BB|BT        ,   HB|BB|BR    ,   BL |BT|HP      ,      BR |BT         ,   BL|BR       ,   BL|BT      ,   0|HP         },
		{BL| BT |HP         , BT|BB|HP        ,   0  |HP         , HE|BT|BB        ,   BT|BB       ,   BR|HP          ,      BL|BR|HP    ,   BL|BR       ,   BL|BR      ,   BL|BR |HP     },
		{BL| BR |HP        , BL|BR|HP|BT        ,   HE|BL|BB    , BT|BB           ,   BT|BB       ,   BR|HP          ,   BL|BR|HP       ,   BL|HE|BR    ,   BL|BR      ,   BL|BR|HP      },
		{BL| BR |HP        , BL|BR|HP        ,   BL|HB|BT |BB   , BB | BT         ,   BB|BT |HP      ,   BR|HP          ,  BL|BR|HP        ,   BL|BR       ,   BL|BR      ,   BL|BR|HP      },
		{BL| BR |HP        , BL |HP         ,   BT|BB|HE    , BT | BB |HP        ,   HB |BB  |BT    ,   BB|HP          ,   BB|BR|HP          ,  BL| HB |BB   ,   BB |BR|HP     ,   BL|BR|HP   },
		{BL| BB |HP        , BB |HP         ,   BT|BB|HP       , BT|BB|HP           , BT |HP           ,  BT| BB|HP       ,   BT|BB|HP          ,   BT|BB|HP       ,  BT| BB|HP      ,   BR|BB|HP      },
	};
	
	
	
	
	
	
	public MapDemo()
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
