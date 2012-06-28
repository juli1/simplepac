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
	
	
	public Game (MapInterface customMap)
	{
		map = new Map (customMap.getWidth(), customMap.getHeight());
		hero = new Hero ();
		ennemies = new ArrayList<Ennemy>();
		initMap (customMap);
		unvulnerableCounter = 0;

		hero.setPositionX (heroDefaultX);
		hero.setPositionY (heroDefaultY);
		
		for (int i = 0 ; i < customMap.getWidth() ; i++)
		{
			for (int j = 0 ; j < customMap.getHeight() ; j++)
			{
				map.setBorderBottom (i,j, customMap.hasBorderBottom(i, j));
				map.setBorderLeft (i,j, customMap.hasBorderLeft(i, j));
				map.setBorderRight (i,j, customMap.hasBorderRight(i, j));
				map.setBorderTop (i,j, customMap.hasBorderTop(i, j));			
			}
		}
		
	}
	
	public void initMap (MapInterface custom)
	{
		int[] tmp;
		
		tmp = custom.getHeroPosition();
		
		this.heroDefaultX = tmp[0];
		this.heroDefaultY = tmp[1];
		
		unvulnerableCounterConstant = custom.getUnvulnerableCounter();
		
		for (int i = 0 ; i < custom.getWidth() ; i++)
		{
			for (int j = 0 ; j < custom.getHeight() ; j++)
			{
				if (custom.hasPoint(i, j))
				{
					map.enablePoint (i, j);
				}
			}
		}
		
		for (int i = 0 ; i < custom.getNbEnnemies() ; i++)
		{
			tmp = custom.getEnnemyPosition(i);
			this.addEnnemy(tmp[0], tmp[1]);
		}
		
		for (int i = 0 ; i < custom.getNbBonuses() ; i++)
		{
			tmp = custom.getBonusPosition(i);
			map.enableSuperPoint (tmp[0], tmp[1]);
		}
	}
	
	public List<Ennemy> getEnnemies()
	{
		return this.ennemies;
	}
	
	public void initDefaultValues ()
	{
		map.initDefaultBorders();
		map.initDefaultsPoints();
		heroDefaultX = map.getWidth() / 2;
		heroDefaultY = map.getHeight() / 2;
		
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
			x = (int) (Math.random() * 100) % map.getWidth();
			y = (int) (Math.random() * 100) % map.getHeight();
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
		if ( (entity.getPositionX() < 0) || (entity.getPositionX() > map.getWidth()))
		{
			return;
		}
		
		if ( (entity.getPositionY() < 0) || (entity.getPositionY() > map.getHeight()))
		{
			return;
		}
		
		MapPart part = map.getPart(entity.getPositionX(), entity.getPositionY());
		switch (entity.getDirection())
		{
			case Entity.DIRECTION_DOWN:
			{
				if (! part.hasBorderBottom())
				{
					entity.setPositionY((entity.getPositionY() + 1 )% map.getHeight());

				}
				break;
			}
			case Entity.DIRECTION_UP:
			{
				if (! part.hasBorderTop())
				{
					
					if (entity.getPositionY() == 0)
					{
						entity.setPositionY (map.getHeight() - 1);
					}
					else
					{
						entity.setPositionY (entity.getPositionY() - 1 );
					}
					

				}
				break;
			}
			case Entity.DIRECTION_LEFT:
			{
				if (! part.hasBorderLeft())
				{
					
					if (entity.getPositionX() == 0)
					{
						entity.setPositionX (map.getWidth() - 1);
					}
					else
					{
						entity.setPositionX (entity.getPositionX() - 1 );
					}
				}
				break;
			}
			case Entity.DIRECTION_RIGHT:
			{
				if (! part.hasBorderRight())
				{
					entity.setPositionX( (entity.getPositionX() + 1) % map.getWidth());
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
			if ( ((int)(Math.random() * 100)) % 7 == 0)
			{
				switch ( ((int)(Math.random() * 100) ) % 4)
				{
					case 0:
					{
						e.setDirection(Entity.DIRECTION_DOWN);
						break;
					}
					case 1:
					{
						e.setDirection(Entity.DIRECTION_LEFT);
						break;
					}
					case 2:
					{
						e.setDirection(Entity.DIRECTION_RIGHT);
						break;
					}
					case 3:
					{
						e.setDirection(Entity.DIRECTION_UP);
						break;
					}
				}
			}
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
