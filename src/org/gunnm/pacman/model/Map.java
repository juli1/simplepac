package org.gunnm.pacman.model;

import android.util.Log;

public class Map {
	public static final int MAP_WIDTH = 10;
	public static final int MAP_HEIGHT = 11;
	private MapPart[][] parts;
	
	private final String TAG = "Map";
	
	public Map ()
	{
		parts = new MapPart[MAP_WIDTH][MAP_HEIGHT];
		for (int i = 0 ; i < MAP_WIDTH ; i++)
		{
			for (int j = 0 ; j < MAP_HEIGHT ; j++)
			{
				parts[i][j] = new MapPart ();
			}
		}
	}
	
	public void initDefaultBorders ()
	{
		for (int i = 0 ; i < MAP_WIDTH ; i++)
		{
			parts[i][0].setBorderTop (true);
			parts[i][MAP_HEIGHT - 1].setBorderBottom (true);
		}

		for (int j = 0 ; j < MAP_HEIGHT ; j++)
		{
			parts[0][j].setBorderLeft (true);
			parts[MAP_WIDTH - 1][j].setBorderRight (true);
		}
		
		for (int i = MAP_WIDTH / 3  ; i < 2 * (MAP_WIDTH / 3) ; i++)
		{
			parts[i][MAP_HEIGHT / 3].setBorderTop (true);
			parts[i][2 * MAP_HEIGHT / 3].setBorderBottom (true);
		}

		for (int i = MAP_HEIGHT / 3  ; i < 2*(MAP_HEIGHT / 3) ; i++)
		{
			parts[MAP_WIDTH / 3][i].setBorderLeft (true);
			parts[2 * (MAP_WIDTH / 3)][i].setBorderRight (true);
		}
	}
	
	public void initDefaultsPoints ()
	{
		int x;
		int y;
		int[] tmp;
		
		for (int i = 0 ; i < MAP_WIDTH ; i++)
		{
			for (int j = 0 ; j < MAP_HEIGHT ; j++)
			{
				parts[i][j].enablePoint();
			}
		}
		
		for (int i = 0 ; i < 10 ; i++)
		{
			tmp = getRandomLocation();
			x = tmp[0];
			y = tmp[1];
			
			while (parts[x][y].hasSuperPoint())
			{
				tmp = getRandomLocation();
				x = tmp[0];
				y = tmp[1];
					
			}
			Log.e (TAG, "Put super point at (" + x + "," + y + ")");
			parts[x][y].enableSuperPoint();
		}
	}
	
	
	public int[] getRandomLocation ()
	{
		int [] ret = new int[2];
		ret[0] = (int) (Math.random() * 100) % Map.MAP_WIDTH;
		ret[1] = (int) (Math.random() * 100) % Map.MAP_HEIGHT;
		return ret;
	}
	
	
	public MapPart getPart (int i, int j)
	{
		if (i > MAP_WIDTH)
		{
			Log.e (TAG, "Incorrect size in getPart, i=" + i);
			return null;
		}
		
		if (j > MAP_HEIGHT)
		{
			Log.e (TAG, "Incorrect size in getPart, j=" + j);
			return null;
		}
		return parts[i][j];
	}
	
}
