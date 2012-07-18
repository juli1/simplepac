package org.gunnm.simplepac;

import java.util.Timer;
import java.util.TimerTask;

import org.gunnm.simplepac.R;
import org.gunnm.simplepac.R.id;
import org.gunnm.simplepac.R.layout;
import org.gunnm.simplepac.controller.Key;
import org.gunnm.simplepac.controller.Touch;
import org.gunnm.simplepac.model.Game;
import org.gunnm.simplepac.view.BasicSkin;
import org.gunnm.simplepac.view.BitmapView;
import org.gunnm.simplepac.view.GameCanvas;
import org.gunnm.simplepac.view.Skin;
import org.gunnm.simplepac.view.SkinInterface;
import org.gunnm.simplepac.view.Sound;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PacmanActivity extends Activity {
	
	GameCanvas 	mainCanvas;
	Game       	gameModel;
	Touch	  	touchController;
	Timer		mainLoopTimer;
	Skin		skin;
	Sound		sound;
	int			currentRotation;
	
	private final static int 	UPDATE_INTERVAL = 60;
	private final static String TAG = "PacmanActivity";
	private Timer 				autoUpdate;
	
	private void stopTimer ()
	{
		this.autoUpdate.cancel();
		this.autoUpdate.purge ();
		
	}

	private void startTimer ()
	{
		autoUpdate = new Timer ();
		autoUpdate.schedule(new TimerTask() 
		{

			public void run() 
			{
				runOnUiThread(new Runnable() 
				{
					public void run() 
					{
						gameModel.reaction();
						sound.reaction();
						TextView tv;
						tv = (TextView) findViewById (R.id.textScore);
						if (tv != null)
						{
							tv.setText("" + gameModel.getHero().getScore());
						}
						tv = (TextView) findViewById (R.id.textLifes);
						if (tv != null)
						{
							tv.setText("" + gameModel.getHero().getLifes());
						}
						mainCanvas.invalidate();
					
					}
				});
			}
	    }, 0, UPDATE_INTERVAL);
	}
	

	  protected void onPause()
	  {
		  super.onPause();
		  stopTimer ();
	  }

	  protected void onResume ()
	  {
		  super.onResume();
		  startTimer();
	  }

	public void onCreate(Bundle savedInstanceState)
	{
		int size;
		int newSize;
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		currentRotation = display.getOrientation();
		autoUpdate = new Timer();
		
		if (display.getHeight() < display.getWidth())
		{
			size = display.getHeight();
		}
		else
		{
			size = display.getWidth();
		}
		gameModel = Game.getInstance ();
		
		if (gameModel.isFinished())
		{
			gameModel.reinit();
		}
		skin = BasicSkin.getInstance();

		if (display.getWidth() > display.getHeight())
		{
			skin.setOrientation(SkinInterface.ORIENTATION_LANDSCAPE);
		}
		else
		{
			skin.setOrientation(SkinInterface.ORIENTATION_PORTRAIT);
		}
		
        mainCanvas = new GameCanvas (this, gameModel, skin);
        sound      = Sound.getInstance();
 
        mainCanvas.setOnTouchListener(new Touch(gameModel, mainCanvas));
             
        mainCanvas.setOnKeyListener(new Key ());
        
        if (gameModel.getHero().getScore() == 0)
        {
        	sound.playIntro();
        }
        
        mainCanvas.setFocusable(true);

        setContentView(R.layout.main);
        LinearLayout fl = (LinearLayout) findViewById (R.id.GamePlace);
        if (fl != null)
        {
        	LayoutParams lp;
        	int w;
        	w = (display.getWidth() - skin.getLogo().getWidth()) / 2;
        	lp = new LayoutParams(display.getWidth(), skin.getLogo().getHeight());
        	
        	fl.addView (new BitmapView (this, skin.getLogo(), w, 0), 0, lp);
        	lp = new LayoutParams(size + 2, size + 2);
        	
        	lp.height = lp.FILL_PARENT;
        	lp.width = lp.FILL_PARENT;
        	fl.addView(mainCanvas, 2, lp);
        	
        	View v = findViewById(R.layout.panel);
        	if (v != null)
        	{
        		fl.addView(v, 2);
        	}
        }
    }
}