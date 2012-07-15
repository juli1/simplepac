package org.gunnm.simplepac.controller;

import org.gunnm.simplepac.model.Entity;
import org.gunnm.simplepac.model.Game;
import org.gunnm.simplepac.view.GameCanvas;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class Touch implements OnTouchListener {

	private Game model;
	private int screenWidth;
	private int screenHeight;
	private static final String TAG = "Touch";
	private float previousX;
	private float previousY;
	
	public Touch (Game m, View v)
	{
		this.model = m;
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
		float heroPositionX;
		float heroPositionY;
		boolean hasMove;
		int partSize = GameCanvas.computeSquareSize(screenWidth, screenHeight, model);
		heroPositionX = ((partSize) * (Game.getInstance().getHero().getPositionX()  ))  +  
						(partSize / 2);
		heroPositionY = (partSize * (Game.getInstance().getHero().getPositionY() )) +  
						(partSize / 2) ;
		
		if (GameCanvas.getInstance() != null)
		{
			heroPositionX = heroPositionX + GameCanvas.getInstance().getAlignX();
			heroPositionY = heroPositionY + GameCanvas.getInstance().getAlignY();
		}
		
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			previousX = event.getX();
			previousY = event.getY();
		}
		
		if (event.getAction() == MotionEvent.ACTION_UP)
		{
			
			
			currentX = event.getX();
			
			currentY = event.getY();
			
			deltaX = currentX - previousX;
			deltaY = currentY - previousY;
			if (Math.abs (deltaX) > screenWidth / 3)
			{
				if (previousX < currentX)
				{
					model.getHero().setDirection(Entity.DIRECTION_RIGHT);
				}
				else
				{
					model.getHero().setDirection(Entity.DIRECTION_LEFT);	
				}
				return true;
			}
			
			if (Math.abs (deltaY) > screenHeight / 3)
			{
				if (previousY < currentY)
				{
					model.getHero().setDirection(Entity.DIRECTION_DOWN);	
				}
				else
				{
					model.getHero().setDirection(Entity.DIRECTION_UP);
				}
				return true;
			}
			
			//Log.i(TAG,  "heroPositionX= " + heroPositionX + "heroPositionY=" + heroPositionY + "currentX=" + currentX + "currentY=" + currentY + "vleft=" + v.getLeft() + "vtop=" + v.getTop() + "alignX=" + GameCanvas.getInstance().getAlignX() + "alignY=" + GameCanvas.getInstance().getAlignY() + "screenwidth=" + screenWidth + ";screenheight=" + screenHeight);
			
			deltaX = Math.abs(heroPositionX - currentX);
			deltaY = Math.abs(heroPositionY - currentY);
			
			if (deltaY > deltaX)
			{
				hasMove = tryMoveY(heroPositionY, currentY);
				if ( ! hasMove )
				{
					tryMoveX (heroPositionX,currentX);
				}
			}
			else
			{
				hasMove = tryMoveX (heroPositionX,currentX);
				if ( ! hasMove )
				{
					tryMoveY(heroPositionY, currentY);
				}
			}
		}
		return true;
	}
	
	private boolean tryMoveX (float heroPositionX, float currentX)
	{
		if ( (currentX > heroPositionX)
				&& model.canTakeDirection(model.getHero(), Entity.DIRECTION_RIGHT))
		{
			model.getHero().setDirection(Entity.DIRECTION_RIGHT);
			return true;
		}
		
		if ( (currentX < heroPositionX)
				&& model.canTakeDirection(model.getHero(), Entity.DIRECTION_LEFT))
		{
			model.getHero().setDirection(Entity.DIRECTION_LEFT);
			return true;
		}
		return false;
	}
	
	private boolean tryMoveY (float heroPositionY, float currentY)
	{
		if ( (currentY < heroPositionY) 
				&& model.canTakeDirection(model.getHero(), Entity.DIRECTION_UP))
		{
			model.getHero().setDirection(Entity.DIRECTION_UP);
			return true;
		}
		if ( (currentY > heroPositionY) &&
			 model.canTakeDirection(model.getHero(), Entity.DIRECTION_DOWN))
		{
			model.getHero().setDirection(Entity.DIRECTION_DOWN);
			return true;
		}
		return false;
	}
}
