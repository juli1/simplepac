package org.gunnm.simplepac.configuration;

import org.gunnm.simplepac.maps.MapDemo;

public class Demo implements  SimplePacConfiguration {

	private final static Class[] mapClasses = {MapDemo.class};
	
	public  int getNbMaps()
	{
		return 1;
	}
	
	public  Class[] getMapClasses ()
	{
		
		return mapClasses;
	}
	
	public  boolean isDemo ()
	{
		return true;
	}
}
