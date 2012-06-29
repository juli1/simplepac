package org.gunnm.pacman.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class BitmapView extends View{
	Bitmap bitmap = null;
	private int alignX;
	private int alignY;
	
	public BitmapView(Context context, Bitmap bm) 
	{
		this (context, bm, 0, 0);
	}

	public BitmapView(Context context, Bitmap bm, int ha, int va) 
	{
		super(context);
		bitmap = bm;
		alignX = ha;
		alignY = va;
	}
	
	protected void onDraw(Canvas canvas)
	{
	
		Paint paint = new Paint();
		paint.setFilterBitmap(true);
		// The image will be scaled so it will fill the width, and the
		// height will preserve the image’s aspect ration

		canvas.drawBitmap(bitmap, alignX, alignY, null);
	}
}
