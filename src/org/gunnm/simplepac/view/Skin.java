package org.gunnm.simplepac.view;

import android.content.res.AssetManager;

public abstract class Skin implements SkinInterface {
	protected static Skin instance = null;
	
	public static Skin getInstance ()
	{
		return instance;
	}
	
	public static Skin getInstance (AssetManager c, int s)
	{ 
		return null;
	}
}
