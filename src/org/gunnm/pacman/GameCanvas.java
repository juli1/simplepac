package org.gunnm.pacman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class GameCanvas extends View
{
	private static String FLAG = "GameCanvas";
	private int size;
	
	public GameCanvas (Context context)
	{
		super (context);
		setMinimumHeight(100);
		setMinimumWidth(100);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
	
		if (display.getHeight() < display.getWidth())
		{
			size = display.getHeight();
		}
		else
		{
			size = display.getWidth();
		}
	}
	
	public void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
	{
		
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
		
	}
	
	public void draw (Canvas canvas)
	{
		Paint color;
		int i;
		int j;
		int squareSize;
		
		squareSize = size / 11;
		for (i = 0 ; i < 10 ; i++)
		{
			for (j = 0 ; j < 10 ; j++)
			{
				color = new Paint ();
				color.setColor(Color.RED);
				canvas.drawRect(i * squareSize, j * squareSize, (i + 1) * squareSize, (j + 1) * squareSize, color);
			}
		}
		
		canvas.save();
	}
}
