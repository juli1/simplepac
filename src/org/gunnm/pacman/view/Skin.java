package org.gunnm.pacman.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;

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
