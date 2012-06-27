package org.gunnm.pacman.model;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class Game {
	private Map map;
	private Hero hero;
	private static final String TAG = "Game";
	private List<Ennemy> ennemies;
	private int heroDefaultX;
	private int heroDefaultY;
	private int unvulnerableCounter;
	private int unvulnerableCounterConstant;
	public static final int DEFAULT_UNVULNERABLE_COUNTER = 100;
	
	public Game ()
	{
		map = new Map ();
		hero = new Hero ();
		ennemies = new ArrayList<Ennemy>();
		this.initDefaultValues();
		unvulnerableCounter = 0;
		unvulnerableCounterConstant = DEFAULT_UNVULNERABLE_COUNTER;
	}
	
	
	public List<Ennemy> getEnnemies()
	{
		return this.ennemies;
	}
	
	public void initDefaultValues ()
	{
		map.initDefaultBorders();
		map.initDefaultsPoints();
		heroDefaultX = Map.MAP_WIDTH / 2;
		heroDefaultY = Map.MAP_HEIGHT / 2;
		hero.setPositionX (heroDefaultX);
		hero.setPositionY (heroDefaultY);
		unvulnerableCounterConstant = DEFAULT_UNVULNERABLE_COUNTER;
		for (int i = 0 ; i < 10 ; i++)
		{
			this.addEnnemy();
		}
		
	}
	
	public Map getMap()
	{
		return this.map;
	}

	public Hero getHero()
	{
		return this.hero;
	}
	
	public Ennemy addEnnemy ()
	{
		int x;
		int y;
		Ennemy e = null;
		
		while (e == null)
		{
			x = (int) (Math.random() * 100) % Map.MAP_WIDTH;
			y = (int) (Math.random() * 100) % Map.MAP_HEIGHT;
			e = addEnnemy (x,y);
		}
		return e;
		
	}
	
	public Ennemy addEnnemy (int i, int j)
	{
		Ennemy newEnnemy;
		
		if ((hero.getPositionX() == i) && (hero.getPositionY() == j))
		{
			return null;
		}
		for (Ennemy e : ennemies)
		{
			if ( (e.getPositionX() == i) && (e.getPositionY() == j))
			{
				Log.i(TAG, "Ennemy already exists at this location ("+i+","+j+")");
				return null;
			}
		}
		newEnnemy = new Ennemy();
		newEnnemy.setPositionX(i);
		newEnnemy.setPositionY(j);
		ennemies.add(newEnnemy);
		return newEnnemy;
	}
	
	
	
	public void reactionEntity(Entity entity)
	{
		if ( (entity.getPositionX() < 0) || (entity.getPositionX() > Map.MAP_WIDTH))
		{
			return;
		}
		
		if ( (entity.getPositionY() < 0) || (entity.getPositionY() > Map.MAP_HEIGHT))
		{
			return;
		}
		
		MapPart part = map.getPart(hero.getPositionX(), hero.getPositionY());
		switch (entity.getDirection())
		{
			case Entity.DIRECTION_DOWN:
			{
				if (! part.hasBorderBottom())
				{
					entity.setPositionY(entity.getPositionY() + 1);
					Log.i(TAG, "Moving entity down");
				}
				break;
			}
			case Entity.DIRECTION_UP:
			{
				if (! part.hasBorderTop())
				{
					entity.setPositionY(entity.getPositionY() - 1);
					Log.i(TAG, "Moving entity top");

				}
				break;
			}
			case Entity.DIRECTION_LEFT:
			{
				if (! part.hasBorderLeft())
				{
					entity.setPositionX(entity.getPositionX() - 1);
					Log.i(TAG, "Moving hero left");
				}
				break;
			}
			case Entity.DIRECTION_RIGHT:
			{
				if (! part.hasBorderRight())
				{
					Log.i(TAG, "Moving hero right");
					entity.setPositionX(entity.getPositionX() + 1);
				}
				break;
			}
		}
	}
	 
	public void heroCollision (Ennemy e)
	{
		if (hero.isVulnerable())
		{
			hero.removeLife ();
			if (hero.isAlive())
			{
				hero.setPositionX(heroDefaultX);
				hero.setPositionY(heroDefaultY);
				hero.setDirection(Entity.DIRECTION_NONE);
			}
		}
		else
		{
			e.died ();
			hero.addPoints (20);
		}
	}
	
	public void reaction()
	{
		reactionEntity(hero);
		
		for (Ennemy e : ennemies)
		{
			reactionEntity (e);
		}
		
		for (Ennemy e : ennemies)
		{
			if ((e.getPositionX() == hero.getPositionX()) &&
				(e.getPositionY() == hero.getPositionY()))
			{
				heroCollision (e);
			}
		}
		
		if (map.getPart(hero.getPositionX(), hero.getPositionY()).hasPoint())
		{
			map.getPart(hero.getPositionX(), hero.getPositionY()).disablePoint();
			hero.addPoint ();
		}
		
		if (map.getPart(hero.getPositionX(), hero.getPositionY()).hasSuperPoint())
		{
			map.getPart(hero.getPositionX(), hero.getPositionY()).disableSuperPoint();
			hero.setUnVulnerable();
			hero.addPoints (10);
			unvulnerableCounter = unvulnerableCounterConstant;
		}
		
		
		if (unvulnerableCounter > 0)
		{
			unvulnerableCounter = unvulnerableCounter - 1;
		}
		else
		{
			hero.setVulnerable();
		}
		
	}
	
}
