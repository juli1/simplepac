package org.gunnm.pacman.view;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

public class BasicSkin implements Skin
{
	public final static String TAG = "Resources";
	private Bitmap pacmanFull;
	private Bitmap ennemyLeft1;
	private Bitmap ennemyLeft2;
	private Bitmap ennemyUp1;
	private Bitmap ennemyUp2;
	private Bitmap ennemyDown1;
	private Bitmap ennemyDown2;
	private Bitmap ennemyRight1;
	private Bitmap ennemyRight2;
	private Bitmap point;
	private Bitmap bonus1;
	private Bitmap bonus2;
	private int partSize;
	
	public BasicSkin (AssetManager manager, int ps)
	{
		partSize = ps;
		this.loadResources(manager);
		
	}
	
	private Bitmap scaleImage(Bitmap bitmap, int newWidth) 
	{
		Bitmap newBitmap;
		
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		float scaleWidth = ((float) newWidth) / width;
		float ratio = ((float) bitmap.getWidth()) / newWidth;
		int newHeight = (int) (height / ratio);
		float scaleHeight = ((float) newHeight) / height;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		return newBitmap;
	}
	
	
	public void loadResources (AssetManager manager)
	{
		int entitySize = partSize - 5;
		try 
		{
			pacmanFull 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-full.png")), entitySize);
			bonus1 			= scaleImage (BitmapFactory.decodeStream(manager.open("bonus1.png")), entitySize);
			bonus2 			= scaleImage (BitmapFactory.decodeStream(manager.open("bonus2.png")), entitySize);
			ennemyLeft1 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-left1.png")), entitySize);
			ennemyLeft2 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-left2.png")), entitySize);
			ennemyUp1 		= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-up1.png")), entitySize);
			ennemyUp2 		= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-up2.png")), entitySize);
			ennemyRight1 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-right1.png")), entitySize);
			ennemyRight2 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-right2.png")), entitySize);
			ennemyDown1 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-down1.png")), entitySize);
			ennemyDown2 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-down2.png")), entitySize);
			point 			= scaleImage (BitmapFactory.decodeStream(manager.open("point.png")), entitySize);

		}
		catch(IOException ex)
		{
			Log.e(TAG, "Exception: " + ex.toString());
		}
	}
	
	
	public Bitmap getPacmanFull ()
	{
		return this.pacmanFull;
	}
	public Bitmap getEnnemyLeft1 ()
	{
		return this.ennemyLeft1;
	}
	
	public Bitmap getEnnemyLeft2 ()
	{
		return this.ennemyLeft2;
	}
	
	public Bitmap getEnnemyUp1 ()
	{
		return this.ennemyUp1;
	}
	
	public Bitmap getEnnemyUp2 ()
	{
		return this.ennemyUp2;
	}
	
	public Bitmap getEnnemyDown1 ()
	{
		return this.ennemyDown1;
	}
	
	public Bitmap getEnnemyDown2 ()
	{
		return this.ennemyDown2;
	}
	public Bitmap getEnnemyRight1 ()
	{
		return this.ennemyRight1;
	}
	
	public Bitmap getEnnemyRight2 ()
	{
		return this.ennemyRight2;
	}
	
	public Bitmap getBonus1 ()
	{
		return this.bonus1;
	}
	
	public Bitmap getBonus2()
	{
		return this.bonus2;
	}
	public Bitmap getPoint()
	{
		return this.point;
	}
	
	
}
