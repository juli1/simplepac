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

import com.scoreloop.client.android.ui.ScoreloopManagerSingleton;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;


public class Scores  {

	    private static Scores instance = null;
	  
	    public Scores ()
	    {
	    	super ();
	    }
	    
	    public static Scores getInstance ()
	    {
	    	if (instance == null)
	    	{
	    		instance = new Scores ();
	    	}
	    	return Scores.instance;
	    }
	    

/*
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(TABLE_CREATE);
	        this.insertDefaultValues(db);
	    }
	   
	    
	    private void insertDefaultValues (SQLiteDatabase db)
	    {
	    	ContentValues scoreValues = new ContentValues();
	    	int nbDefaultScores = 10;
	    	int[] defaultScores = { 0 , 1, 2, 3, 4, 5,  6, 7, 8, 9};
	    	String[] defaultScoresNames = { "Jane", "Ali Baba", "Kyle", "Henry", "Jack", "Eric",
	    			                     "John", "Jamie", "Stan", "Diana"};
	    	String[] defaultScoresDates = { "12-09-1975-00-00", "02-06-1977-00-00", "02-10-1960-00-00", "12-09-2009-00-00", "21-08-1990-00-00", "23-10-1995-00-00",
                    "11-03-1979-00-00", "01-01-1980-00-00", "00-00-0000-00-00", "Unknown"};
	    	
	    	for (int i = 0 ; i < nbDefaultScores ; i++)
	    	{
	    		scoreValues.put ("score", defaultScores[i]);
	    		scoreValues.put ("player", defaultScoresNames[i]);
	    		scoreValues.put ("record_date", defaultScoresDates[i]);
	    	
	    		if (db.insert (TABLE_NAME, null, scoreValues) == -1)
	    		{
	    			//Log.e (TAG, "ERROR WHEN INSERT");
	    		}
	    		else
	    		{
	    			//Log.i (TAG, "INSERT SUCCESFUL");
	    		}
	    	}
	    }
	    */ 
	    public static void registerScore (int score)
	    {
	    	Double result = new Double (score);
	    	ScoreloopManagerSingleton.get().onGamePlayEnded(result, null);
	    	return;
	    	/*
	    	String username;
	    	String date;
    		
	    	if (Game.isDemo())
    		{
    			return;
    		}
	    	
    		username = preferences.getString ("username", "UnnamedPlayer");
	    	
	    	Calendar c = Calendar.getInstance();
	    	date = c.get(Calendar.YEAR) + "-" +
	    		   c.get(Calendar.MONTH) + "-" +
	    		   c.get(Calendar.DAY_OF_MONTH) + "-" +
	    		   c.get(Calendar.HOUR) + "-" +
	    		   c.get(Calendar.MINUTE);
	    	ContentValues scoreValues = new ContentValues();
	    	scoreValues.put ("score", score);
	    	scoreValues.put ("player", username);
	    	scoreValues.put ("record_date", date);
	    	
	    	SQLiteDatabase db = this.getWritableDatabase ();
	    	
	    	if (db.insert (TABLE_NAME, null, scoreValues) == -1)
	    	{
	    		//Log.e (TAG, "ERROR WHEN INSERT");
	    	}
	    	else
	    	{
	    		//Log.i (TAG, "INSERT SUCCESFUL");
	    	}
	    	// Put a timeout for establishing a connection to the remote server
	    	sendScore(score, date, 5000); */

	    }
/*
	    private boolean sendScore(int score, String date)
	    {
	    	return this.sendScore(score, date, 0);
	    }
	    
    	private boolean sendScore(int score, String date, int timeout) 
    	{
    		String username;
    		HttpClient httpclient;
    		HttpParams httpParameters;
    		
    		if (Game.isDemo())
    		{
    			return false;
    		}
    		
    		username = preferences.getString ("username", "Unnamed Player");
    		    		
    		try 
    		{
    			if (timeout != 0)
    			{
    				httpParameters = new BasicHttpParams();
    				HttpConnectionParams.setConnectionTimeout(httpParameters, timeout);
    				HttpConnectionParams.setSoTimeout(httpParameters, timeout);
    				httpclient = new DefaultHttpClient(httpParameters);
    			}
    			else
    			{
    				httpclient = new DefaultHttpClient();
    			}
    			
        		HttpPost httppost = new HttpPost("http://games.gunnm.org/cgi/simplepac-post.pl");
    			List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair> (3);

    		
    			nameValuePairs.add(new BasicNameValuePair("score", ""+score));
    			nameValuePairs.add(new BasicNameValuePair("date", date));
    			nameValuePairs.add(new BasicNameValuePair("user", username));
    			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

    			httpclient.execute(httppost);
    		

    		}
    		catch (Exception e) 
    		{
    			return false;
    		}
    		return true;
    	}
	    
	    
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate (db);
		}
	
		
		public void reset()
		{
			SQLiteDatabase db = this.getWritableDatabase ();
			db.delete (TABLE_NAME, null, null);
			this.insertDefaultValues(db);
		}
		
		public boolean sendAll()
		{
			
			String dateStr;
	    	int score;
	    	int i = 0;
	    	int count;
	    	SQLiteDatabase db = this.getWritableDatabase ();
	    	Cursor cursor = db.query (TABLE_NAME, null, null, null, null, null, null, null);
	    	
	    	
	    	count =  cursor.getCount();
    		cursor.moveToFirst();
	    	for (i = 0 ; i < count ; i++)
	    	{
		    	try 
		    	{
		    		dateStr = cursor.getString(1);
		    		
		    		score   = cursor.getInt (0);
		    		if ( ! sendScore(score, dateStr))
		    		{
		    			return false;
		    		}
		    	}
		    	catch (Exception e)
		    	{
		    		return false;
		    	}
	    		cursor.moveToNext();
	    	}
	    	cursor.close();
	    	return true;
		}
		
		
		public String[] getScores()
		{
			
			String[] dateStr;
			String   dateString;
			Date dateObj;
	    	String[] scores;
	    	int i = 0;
	    	int count;
	    	SQLiteDatabase db = this.getWritableDatabase ();
	    	Cursor cursor = db.query (TABLE_NAME, null, null, null, null, null, "score", null);
	    	
	    	count =  cursor.getCount();
	    	scores = new String[count];
    		cursor.moveToLast();
	    	for (i = 0 ; i < count ; i++)
	    	{
		   		dateStr = cursor.getString(2).split("-");
		   		try
		   		{
			   		dateObj = new Date (Integer.parseInt(dateStr[0]),
			   							Integer.parseInt(dateStr[1]),
			   							Integer.parseInt(dateStr[2]),
			   							Integer.parseInt(dateStr[3]),
			   							Integer.parseInt(dateStr[4]),
			   							0);
			    	dateString = dateObj.toString();
			    	dateString = dateString.substring(0, dateString.lastIndexOf(':'));
		   		}
		    	catch (Exception e)
		    	{
		    		dateString = "Unknown date";
		    	}
		   		
		    	scores[i] = cursor.getInt (0) + " by " + cursor.getString(1) + " on "+ dateString;
	    		cursor.moveToPrevious();
	    	}
	    	cursor.close();
	    	return scores;
		}
			*/	
}
