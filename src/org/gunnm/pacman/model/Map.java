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
