package org.gunnm.pacman;

import java.util.Timer;

import org.gunnm.pacman.controller.MainLoop;
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
        mainLoopTimer.scheduleAtFixedRate(new MainLoop(gameModel, mainCanvas), 0, 300);

        //setContentView(R.layout.main);
    }
}