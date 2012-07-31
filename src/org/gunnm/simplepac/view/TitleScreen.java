package org.gunnm.simplepac.view;

import org.gunnm.simplepac.model.Game;

import com.scoreloop.client.android.core.model.Continuation;
import com.scoreloop.client.android.ui.EntryScreenActivity;
import com.scoreloop.client.android.ui.ScoreloopManagerSingleton;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TitleScreen extends View implements OnTouchListener
{
	private Skin skin;
	private Display display;
	private final static String TAG = "TitleScreen";
	int logoAlignX;
	int logoAlignY;
	int newGameAlignX;
	int newGameAlignY;
	int highScoresAlignX;
	int highScoresAlignY;
	int preferencesAlignX;
	int preferencesAlignY;
	int copyrightAlignX;
	int copyrightAlignY;
	int instructionsAlignX;
	int instructionsAlignY;
	private Activity context;
	private int margin = 0;
	AlertDialog.Builder builder;
	
	
	
	public TitleScreen (Activity c, Skin s, Display d)
	{ 
		super (c);
		this.context = c;
		skin = s;
		display = d;
		
		this.setOnTouchListener(this);
		builder = new AlertDialog.Builder(c);
	}
	
	
	protected void onDraw(Canvas canvas)
	{
		margin = (display.getHeight() 
					- skin.getLogo().getHeight() 
					- skin.getNewGame().getHeight()
					- skin.getHighScores().getHeight()
					- skin.getPreferences().getHeight()
					- skin.getCopyright().getHeight()) / 5;
		logoAlignX = (display.getWidth() - skin.getLogo().getWidth()) / 2;
		logoAlignY = margin / 3;
		
		newGameAlignX = (display.getWidth() - skin.getNewGame().getWidth()) / 2;
		newGameAlignY = logoAlignY + margin + skin.getLogo().getHeight();
		
		highScoresAlignX = (display.getWidth() - skin.getHighScores().getWidth()) / 2;
		highScoresAlignY = newGameAlignY + margin + skin.getNewGame().getHeight();
		
		preferencesAlignX = (display.getWidth() - skin.getPreferences().getWidth()) / 2;
		preferencesAlignY = highScoresAlignY + margin + skin.getHighScores().getHeight();
		
		copyrightAlignX = display.getWidth() - skin.getCopyright().getWidth();
		copyrightAlignY = preferencesAlignY + skin.getPreferences().getHeight() + margin;

		instructionsAlignX = 0;
		instructionsAlignY = preferencesAlignY + skin.getPreferences().getHeight() + margin;
		 
		canvas.drawBitmap(skin.getLogo(), logoAlignX, logoAlignY, null);
		canvas.drawBitmap(skin.getNewGame(), newGameAlignX, newGameAlignY, null);
		canvas.drawBitmap(skin.getLeaderBoard(), highScoresAlignX, highScoresAlignY, null);
		canvas.drawBitmap(skin.getPreferences(), preferencesAlignX, preferencesAlignY, null);
		canvas.drawBitmap(skin.getCopyright(), copyrightAlignX, copyrightAlignY, null);
		canvas.drawBitmap(skin.getInstructions(), instructionsAlignX, instructionsAlignY, null);
	}


	public boolean onTouch(View arg0, MotionEvent event)
	{
		int x;
		int y;
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			x = (int)event.getX();
			y = (int)event.getY();
			
			if ( (y > (newGameAlignY - margin / 2) ) && ( y < (newGameAlignY + margin / 2 + skin.getNewGame().getHeight())))
			{
//				Log.i(TAG, "New Game");
		    	Intent intent = new Intent(context, org.gunnm.simplepac.PacmanActivity.class);
		    	context.startActivity(intent);
			}
			 
			if ( (y > ( highScoresAlignY - margin / 2 )) && ( y < (highScoresAlignY + margin / 2 + skin.getHighScores().getHeight())))
			{
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
			    boolean useScoreloop;

			    useScoreloop = preferences.getBoolean("use_scoreloop", false);
			    
			    if (useScoreloop)
			    {
			    	ScoreloopManagerSingleton.get().askUserToAcceptTermsOfService( context, new Continuation<Boolean>() {
			    		public void withValue(final Boolean value, final Exception error) {
			    			if (value != null && value) {
								Intent intent = new Intent(context, EntryScreenActivity.class);
								context.startActivity(intent);
			    			}
			    		}
			    	});
			    }
			    else
			    {
			    	  builder.setMessage("You must use Score Loop to get access to the leaderboard");  
			          builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {  
			               public void onClick(DialogInterface dialog, int which) {  
			                    
			               }  
			          });  
			          AlertDialog alert = builder.create();  
			          alert.show();
			    }

			}
			
			if ( (y > ( preferencesAlignY - margin / 2) ) && ( y < (preferencesAlignY + margin / 2 +  skin.getPreferences().getHeight())))
			{
		    	Intent intent = new Intent(context, org.gunnm.simplepac.AppPreferences.class);
		    	context.startActivity(intent);
			}
			
			if ( (y > (copyrightAlignY - margin / 2) ) && ( x > copyrightAlignX))
			{
		    	Intent intent = new Intent(context, org.gunnm.simplepac.CopyrightActivity.class);
		    	context.startActivity(intent);
			}
			
			
			if ( (y > ( instructionsAlignY - margin / 2) ) && ( x < skin.getInstructions().getWidth()))
			{
		    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(skin.getInstructionsURL()));
		    	context.startActivity(browserIntent);
			}
		}
		return true;
	}
}
