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
import org.gunnm.pacman.view.TitleScreen;

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

public class TitleActivity extends Activity {
	
	TitleScreen titleCanvas;
	Game       	gameModel;
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
		gameModel 		= new Game (new Map1());
		squareSize 		= size / gameModel.getMap().getWidth();
		skin 			= BasicSkin.getInstance (this.getResources().getAssets(), squareSize);
		sound      		= Sound.getInstance (this, gameModel, skin);

        titleCanvas = new TitleScreen (this, skin, display);
        
        //title.setOnTouchListener(new);
         
        sound.playIntro();
        
        setContentView(titleCanvas);
    }
}