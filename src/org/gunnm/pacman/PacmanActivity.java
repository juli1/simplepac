package org.gunnm.pacman;

import java.util.Timer;
import java.util.TimerTask;

import org.gunnm.pacman.controller.Key;
import org.gunnm.pacman.controller.Touch;
import org.gunnm.pacman.maps.Map1;
import org.gunnm.pacman.model.Game;

import android.app.Activity;
import android.os.Bundle;

public class PacmanActivity extends Activity {
	
	GameCanvas 	mainCanvas;
	Game       	gameModel;
	Touch	  	touchController;
	Timer		mainLoopTimer;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainCanvas = new GameCanvas (this);
        gameModel = new Game (new Map1());
        mainCanvas.setModel (gameModel);
        
        touchController = new Touch(gameModel, mainCanvas);
        mainLoopTimer = new Timer (true);
        setContentView(mainCanvas);
        mainCanvas.setOnTouchListener(touchController);
        mainCanvas.setOnKeyListener(new Key (gameModel));
        Timer autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
         public void run() {
          runOnUiThread(new Runnable() {
           public void run() {
            gameModel.reaction();
            mainCanvas.invalidate();
           }
          });
         }
        }, 0, 40); // updates each 40 msec
        mainCanvas.setFocusable(true);
        //setContentView(R.layout.main);
    }
}