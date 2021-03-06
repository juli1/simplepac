package org.gunnm.simplepac;

import org.gunnm.simplepac.configuration.Demo;
import org.gunnm.simplepac.configuration.FullGame;
import org.gunnm.simplepac.model.Game;
import org.gunnm.simplepac.model.Scores;
import org.gunnm.simplepac.view.BasicSkin;
import org.gunnm.simplepac.view.GameCanvas;
import org.gunnm.simplepac.view.Skin;
import org.gunnm.simplepac.view.Sound;
import org.gunnm.simplepac.view.TitleScreen;

import com.scoreloop.client.android.core.model.Continuation;
import com.scoreloop.client.android.ui.ScoreloopManagerSingleton;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.WindowManager;

public class TitleActivity extends Activity {
	
	TitleScreen 			titleCanvas;
	protected Game	    	gameModel;
	public static Skin		skin;
	Sound					sound;
	private Scores			scores;
	private final static boolean  debug = false;
	private ProgressDialog 	loadingResources = null;
	private	static TitleActivity instance;
	
	public static TitleActivity getInstance ()
	{
		return instance;
	}
	
	
	public TitleActivity ()
	{
		
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
	 
	 
	 public void onResume ()
	 {
		super.onResume();
		scores = Scores.getInstance(this);
		scores.checkTermsOfService();
		
	 }
	 
	public void onStop()
	{
		super.onStop();
		loadingResources = null;
	}
	 
	public void onCreate(Bundle savedInstanceState)
	{
		AlertDialog.Builder alertDialogBuilder;
		AlertDialog 		alertDialog;
        super.onCreate(savedInstanceState);
              
        instance = this;
        
        loadingResources 	= new ProgressDialog(this);
        loadingResources.setMessage("Loading Resources ...");
        loadingResources.setTitle("Please wait ...");
        loadingResources.setCancelable(true);
        
        alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Loading error");

        alertDialogBuilder
        .setMessage("Error when loading resources")
        .setPositiveButton("Close",new DialogInterface.OnClickListener() 
        {
        	public void onClick(DialogInterface dialog,int id) 
        	{
        		TitleActivity.instance.finish();
        	}
        });
 
		alertDialog = alertDialogBuilder.create();
				
				
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
        
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		try
		{
			gameModel       = Game.getInstance();
			skin 			= BasicSkin.getInstance (this.getResources().getAssets(), display.getWidth(), display.getHeight(), gameModel);
			sound      		= Sound.getInstance (this, gameModel, skin);
		}
		catch (Exception e)
		{
			alertDialog.show();
			return;
		}
		
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
