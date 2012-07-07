package org.gunnm.pacman.model;

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

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.util.Log;


public class Scores extends SQLiteOpenHelper {

		private final static String TAG = "PacmanDb";
	
	   	private static final int DATABASE_VERSION = 3;
	   	private static final String DATABASE_NAME = "pacman_scores";
	    private static final String TABLE_NAME = "scores";
	    private static final String TABLE_CREATE =
	                "CREATE TABLE " + TABLE_NAME + " (" +
	                "score " + " INTEGER, " +
	                "player " + " TEXT, " +
	                "record_date  " + " TEXT);";
	    private SharedPreferences preferences;
	    private static Scores instance = null;
	  
	    public static Scores getInstance (Context context)
	    {
	    	if (Scores.instance == null)
	    	{
	    		Scores.instance = new Scores (context);
	    	}
	    	return Scores.getInstance ();
	    }
	    
	    public static Scores getInstance ()
	    {
	    	return Scores.instance;
	    }
	    
	    public Scores(Context context) 
	    {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
	    }

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
	    
	    public void registerScore (int score)
	    {
	    	String username;
    		username = preferences.getString ("username", "UnnamedPlayer");
	    	String date;
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
	    	sendScore(score, date); 

	    }

    	private boolean sendScore(int score, String date) 
    	{
    		String username;
    		username = preferences.getString ("username", "Unnamed Player");
    		
    
    		Log.i (TAG, "sending score " + score  + " from " + username + " on " + date);
    		try {
    			HttpClient httpclient = new DefaultHttpClient();
        		
        		HttpPost httppost = new HttpPost("http://games.gunnm.org/cgi/simplepac-post.pl");
    			List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair> (3);

    		
    			nameValuePairs.add(new BasicNameValuePair("score", ""+score));
    			nameValuePairs.add(new BasicNameValuePair("date", date));
    			nameValuePairs.add(new BasicNameValuePair("user", username));
    			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

    			httpclient.execute(httppost);


    		} catch (ClientProtocolException e) {
    			Log.i(TAG, "Protocol exception "+ e.toString());
    			return false;
    		} catch (IOException e) {
    			Log.i(TAG, "IO exception "+ e.toString());
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
    		Log.i(TAG , "nb scores" + count);
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
		    		Log.i(TAG , "score exception " + e.toString() + " dateStr = " + dateStr + "toparse=" + cursor.getString(2));
		    		dateString = "Unknown date";
		    	}
		   		
		    	scores[i] = cursor.getInt (0) + " by " + cursor.getString(1) + " on "+ dateString;
		    	Log.i(TAG , "score " + i + " value" + scores[i]);
	    		cursor.moveToPrevious();
	    	}
	    	cursor.close();
	    	return scores;
		}
				
}
