package org.gunnm.pacman;

import java.util.Timer;
import java.util.TimerTask;

import org.gunnm.pacman.controller.Touch;
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
        gameModel = new Game ();
        mainCanvas.setModel (gameModel);
        
        touchController = new Touch(gameModel, mainCanvas);
        mainLoopTimer = new Timer (true);
        setContentView(mainCanvas);
        mainCanvas.setOnTouchListener(touchController);
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
        //setContentView(R.layout.main);
    }
}