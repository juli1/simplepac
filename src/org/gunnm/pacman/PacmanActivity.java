package org.gunnm.pacman;

import java.util.Timer;
import java.util.TimerTask;

import org.gunnm.pacman.controller.Key;
import org.gunnm.pacman.controller.Touch;
import org.gunnm.pacman.maps.Map1;
import org.gunnm.pacman.model.Game;
import org.gunnm.pacman.model.Scores;
import org.gunnm.pacman.view.BasicSkin;
import org.gunnm.pacman.view.BitmapView;
import org.gunnm.pacman.view.GameCanvas;
import org.gunnm.pacman.view.Skin;
import org.gunnm.pacman.view.Sound;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
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
	
	  public void onResume()
	  {
		  super.onResume();
		  startTimer();
	  }

	  protected void onPause()
	  {
		  super.onPause();
		  stopTimer ();
	  }
	
	
	public void onCreate(Bundle savedInstanceState)
	{
		int size;
		int squareSize;
		
        super.onCreate(savedInstanceState);
        
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
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
		squareSize = size / gameModel.getMap().getWidth();
		skin = BasicSkin.getInstance();

        
        mainCanvas = new GameCanvas (this, gameModel, skin);
        sound      = Sound.getInstance();

       // setContentView(mainCanvas);
        mainCanvas.setOnTouchListener(new Touch(gameModel, mainCanvas));
        mainCanvas.setOnKeyListener(new Key (gameModel));
        sound.playIntro();
        //startTimer ();
        mainCanvas.setFocusable(true);
        //ArrayList<View> views = new ArrayList<View>();
        //views.add(mainCanvas);

        setContentView(R.layout.main);
        LinearLayout fl = (LinearLayout) findViewById (R.id.GamePlace);
        if (fl == null)
        {
        	Log.e("Main", "null linearlayout");
        }
        else
        {
        	LayoutParams lp;
        	int w;
        	w = (size - skin.getLogo().getWidth()) / 2;
        	lp = new LayoutParams(size, skin.getLogo().getHeight());
        	
        	fl.addView (new BitmapView (this, skin.getLogo(), w, 0), 0, lp);
        	lp = new LayoutParams(size + 2, size + 2);
        	fl.addView(mainCanvas, 2, lp);
        	
        	View v = findViewById(R.layout.panel);
        	if (v == null)
        	{
        		Log.i(TAG, "Cannot find the view");
        	}
        	else
        	{
        		Log.i(TAG, "Other view added");

        		fl.addView(v, 2);
        	}
         	Log.e(TAG, "main canvas added");
        }
    }
}