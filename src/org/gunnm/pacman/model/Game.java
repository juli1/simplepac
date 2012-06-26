package org.gunnm.pacman.model;

public class Game {
	private Map map;
	
	public Game ()
	{
		map = new Map ();
		map.initDefaultBorders();
	}
	
	public Map getMap()
	{
		return this.map;
	}
}
