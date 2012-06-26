package org.gunnm.pacman;

import org.gunnm.pacman.controller.Touch;
import org.gunnm.pacman.model.Game;

import android.app.Activity;
import android.os.Bundle;

public class PacmanActivity extends Activity {
	
	GameCanvas 	mainCanvas;
	Game       	gameModel;
	Touch	  	touchController;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainCanvas = new GameCanvas (this);
        gameModel = new Game ();
        mainCanvas.setModel (gameModel);
        
        touchController = new Touch(gameModel, mainCanvas);
        
        setContentView(mainCanvas);
        mainCanvas.setOnTouchListener(touchController);
        //setContentView(R.layout.main);
    }
}