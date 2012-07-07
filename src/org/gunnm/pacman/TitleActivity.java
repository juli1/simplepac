package org.gunnm.pacman;

import org.gunnm.pacman.maps.Map2;
import org.gunnm.pacman.model.Game;
import org.gunnm.pacman.model.Scores;
import org.gunnm.pacman.view.BasicSkin;
import org.gunnm.pacman.view.Skin;
import org.gunnm.pacman.view.Sound;
import org.gunnm.pacman.view.TitleScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

public class TitleActivity extends Activity {
	
	TitleScreen 			titleCanvas;
	Game   			    	gameModel;
	public static Skin		skin;
	Sound					sound;
	private Scores			scores;
	private final static boolean  debug = false;
	
	public void onCreate(Bundle savedInstanceState)
	{
		int size;
		int squareSize;
		
        super.onCreate(savedInstanceState);
        
        
        
    	scores = Scores.getInstance (getApplicationContext());
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
		gameModel 		= new Game ();
		squareSize 		= size / gameModel.getMap().getWidth();
		skin 			= BasicSkin.getInstance (this.getResources().getAssets(), squareSize, display.getWidth(), display.getHeight());
		sound      		= Sound.getInstance (this, gameModel, skin);
   
		if (debug)   
        {  
        	Intent intent = new Intent(this, org.gunnm.pacman.PacmanActivity.class);
        	startActivity(intent);
        }
        else
        { 
        	titleCanvas = new TitleScreen (this, skin, display);
        	//title.setOnTouchListener(new);
        	sound.playIntro();
        	setContentView(titleCanvas);
        }
    }
}