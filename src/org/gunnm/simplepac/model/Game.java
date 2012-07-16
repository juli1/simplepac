package org.gunnm.simplepac.model;

import java.util.ArrayList;
import java.util.List;

import org.gunnm.simplepac.maps.*;

public class Game {
	private Map map;
	private Hero hero;
	private static final String TAG = "Game";
	private List<Ennemy> ennemies;
	private int heroDefaultX;
	private int heroDefaultY;
	private int dyingCounter;
	private int unvulnerableCounter;
	private int unvulnerableCounterConstant;
	private volatile int currentAction;
	private int pointsEaten;
	
	
	public static final int POINTS_SUPERPOINT 		= 10;
	public static final int POINTS_SPECIAL_SMALL 	= 20;
	public static final int POINTS_SPECIAL_MEDIUM 	= 50;
	public static final int POINTS_SPECIAL_BIG    	= 100;

	public static final int DEFAULT_UNVULNERABLE_COUNTER = 100;
	public static final int MAX_ENNEMIES = 100;
	public static final int COUNTER_NEXT_LEVEL = 60;
	public static final int INTERNAL_STEP_THRESHOLD = 100;
	public static final int INTERNAL_STEP_VALUE = 25;
	public static final int ACTION_NONE = 0;
	public static final int ACTION_EAT = 1;
	public static final int ACTION_BONUS = 2;
	public static final int ACTION_UNVULNERABLE = 3;
	public static final int ACTION_FINISHED = 4;
	public static final int ACTION_DYING = 5;
	private static Game instance;
	private int currentMapIndex;
	
	
	public final static boolean isDemo = false;
	public final static int NB_MAPS = 2;
	private final static Class[] mapClasses = {Map1.class,Map2.class};
	
	/*
	public final static boolean isDemo = true;
	
	public final static int NB_MAPS = 1;
	private final static Class[] mapClasses = {MapDemo.class};
	*/
	
	public void reinit ()
	{
		currentMapIndex 	= 0;
		pointsEaten 		= 0;
		currentAction 		= ACTION_NONE;
		
		hero.reinit();
		for (int i = 0 ; i < ennemies.size() ; i++)
		{
			ennemies.remove(0);
		}
		initMap (currentMapIndex);
		unvulnerableCounter = 0;
		dyingCounter 		= 0;
		hero.setPositionX (heroDefaultX);
		hero.setPositionY (heroDefaultY);
		hero.isDying (false);
	}
	
	
	public Game ()
	{
		currentMapIndex 	= 0;
		pointsEaten 		= 0;
		currentAction 		= ACTION_NONE;
		
		hero 				= new Hero ();
		ennemies 			= new ArrayList<Ennemy>();
		initMap (currentMapIndex);
		unvulnerableCounter = 0;
		dyingCounter 		= 0;
		hero.setPositionX (heroDefaultX);
		hero.setPositionY (heroDefaultY);
	
		instance = this;
	}
	
	
	
	public static Game getInstance ()
	{
		return instance;
	}
	
