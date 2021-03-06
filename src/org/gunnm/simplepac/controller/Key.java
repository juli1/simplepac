package org.gunnm.simplepac.controller;

import org.gunnm.simplepac.model.Entity;
import org.gunnm.simplepac.model.Game;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

public class Key implements OnKeyListener
{
	private static final String TAG = "Key";
	
	public Key ()
	{
	}


	public boolean onKey(View v, int keyCode, KeyEvent event) 
	{
		Game model = Game.getInstance();
		if (event.getAction() == KeyEvent.ACTION_DOWN)
		{
			if (event.getKeyCode()  == KeyEvent.KEYCODE_DPAD_LEFT)
			{
				model.getHero().setDirection(Entity.DIRECTION_LEFT);
			}
			 
			if (event.getKeyCode()  == KeyEvent.KEYCODE_DPAD_RIGHT)
			{
				model.getHero().setDirection(Entity.DIRECTION_RIGHT);
			}

			if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP)
			{
				model.getHero().setDirection(Entity.DIRECTION_UP);
			}
			
			if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN)
			{
				model.getHero().setDirection(Entity.DIRECTION_DOWN);
			}
		}
		return true;
	}
}
