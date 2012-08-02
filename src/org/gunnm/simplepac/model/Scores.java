package org.gunnm.simplepac.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.scoreloop.client.android.core.model.Continuation;
import com.scoreloop.client.android.ui.OnScoreSubmitObserver;
import com.scoreloop.client.android.ui.PostScoreOverlayActivity;
import com.scoreloop.client.android.ui.ScoreloopManagerSingleton;
import com.scoreloop.client.android.ui.ShowResultOverlayActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.util.Log;


public class Scores implements OnScoreSubmitObserver
{

	    private static Scores 		instance 			= null;
		public final static int    	SHOW_RESULT 		= 2;
		public final static int    	POST_SCORE 			= 3;
		private int 				scoreSubmitStatus;
		private Activity 			gameActivity; 
		
	    public Scores ()
	    {
	    	super ();
	    	this.gameActivity = null;
	    }
	    
	    public static Scores getInstance ()
	    {
	    	if (instance == null)
	    	{
	    		instance = new Scores ();
	    	}
	    	return Scores.instance;
	    }
	    

	    public void registerScore (int score)
	    {
	    	Double result = new Double (score);
	    	ScoreloopManagerSingleton.get().onGamePlayEnded(result, null);
	    	return;
	    }
	    
	    public void setGameActivity (Activity a)
	    {
	    	this.gameActivity = a;
	    }



	    public void onScoreSubmit(final int status, final Exception error) {
	    	SharedPreferences 	preferences;
	    	boolean 			useScoreloop;
	    	
	    	scoreSubmitStatus = status;

	    	if(gameActivity == null)
	    	{
	    		return;
	    	}

	    	preferences = PreferenceManager.getDefaultSharedPreferences(gameActivity);
	    	useScoreloop = preferences.getBoolean("use_scoreloop", false);
	    	
	    	if (useScoreloop)
	    	{
	    		checkTermsOfService();
	    		gameActivity.startActivityForResult(new Intent(gameActivity, ShowResultOverlayActivity.class), SHOW_RESULT);
	    	}
	    }
	    
	    
	    public int getScoreSubmitStatus ()
	    {
	    	return this.scoreSubmitStatus;
	    }
	    
	    public void checkTermsOfService ()
	    {
	    	final SharedPreferences preferences;
		    boolean useScoreloop;
		    
	    	if (gameActivity == null)
	    	{
	    		return;
	    	}
	    	
			preferences = PreferenceManager.getDefaultSharedPreferences(gameActivity);
		    	
		    useScoreloop = preferences.getBoolean("use_scoreloop", false);
		    if (useScoreloop)
		    {

		    	ScoreloopManagerSingleton.get().askUserToAcceptTermsOfService( gameActivity, new Continuation<Boolean>() {
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
