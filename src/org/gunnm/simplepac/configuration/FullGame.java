package org.gunnm.simplepac.configuration;

import java.nio.MappedByteBuffer;

import org.gunnm.simplepac.maps.Map1;
import org.gunnm.simplepac.maps.Map2;
import org.gunnm.simplepac.maps.Map3;
import org.gunnm.simplepac.maps.MapDemo;

public class FullGame implements  SimplePacConfiguration {

	public final static boolean isDemo = false;
	public final static int NB_MAPS = 3;
	private final static Class[] mapClasses = {Map1.class, Map2.class,Map3.class};

	public  int getNbMaps()
	{
		return NB_MAPS;
	}
	
	public  Class[] getMapClasses ()
	{	
		return mapClasses;
	}
	
	public  boolean isDemo ()
	{
		return false;
	} 
}
