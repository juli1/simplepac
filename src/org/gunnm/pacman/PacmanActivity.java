package org.gunnm.pacman;

import java.util.Timer;
import java.util.TimerTask;

import org.gunnm.pacman.controller.Key;
import org.gunnm.pacman.controller.Touch;
import org.gunnm.pacman.maps.Map1;
import org.gunnm.pacman.model.Game;
import org.gunnm.pacman.view.BasicSkin;
import org.gunnm.pacman.view.GameCanvas;
import org.gunnm.pacman.view.Skin;
import org.gunnm.pacman.view.Sound;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

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
		skin = new BasicSkin (this.getResources().getAssets(), squareSize);

        
        mainCanvas = new GameCanvas (this, gameModel, skin);
        sound      = new Sound (this, gameModel, skin);
        
        setContentView(mainCanvas);
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
            mainCanvas.invalidate();
           }
          });
         }
        }, 0, 60); // updates each 40 msec
        mainCanvas.setFocusable(true);
        //setContentView(R.layout.main);
    }
}