	public void initMap (int mapIndex)
	{
		int[] tmp;
		MapInterface custom = null;
		try 
		{
			custom = (MapInterface)mapClasses[mapIndex].newInstance();
		} 
		catch (IllegalAccessException e) 
		{
			//Log.i (TAG, e.toString());
			return;
		} 
		catch (InstantiationException e) 
		{
			//Log.i (TAG, e.toString());
			return;
		}	
		
		map = new Map (custom.getWidth(), custom.getHeight());

		pointsEaten = 0;
		currentAction = ACTION_NONE;
		
		ennemies.removeAll(ennemies);
		unvulnerableCounter = 0;
		dyingCounter = 0;
		hero.setPositionX (heroDefaultX);
		hero.setPositionY (heroDefaultY);
		hero.canMove(true);

		  
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
		
		for (int i = 0 ; i < custom.getNbSuperPoints() ; i++)
		{
			tmp = custom.getSuperPointPosition(i);
			map.enableSuperPoint (tmp[0], tmp[1]);
		}
		
		for (int i = 0 ; i < custom.getNbSpecialSmall() ; i++)
		{
			tmp = custom.getSpecialSmallPosition(i);
			map.enableSpecialSmall (tmp[0], tmp[1]);
		}
		

		for (int i = 0 ; i < custom.getNbSpecialMedium() ; i++)
		{
			tmp = custom.getSpecialMediumPosition(i);
			map.enableSpecialMedium (tmp[0], tmp[1]);
		}		
		

		for (int i = 0 ; i < custom.getNbSpecialBig() ; i++)
		{
			tmp = custom.getSpecialBigPosition(i);
			map.enableSpecialBig (tmp[0], tmp[1]);
		}		
		
		for (int i = 0 ; i < custom.getWidth() ; i++)
		{
			for (int j = 0 ; j < custom.getHeight() ; j++)
			{
				map.setBorderBottom (i,j, custom.hasBorderBottom(i, j));
				map.setBorderLeft (i,j, custom.hasBorderLeft(i, j));
		 		map.setBorderRight (i,j, custom.hasBorderRight(i, j));
				map.setBorderTop (i,j, custom.hasBorderTop(i, j));			
			}
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

	public int[] getFreePosition ()
	{
		int x;
		int y;
		int res[];
		boolean found = false;
		res = new int[2];
		
		while (!found)
		{
			x = (int) (Math.random() * 100) % map.getWidth();
			y = (int) (Math.random() * 100) % map.getHeight();
			res[0] = x;
			res[1] = y;
			found = true;
			if ( (hero.getPositionX() == x) && (hero.getPositionY() == y))
			{
				found = false;
			}
			for (Ennemy e : ennemies)
			{
				if ( (e.getPositionX() == x) && (e.getPositionY() == y))
				{
					found = false;
				}
			}
		}

		return res;
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
//				Log.i(TAG, "Ennemy already exists at this location ("+i+","+j+")");
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
		entity.react();

		
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
				if (entity.getInternalStepValueY() < INTERNAL_STEP_THRESHOLD)
				{
					if (  (! part.hasBorderBottom()) &&
					      (entity.getInternalStepValueX() <= INTERNAL_STEP_VALUE) && 
					      (entity.getInternalStepValueX() >= ((-1 ) * INTERNAL_STEP_VALUE)))
					{
						entity.setInternalStepValueY( entity.getInternalStepValueY() + INTERNAL_STEP_VALUE);
					} 
					
					if ((part.hasBorderBottom()) && (entity.getInternalStepValueY() < 0 ))
					{
						entity.setInternalStepValueY( entity.getInternalStepValueY() + INTERNAL_STEP_VALUE);
					}
					
					if ((part.hasBorderBottom()) && (entity.getInternalStepValueY() < 0  ))
					{
						entity.setInternalStepValueY (0);
					}
					
				}
				else
				{
					if (! part.hasBorderBottom())
					{
						entity.setInternalStepValueX( 0 );
						entity.setInternalStepValueY( (INTERNAL_STEP_THRESHOLD * -1 ) );
						entity.setPositionY((entity.getPositionY() + 1 )% map.getHeight());
					}
				}
				break;
			}
			case Entity.DIRECTION_UP:
			{
				if (entity.getInternalStepValueY() > (INTERNAL_STEP_THRESHOLD * -1))
				{
					
					if ( (! part.hasBorderTop()) && 
						(entity.getInternalStepValueX() <= INTERNAL_STEP_VALUE) &&
						(entity.getInternalStepValueX() >= ( -1 * INTERNAL_STEP_VALUE))
					    )
					{
						entity.setInternalStepValueY( entity.getInternalStepValueY() - INTERNAL_STEP_VALUE);

					}

					if ((part.hasBorderTop()) && (entity.getInternalStepValueY() < 0 ))
					{
						entity.setInternalStepValueY (0);
					}
					
					if ((part.hasBorderTop()) && (entity.getInternalStepValueY() > 0 ))
					{
						entity.setInternalStepValueY( entity.getInternalStepValueY() - INTERNAL_STEP_VALUE);
					}
					
					
					
				
				}
				else
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
						entity.setInternalStepValueX(0);
						entity.setInternalStepValueY( (INTERNAL_STEP_THRESHOLD) );
					}
					
				}
				break;
			}
			case Entity.DIRECTION_LEFT:
			{
				if (entity.getInternalStepValueX() > (INTERNAL_STEP_THRESHOLD * -1))
				{
					
					if ( (! part.hasBorderLeft()) && 
					     (entity.getInternalStepValueY() >= (INTERNAL_STEP_VALUE * -1) ) &&
					     (entity.getInternalStepValueY() <= (INTERNAL_STEP_VALUE ) ))
					{
						entity.setInternalStepValueX (entity.getInternalStepValueX() - INTERNAL_STEP_VALUE);
					}

					if ((part.hasBorderLeft()) && (entity.getInternalStepValueX() <0  ))
					{
						entity.setInternalStepValueX (0);
					}
					
					if ((part.hasBorderLeft()) && (entity.getInternalStepValueX() > 0  ))
					{
						entity.setInternalStepValueX (entity.getInternalStepValueX() - INTERNAL_STEP_VALUE);
					}
					 
				}
				else
				{
					if ( (! part.hasBorderLeft()) && ( (entity.getInternalStepValueY() <= INTERNAL_STEP_VALUE ) &&
													   (entity.getInternalStepValueY() >= (-1 *INTERNAL_STEP_VALUE))))
					{
					 	entity.setInternalStepValueY(0);
						if (entity.getPositionX() == 0)
						{
							entity.setPositionX (map.getWidth() - 1);
						}
						else
						{
							entity.setPositionX (entity.getPositionX() - 1 );
						}
						entity.setInternalStepValueX (INTERNAL_STEP_THRESHOLD);
					}
				}
				break;
			}
			case Entity.DIRECTION_RIGHT:
			{
				if (entity.getInternalStepValueX() < INTERNAL_STEP_THRESHOLD)
				{
					if ((entity.getInternalStepValueY() <= INTERNAL_STEP_VALUE) && 
					    (entity.getInternalStepValueY() >= (-1 * INTERNAL_STEP_VALUE)))
					{
						
						entity.setInternalStepValueY (0);
						if (! part.hasBorderRight() && (entity.getInternalStepValueX() <= INTERNAL_STEP_THRESHOLD))
						{
							entity.setInternalStepValueX (entity.getInternalStepValueX() + INTERNAL_STEP_VALUE);
						}
	
						if ((part.hasBorderRight()) && (entity.getInternalStepValueX() >0  ))
						{
							entity.setInternalStepValueX (0);	
						}
						
						if ((part.hasBorderRight()) && (entity.getInternalStepValueX() < 0  ))
						{
							entity.setInternalStepValueX (entity.getInternalStepValueX() + INTERNAL_STEP_VALUE);
						}
					}
				}
				else
				{
					if (! part.hasBorderRight() )
					{
						if( (entity.getInternalStepValueY() <= 0) &&
		   						(entity.getInternalStepValueY() >= -1 *INTERNAL_STEP_VALUE))
						{
							/*
							if (entity instanceof Hero)
							{
								Log.i(TAG, "Going right, X="+ entity.getPositionX() + "Y="+ entity.getPositionY() + " stepX=" +entity.getInternalStepValueX() + "stepY"   + entity.getInternalStepValueY() );
							}
							*/
							entity.setPositionX( (entity.getPositionX() + 1) % map.getWidth());
							entity.setInternalStepValueX (INTERNAL_STEP_THRESHOLD * -1);
							entity.setInternalStepValueY (0);
						}
					
					}
				}
				break;
			}
		}
	}
	 
	public int getDyingCounter ()
	{
		return this.dyingCounter;
	}
	
	public void heroDying ()
	{
		if (this.dyingCounter == 30)
		{
			hero.isDying (false);
			
			if (hero.getLifes() > 0)
			{
				hero.setPositionX(heroDefaultX);
				hero.setPositionY(heroDefaultY);
				hero.setInternalStepValueX(0);
				hero.setInternalStepValueY(0);
				hero.setDirection(Entity.DIRECTION_NONE);
			}	
		}
		else
		{
			this.dyingCounter++;
		}
		
	}
	
	public void heroCollision (Ennemy e)
	{
		if (hero.isVulnerable() && e.isAlive())
		{
			if ( (e.getPositionX() == this.heroDefaultX) &&
			     (e.getPositionY() == this.heroDefaultY))
			{
				int tmp[] = getFreePosition();
				e.setPosition (tmp);
			}
			this.currentAction = ACTION_DYING;
			hero.removeLife ();
			hero.isDying (true);
			this.dyingCounter = 0;
			
		}
		else
		{
			e.died ();
			hero.addPoints (20);
		}
	}
	
	public synchronized void reaction()
	{
//		Log.i (TAG, "Reaction !" + this.currentAction);

		
		if (this.currentAction == ACTION_FINISHED)
		{
			if (hero.getLifes() == 0)
			{
				if (dyingCounter > 0)
				{
					dyingCounter = dyingCounter - 1;
				}
				return;
			}
			
			if (currentMapIndex < (NB_MAPS - 1 ))
			{
				if (dyingCounter > 0)
				{
					dyingCounter = dyingCounter - 1;
				}
				else
				{
					currentMapIndex = currentMapIndex + 1;
					currentAction = ACTION_NONE;
					
					initMap (currentMapIndex);
					
					unvulnerableCounter = 0;
					dyingCounter = 0;
					hero.setPositionX (heroDefaultX);
					hero.setPositionY (heroDefaultY);
				}
			}
			return;
		}
		
		if ((this.pointsEaten == map.getNbPoints()) &&
			(this.currentAction != ACTION_FINISHED))
		{
//			Log.i (TAG, "Everything eaten !");
			hero.canMove(false);
			this.currentAction = ACTION_FINISHED;
			dyingCounter = COUNTER_NEXT_LEVEL;
			Scores.getInstance().registerScore(hero.getScore());
			return;
		} 
 
		if (hero.getLifes() == 0)
		{
			this.currentAction = ACTION_FINISHED;
//			Log.i (TAG, "No more lifes !");
			hero.canMove(false);
			Scores.getInstance().registerScore(hero.getScore());
			return;
		}
		
		this.currentAction = ACTION_NONE;
		
		if (hero.isDying ())
		{
			this.heroDying();
			return;
		}
		
	

		
		//Log.i(TAG, "BEFORE REACTION, HERO, X="+ hero.getPositionX() + "Y="+ hero.getPositionY() + " stepX=" + hero.getInternalStepValueX() + " stepY="   + hero.getInternalStepValueY() );
		reactionEntity(hero);
		//Log.i(TAG, "AFTER REACTION, HERO, X="+ hero.getPositionX() + "Y="+ hero.getPositionY() + " stepX=" + hero.getInternalStepValueX() + " stepY="   + hero.getInternalStepValueY() );

		
		for (Ennemy e : ennemies)
		{
			if( ! e.isAlive())
			{
				continue;
			}
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
		

		
		if (map.getPart(hero.getPositionX(), hero.getPositionY()).hasPoint())
		{
			map.getPart(hero.getPositionX(), hero.getPositionY()).disablePoint();
			hero.addPoint ();
			this.currentAction = ACTION_EAT;
			this.pointsEaten = this.pointsEaten + 1;
//			Log.i(TAG, "Points eaten="+this.pointsEaten+ " total points=" + map.getNbPoints());
		}
		
		if (map.getPart(hero.getPositionX(), hero.getPositionY()).hasSuperPoint())
		{
			map.getPart(hero.getPositionX(), hero.getPositionY()).disableSuperPoint();
			hero.setUnVulnerable();
			hero.addPoints (POINTS_SUPERPOINT);
			unvulnerableCounter = unvulnerableCounterConstant;
			this.currentAction = ACTION_BONUS;
		}
		
		if (map.getPart(hero.getPositionX(), hero.getPositionY()).hasSpecialBig())
		{
			map.getPart(hero.getPositionX(), hero.getPositionY()).disableSpecialBig();
			hero.addPoints (POINTS_SPECIAL_BIG);
			this.currentAction = ACTION_BONUS;
		}
		
		if (map.getPart(hero.getPositionX(), hero.getPositionY()).hasSpecialMedium())
		{
			map.getPart(hero.getPositionX(), hero.getPositionY()).disableSpecialMedium();
			hero.addPoints (POINTS_SPECIAL_MEDIUM);
			this.currentAction = ACTION_BONUS;
		}
		
		if (map.getPart(hero.getPositionX(), hero.getPositionY()).hasSpecialSmall())
		{
			map.getPart(hero.getPositionX(), hero.getPositionY()).disableSpecialSmall();
			hero.addPoints (POINTS_SPECIAL_SMALL);
			this.currentAction = ACTION_BONUS;
		}
		
		
		if (unvulnerableCounter > 0)
		{
			unvulnerableCounter = unvulnerableCounter - 1;
		}
		else
		{
			hero.setVulnerable();
		}
		
		for (Ennemy e : ennemies)
		{
			if ((e.getPositionX() == hero.getPositionX()) &&
				(e.getPositionY() == hero.getPositionY()))
			{
				heroCollision (e);
			}
		}
	}
	
	public boolean isFinished ()
	{
		
		return ((this.pointsEaten >= map.getNbPoints()) || (hero.getLifes() <= 0));
	}
	
	public int getCurrentAction ()
	{
		return this.currentAction;
	}
	
	public int getCurrentMapIndex ()
	{
		return this.currentMapIndex;
	}
	
	
	public boolean canTakeDirection(Entity e, int direction)
	{
		switch (direction)
		{
			case Entity.DIRECTION_DOWN:
			{
				if (e.getInternalStepValueX() < (INTERNAL_STEP_VALUE * -1) || e.getInternalStepValueX() > (INTERNAL_STEP_VALUE * 1))
				{
					return false;
				}
				return ! map.getPart(e.getPositionX(), e.getPositionY()).hasBorderBottom();
			}
			case Entity.DIRECTION_UP:
			{
				if (e.getInternalStepValueX() < (INTERNAL_STEP_VALUE * -1) || e.getInternalStepValueX() > (INTERNAL_STEP_VALUE * 1))
				{
					return false;
				}
				return ! map.getPart(e.getPositionX(), e.getPositionY()).hasBorderTop();
			}
			case Entity.DIRECTION_LEFT:
			{
				if (e.getInternalStepValueY() < (INTERNAL_STEP_VALUE * -1) || e.getInternalStepValueY() > (INTERNAL_STEP_VALUE * 1))
				{
					return false;
				}
				return ! map.getPart(e.getPositionX(), e.getPositionY()).hasBorderLeft();
			}
			case Entity.DIRECTION_RIGHT:
			{
				if (e.getInternalStepValueY() < (INTERNAL_STEP_VALUE * -1) || e.getInternalStepValueY() > (INTERNAL_STEP_VALUE * 1))
				{
					return false;
				}
				return ! map.getPart(e.getPositionX(), e.getPositionY()).hasBorderRight();
			}
		}
		
		return false;
	}
	
	
}
