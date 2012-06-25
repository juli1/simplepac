package org.gunnm.pacman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GameCanvas extends View
{
	private static String FLAG = "GameCanvas";
	
	public GameCanvas (Context context)
	{
		super (context);
		setMinimumHeight(160);
		setMinimumWidth(105);
	}
	
	public void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
	{
		setMeasuredDimension(getSuggestedMinimumHeight(), getSuggestedMinimumWidth());
	}
	
	public void draw (Canvas canvas)
	{
		Paint color;
		color = new Paint ();
		color.setColor(Color.RED);
		canvas.drawColor(Color.GREEN);
		canvas.drawText ("Hello", 40, 55, color);
		canvas.drawLine(50, 50, 100,100,color );
		canvas.save();
	}
}
