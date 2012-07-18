package org.gunnm.simplepac;

import org.gunnm.simplepac.configuration.Demo;
import org.gunnm.simplepac.model.Game;
import org.gunnm.simplepac.model.Scores;
import org.gunnm.simplepac.view.BasicSkin;
import org.gunnm.simplepac.view.GameCanvas;
import org.gunnm.simplepac.view.Skin;
import org.gunnm.simplepac.view.Sound;
import org.gunnm.simplepac.view.TitleScreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.WindowManager;

public class TitleActivity extends Activity {
	
	TitleScreen 			titleCanvas;
	protected Game	    	gameModel;
	public static Skin		skin;
	Sound					sound;
	private Scores			scores;
	private final static boolean  debug = false;
	private ProgressDialog 	loadingResources;

	 AlertDialog.Builder builder;
	 
	 public void loadGameModel()
	 {
		 gameModel 		= new Game (new Demo());
	 }
	 
	 private Handler handler = new Handler()
	 {
         public void handleMessage(Message msg)
         {
        	 if (loadingResources != null)
        	 {
        		 loadingResources.dismiss();
        		 loadingResources = null;
        	 }
         }
	 };
	 
	public void onStop()
	{
		super.onStop();
		loadingResources = null;
	}
	 
	public void onCreate(Bundle savedInstanceState)
	{

        super.onCreate(savedInstanceState);
        
        builder = new AlertDialog.Builder(this);
        loadingResources = new ProgressDialog(this);
        loadingResources.setMessage("Loading Resources ...");
        loadingResources.setTitle("Please wait ...");
        loadingResources.setCancelable(true);
		
        
		 Thread thread = new Thread(new Runnable ()
		 {
			 public void run()
			 {
				try
				{
					Thread.sleep(2000);
				}
				catch (InterruptedException e) 
				{
				}
				 handler.sendEmptyMessage(0);
			 }
		 });

		 thread.start();
        
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
    	scores = Scores.getInstance (getApplicationContext());
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		loadGameModel ();
		skin 			= BasicSkin.getInstance (this.getResources().getAssets(), display.getWidth(), display.getHeight(), gameModel);
		sound      		= Sound.getInstance (this, gameModel, skin);

        loadingResources.show();
        
        
		if (debug)   
        {  
        	Intent intent = new Intent(this, org.gunnm.simplepac.PacmanActivity.class);
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