package org.gunnm.pacman.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
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
	private Context context;
	
	public TitleScreen (Context c, Skin s, Display d)
	{
		super (c);
		this.context = c;
		skin = s;
		display = d;
		this.setOnTouchListener(this);

	}
	
	
	protected void onDraw(Canvas canvas)
	{
	
		Paint paint;

		int margin = display.getHeight() / 20;
		paint = new Paint();
		paint.setFilterBitmap(true);
		logoAlignX = (display.getWidth() - skin.getLogo().getWidth()) / 2;
		logoAlignY = margin;
		
		newGameAlignX = (display.getWidth() - skin.getNewGame().getWidth()) / 2;
		newGameAlignY = logoAlignY + margin + skin.getLogo().getHeight();
		
		highScoresAlignX = (display.getWidth() - skin.getHighScores().getWidth()) / 2;
		highScoresAlignY = newGameAlignY + margin + skin.getNewGame().getHeight();
		
		preferencesAlignX = (display.getWidth() - skin.getPreferences().getWidth()) / 2;
		preferencesAlignY = highScoresAlignY + margin + skin.getHighScores().getHeight();
		
		copyrightAlignX = display.getWidth() - skin.getCopyright().getWidth();
		copyrightAlignY = preferencesAlignY + skin.getPreferences().getHeight() + margin;
		
		canvas.drawBitmap(skin.getLogo(), logoAlignX, logoAlignY, null);
		canvas.drawBitmap(skin.getNewGame(), newGameAlignX, newGameAlignY, null);
		canvas.drawBitmap(skin.getHighScores(), highScoresAlignX, highScoresAlignY, null);
		canvas.drawBitmap(skin.getPreferences(), preferencesAlignX, preferencesAlignY, null);
		canvas.drawBitmap(skin.getCopyright(), copyrightAlignX, copyrightAlignY, null);
	}


	public boolean onTouch(View arg0, MotionEvent event)
	{
		int x;
		int y;
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			x = (int)event.getX();
			y = (int)event.getY();
			
			if ( (y > newGameAlignY ) && ( y < (newGameAlignY + skin.getNewGame().getHeight())))
			{
				Log.i(TAG, "New Game");
		    	Intent intent = new Intent(context, org.gunnm.pacman.PacmanActivity.class);
		    	context.startActivity(intent);
			}
			
			if ( (y > highScoresAlignY ) && ( y < (highScoresAlignY + skin.getHighScores().getHeight())))
			{
				Log.i(TAG, "High Scores");
		    	Intent intent = new Intent(context, org.gunnm.pacman.ScoresActivity.class);
		    	context.startActivity(intent);
			}
			
			if ( (y > preferencesAlignY ) && ( y < (preferencesAlignY + skin.getPreferences().getHeight())))
			{
				Log.i(TAG, "Preferences");
		    	Intent intent = new Intent(context, org.gunnm.pacman.AppPreferences.class);
		    	context.startActivity(intent);
			}
			
			if ( (y > copyrightAlignY ) && ( x > copyrightAlignX))
			{
				Log.i(TAG, "Copyright");
		    	Intent intent = new Intent(context, org.gunnm.pacman.CopyrightActivity.class);
		    	context.startActivity(intent);
			}
		}
		return true;
	}
}
