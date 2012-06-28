package org.gunnm.pacman.view;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Resources 
{
	public final static String TAG = "Resources";
	public Bitmap pacmanFull;
	
	public Resources (AssetManager manager)
	{
		this.loadResources(manager);
	}
	
	
	public void loadResources (AssetManager manager)
	{
		try {
			InputStream ims = manager.open("pacman-full.png");

			pacmanFull = BitmapFactory.decodeStream(ims);

		}
		catch(IOException ex)
		{
			Log.e(TAG, "Exception: " + ex.toString());
		}
	}
}
