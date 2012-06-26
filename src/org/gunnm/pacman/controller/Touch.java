package org.gunnm.pacman.controller;

import org.gunnm.pacman.model.Entity;
import org.gunnm.pacman.model.Game;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class Touch implements OnTouchListener {

	private View relatedView;
	private Game model;
	private int screenWidth;
	private int screenHeight;
	private static final String TAG = "Touch";
	private float previousX;
	private float previousY;
	
	public Touch (Game m, View v)
	{
		this.model = m;
		this.relatedView = v;
		WindowManager wm = (WindowManager) v.getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		screenWidth = display.getWidth();
		screenHeight = display.getHeight();
		previousX = 0;
		previousY = 0;
	}


	public boolean onTouch(View v, MotionEvent event) 
	{
		float currentX;
		float currentY;
		float deltaX;
		float deltaY;
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			previousX = event.getX();
			previousY = event.getY();
		}
		
		if (event.getAction() == MotionEvent.ACTION_UP)
		{
			
			
			currentX = event.getX();
			
			currentY = event.getY();
			
			Log.i(TAG, "Touch Event, previous coord ("+previousX+ "," + previousY+") current coord ("+currentX+ ","+currentY+")");
			
			deltaX = currentX - previousX;
			deltaY = currentY - previousY;
			if (Math.abs (deltaX) > screenWidth / 3)
			{
				if (previousX < currentX)
				{
					Log.i(TAG, "Moving right");
					model.getHero().setDirection(Entity.DIRECTION_RIGHT);
				}
				else
				{
					Log.i(TAG, "Moving left");
					model.getHero().setDirection(Entity.DIRECTION_LEFT);
				}
			}
			
			if (Math.abs (deltaY) > screenHeight / 3)
			{
				if (previousY < currentY)
				{
					Log.i(TAG, "Moving down");

					model.getHero().setDirection(Entity.DIRECTION_DOWN);
					
				}
				else
				{
					Log.i(TAG, "Moving up");
					model.getHero().setDirection(Entity.DIRECTION_UP);
				}
			}
		}

		v.invalidate();

		return true;
	}
}
