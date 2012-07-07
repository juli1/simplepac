package org.gunnm.pacman;

import org.gunnm.pacman.model.Scores;
import org.gunnm.pacman.view.BitmapView;
import org.gunnm.pacman.view.TitleScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ScoresActivity extends Activity {
	
	TitleScreen titleCanvas;
	Scores		scores;
	public static String SCORE_WEBSITE = "http://games.gunnm.org/simplepac-scores";
	
	public void onCreate(Bundle savedInstanceState)
	{
		int size;
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
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.scores);
    	scores = Scores.getInstance (getApplicationContext());
//    	scores.registerScore(100000 + (int)(Math.random() % 100));
    	this.updateScores ();
    	
        LinearLayout fl = (LinearLayout) findViewById (R.id.scores);
    	LayoutParams lp;
    	int w;
    	w = (size - TitleActivity.skin.getLogo().getWidth()) / 2;
    	lp = new LayoutParams(size, TitleActivity.skin.getLogo().getHeight());
    	
    	fl.addView (new BitmapView (this, TitleActivity.skin.getLogo(), w, 0), 0, lp);
    }
	
    public void editPreferences (View view)
    {
    	Intent intent = new Intent(getApplicationContext(), org.gunnm.pacman.AppPreferences.class);
    	startActivity(intent);
    }
    
    public void goOnline (View view)
    {
    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(SCORE_WEBSITE));
    	startActivity(browserIntent);
    }
    
    private void updateScores ()
    {
    	ListView lv = (ListView) findViewById(R.id.scores_list);
    	
    	if (scores == null)
    	{
    		Log.e("Scores", "null");
    	}
    	else
    	{
    		lv.setAdapter (new ArrayAdapter<String> (this, R.layout.score_item, scores.getScores()));
    	}
    }
}