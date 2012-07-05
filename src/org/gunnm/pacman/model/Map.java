package org.gunnm.pacman.model;

import android.util.Log;

public class Map {
	public static final int DEFAULT_MAP_WIDTH = 10;
	public static final int DEFAULT_MAP_HEIGHT = 11;
	private MapPart[][] parts;
	private int nbPoints;
	private int width;
	private int height;
	
	private final String TAG = "Map";
	
	public Map ()
	{
		this (DEFAULT_MAP_WIDTH, DEFAULT_MAP_HEIGHT);
	}
	
	public Map (int w, int h)
	{
		this.nbPoints = 0;
		this.width = w;
		this.height = h;
		parts = new MapPart[this.width][this.height];
		for (int i = 0 ; i < this.width ; i++)
		{
			for (int j = 0 ; j < this.height ; j++)
			{
				parts[i][j] = new MapPart ();
			}
		}	
	}

	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public void initDefaultBorders ()
	{
		for (int i = 0 ; i < getWidth() ; i++)
		{
			parts[i][0].setBorderTop (true);
			parts[i][getHeight() - 1].setBorderBottom (true);
		}

		for (int j = 0 ; j < getHeight() ; j++)
		{
			parts[0][j].setBorderLeft (true);
			parts[getWidth() - 1][j].setBorderRight (true);
		}
		
		for (int i = getWidth() / 3  ; i < 2 * (getWidth() / 3) ; i++)
		{
			parts[i][getHeight() / 3].setBorderTop (true);
			parts[i][2 * getHeight() / 3].setBorderBottom (true);
		}

		for (int i = getHeight() / 3  ; i < 2*(getHeight() / 3) ; i++)
		{
			parts[getWidth() / 3][i].setBorderLeft (true);
			parts[2 * (getWidth() / 3)][i].setBorderRight (true);
		}
	}
	
	public void initDefaultsPoints ()
	{
		int x;
		int y;
		int[] tmp;
		
		for (int i = 0 ; i < getWidth() ; i++)
		{
			for (int j = 0 ; j < getHeight() ; j++)
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
	
	
	public void enablePoint (int x, int y)
	{
		parts[x][y].enablePoint();
		this.nbPoints = this.nbPoints + 1;
	}
	
	public int getNbPoints ()
	{
		return this.nbPoints;
	}
	
	
	public void enableSuperPoint (int x, int y)
	{
		parts[x][y].enableSuperPoint();
	}
	
	public void enableSpecialSmall (int x, int y)
	{
		parts[x][y].enableSpecialSmall();
	}
	

	public void enableSpecialMedium (int x, int y)
	{
		parts[x][y].enableSpecialMedium();
	}
	
	public void enableSpecialBig (int x, int y)
	{
		parts[x][y].enableSpecialBig();
	}
	
	public int[] getRandomLocation ()
	{
		int [] ret = new int[2];
		ret[0] = (int) (Math.random() * 100) % getWidth();
		ret[1] = (int) (Math.random() * 100) % getHeight();
		return ret;
	}
	
	
	public MapPart getPart (int i, int j)
	{
		if (i > getWidth())
		{
			Log.e (TAG, "Incorrect size in getPart, i=" + i);
			return null;
		}
		
		if (j > getHeight())
		{
			Log.e (TAG, "Incorrect size in getPart, j=" + j);
			return null;
		}
		return parts[i][j];
	}
	
	public void setBorderTop (int x, int y, boolean v)
	{
		parts[x][y].setBorderTop (v);
	}
	
	public void setBorderLeft (int x, int y, boolean v)
	{
		parts[x][y].setBorderLeft (v);
	}
	public void setBorderRight (int x, int y, boolean v)
	{
		parts[x][y].setBorderRight (v);
	}
	
	public void setBorderBottom (int x, int y, boolean v)
	{
		parts[x][y].setBorderBottom(v);
	}
	
}
