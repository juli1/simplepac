package org.gunnm.pacman.model;

import android.util.Log;

public class Game {
	private Map map;
	private Hero hero;
	private static final String TAG = "Game";

	public Game ()
	{
		map = new Map ();
		map.initDefaultBorders();
		hero = new Hero ();
		hero.setPositionX (Map.MAP_WIDTH / 2);
		hero.setPositionY (Map.MAP_HEIGHT / 2);
	}
	
	public Map getMap()
	{
		return this.map;
	}

	public Entity getHero()
	{
		return this.hero;
	}
	
	public void reactionHero()
	{
		if ( (hero.getPositionX() < 0) || (hero.getPositionX() > Map.MAP_WIDTH))
		{
			return;
		}
		
		if ( (hero.getPositionY() < 0) || (hero.getPositionY() > Map.MAP_HEIGHT))
		{
			return;
		}
		
		MapPart heroPart = map.getPart(hero.getPositionX(), hero.getPositionY());
		switch (hero.getDirection())
		{
			case Entity.DIRECTION_DOWN:
			{
				if (! heroPart.hasBorderBottom())
				{
					hero.setPositionY(hero.getPositionY() + 1);
					Log.i(TAG, "Moving hero down");
				}
				break;
			}
			case Entity.DIRECTION_UP:
			{
				if (! heroPart.hasBorderTop())
				{
					hero.setPositionY(hero.getPositionY() - 1);
					Log.i(TAG, "Moving hero top");

				}
				break;
			}
			case Entity.DIRECTION_LEFT:
			{
				if (! heroPart.hasBorderLeft())
				{
					hero.setPositionX(hero.getPositionX() - 1);
					Log.i(TAG, "Moving hero left");
				}
				break;
			}
			case Entity.DIRECTION_RIGHT:
			{
				if (! heroPart.hasBorderRight())
				{
					Log.i(TAG, "Moving hero right");
					hero.setPositionX(hero.getPositionX() + 1);
				}
				break;
			}
		}
	}
	
	public void reaction()
	{
		reactionHero();
	}
	
}
