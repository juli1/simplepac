package org.gunnm.simplepac.model;

import org.gunnm.simplepac.configuration.FullGame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.scoreloop.client.android.core.model.Continuation;
import com.scoreloop.client.android.ui.OnScoreSubmitObserver;
import com.scoreloop.client.android.ui.ScoreloopManagerSingleton;
import com.scoreloop.client.android.ui.ShowResultOverlayActivity;


public class Scores implements OnScoreSubmitObserver
{

	    private static Scores 		instance 			= null;
		public final static int    	SHOW_RESULT 		= 2;
		public final static int    	POST_SCORE 			= 3;
		private int 				scoreSubmitStatus;
		private Activity 			currentActivity; 
		private boolean				scoreLoopInitialized = false;
	    public Scores ()
	    {
	    	super ();
	    	this.scoreLoopInitialized = false;
	    	this.currentActivity = null;
	    }
	    
	    public static Scores getInstance ()
	    {
	    	if (instance == null)
	    	{
	    		instance = new Scores ();
	    	}
	    	
	    	instance.init ();
	    	return Scores.instance;
	    }
	    
	    public static Scores getInstance (Activity a)
	    {
	    	if (instance == null)
	    	{
	    		instance = new Scores ();
	    	}
	    	
	    	instance.setCurrentActivity(a);
	    	instance.init ();
	    	return Scores.instance;
	    }
	    
	    public void init ()
	    {
	    	if (this.currentActivity == null)
	    	{
	    		return;
	    	}
	    
	    	
	    	
	    	if (this.scoreLoopInitialized == false)
	    	{
	    		try
	    		{
	    			ScoreloopManagerSingleton.init(currentActivity, FullGame.scoreLoopSecret);
	    			ScoreloopManagerSingleton.get().setOnScoreSubmitObserver(this);
	    			this.scoreLoopInitialized = true;
	    		}
	    		catch (IllegalStateException ise)
	    		{
	    			
	    		}
	    	}
	    }

	    public void registerScore (int score)
	    {
	    	Double result;
	    	if (this.useScoreLoop())
	    	{
	    		result = new Double (score);
	    		ScoreloopManagerSingleton.get().onGamePlayEnded(result, null);
	    	}
	    }
	    
	    public void setCurrentActivity (Activity a)
	    {
	    	this.currentActivity = a;
	    }



	    public void onScoreSubmit(final int status, final Exception error)
	    {
	    	this.scoreSubmitStatus = status;
	    	if (this.useScoreLoop())
	    	{
	    		checkTermsOfService();
	    		currentActivity.startActivityForResult(new Intent(currentActivity, ShowResultOverlayActivity.class), SHOW_RESULT);
	    	}
	    }
	    
	    
	    public int getScoreSubmitStatus ()
	    {
	    	return this.scoreSubmitStatus;
	    }
	    
	    private boolean useScoreLoop ()
	    {
	    	final SharedPreferences preferences;
		    boolean useScoreloop;

	    	if (this.currentActivity == null)
	    	{
	    		return false;
	    	}
	    	
			preferences = PreferenceManager.getDefaultSharedPreferences(currentActivity);
		    	
		    useScoreloop = preferences.getBoolean("use_scoreloop", false);
		    return useScoreloop;
	    }
	    
	    public void checkTermsOfService ()
	    {
	    	final SharedPreferences preferences;
		    boolean useScoreloop;
		    
	    	if (currentActivity == null)
	    	{
	    		return;
	    	}
	    	
			preferences = PreferenceManager.getDefaultSharedPreferences(currentActivity);
		    	
		    useScoreloop = preferences.getBoolean("use_scoreloop", false);
		    if (useScoreloop)
		    {

		    	ScoreloopManagerSingleton.get().askUserToAcceptTermsOfService( currentActivity, new Continuation<Boolean>() {
		    		public void withValue(final Boolean value, final Exception error) {
		    			if (value != null && value) {
		    				preferences.edit().putBoolean("use_scoreloop", true);
		    			}
		    			else
		    			{
		    				preferences.edit().putBoolean("use_scoreloop", false);

		    			}
		    		}
		    	});
		    }
		    else
		    {
		    	preferences.edit().putBoolean("use_scoreloop", false);
		    }
	    }
}
