package org.gunnm.pacman;

import java.util.Timer;
import java.util.TimerTask;

import org.gunnm.pacman.controller.Key;
import org.gunnm.pacman.controller.Touch;
import org.gunnm.pacman.maps.Map1;
import org.gunnm.pacman.model.Game;
import org.gunnm.pacman.view.GameCanvas;

import android.app.Activity;
import android.os.Bundle;

public class PacmanActivity extends Activity {
	
	GameCanvas 	mainCanvas;
	Game       	gameModel;
	Touch	  	touchController;
	Timer		mainLoopTimer;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        gameModel = new Game (new Map1());
        mainCanvas = new GameCanvas (this, gameModel);
        
        
        setContentView(mainCanvas);
        mainCanvas.setOnTouchListener(new Touch(gameModel, mainCanvas));
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
        }, 0, 60); // updates each 40 msec
        mainCanvas.setFocusable(true);
        //setContentView(R.layout.main);
    }
}