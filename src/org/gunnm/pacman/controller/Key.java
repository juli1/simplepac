package org.gunnm.pacman.controller;

import org.gunnm.pacman.model.Entity;
import org.gunnm.pacman.model.Game;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnKeyListener;

public class Key implements OnKeyListener
{
	private Game model;
	private static final String TAG = "Key";
	
	public Key (Game m)
	{
		this.model = m;
	}


	public boolean onKey(View v, int keyCode, KeyEvent event) 
	{
		if (event.getAction() == KeyEvent.ACTION_DOWN)
		{
			if (event.getKeyCode()  == KeyEvent.KEYCODE_DPAD_LEFT)
			{
				model.getHero().setDirection(Entity.DIRECTION_LEFT);
				Log.i(TAG, "Moving left");
			}
			
			if (event.getKeyCode()  == KeyEvent.KEYCODE_DPAD_RIGHT)
			{
				model.getHero().setDirection(Entity.DIRECTION_RIGHT);
				Log.i(TAG, "Moving right");
			}

			if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP)
			{
				model.getHero().setDirection(Entity.DIRECTION_UP);
				Log.i(TAG, "Moving up");
			}
			
			if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN)
			{
				model.getHero().setDirection(Entity.DIRECTION_DOWN);
				Log.i(TAG, "Moving down");
			}
		}
		return true;
	}
}
