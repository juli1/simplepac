package org.gunnm.pacman;

import java.util.Timer;
import java.util.TimerTask;

import org.gunnm.pacman.controller.Key;
import org.gunnm.pacman.controller.Touch;
import org.gunnm.pacman.maps.Map1;
import org.gunnm.pacman.model.Game;
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
	
	public void onCreate(Bundle savedInstanceState)
	{
		int size;
		int squareSize;
		
        super.onCreate(savedInstanceState);
        
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		if (display.getHeight() < display.getWidth())
		{
			size = display.getHeight();
		}
		else
		{
			size = display.getWidth();
		}
		gameModel = new Game (new Map1());
		squareSize = size / gameModel.getMap().getWidth();
		skin = BasicSkin.getInstance();

        
        mainCanvas = new GameCanvas (this, gameModel, skin);
        sound      = Sound.getInstance();
        
       // setContentView(mainCanvas);
        mainCanvas.setOnTouchListener(new Touch(gameModel, mainCanvas));
        mainCanvas.setOnKeyListener(new Key (gameModel));
        Timer autoUpdate = new Timer(); 
        sound.playIntro();
        autoUpdate.schedule(new TimerTask() {
         public void run() {
          runOnUiThread(new Runnable() {
           public void run() {
            gameModel.reaction();
            sound.reaction();
            TextView tv;
            tv = (TextView) findViewById (R.id.textScore);
            tv.setText("" + gameModel.getHero().getScore());
            tv = (TextView) findViewById (R.id.textLifes);
            tv.setText("" + gameModel.getHero().getLifes());
            mainCanvas.invalidate();
           }
          });
         }
        }, 0, 60); // updates each 40 msec
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
        	
        	fl.addView (new BitmapView (this, skin.getLogo(), w, 0), 1, lp);
        	lp = new LayoutParams(size + 2, size + 2);
        	fl.addView(mainCanvas, 2, lp);
         	Log.e("Main", "main canvas added");
        }
    }
}