package org.gunnm.simplepac.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class BitmapView extends View{
	Bitmap bitmap = null;
	private int alignX;
	private int alignY;
	private Paint defaultPaint;
	
	public BitmapView(Context context, Bitmap bm) 
	{
		this (context, bm, 0, 0);
		defaultPaint = new Paint();
		defaultPaint.setFilterBitmap(true);

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
	
		// The image will be scaled so it will fill the width, and the
		// height will preserve the image’s aspect ration

		canvas.drawBitmap(bitmap, alignX, alignY, null);
	}
}
