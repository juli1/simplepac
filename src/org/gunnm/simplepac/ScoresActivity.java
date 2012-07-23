package org.gunnm.simplepac;

import org.gunnm.simplepac.R;
import org.gunnm.simplepac.R.id;
import org.gunnm.simplepac.R.layout;
import org.gunnm.simplepac.model.Game;
import org.gunnm.simplepac.model.Scores;
import org.gunnm.simplepac.view.BasicSkin;
import org.gunnm.simplepac.view.BitmapView;
import org.gunnm.simplepac.view.Skin;
import org.gunnm.simplepac.view.TitleScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
		Skin skin;
		LinearLayout fl;
		LayoutParams lp;
		int w;
		
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
    	
    	this.updateScores ();
    	
        fl = (LinearLayout) findViewById (R.id.scores);
    	skin = BasicSkin.getInstance(getApplicationContext().getAssets(), display.getWidth(), display.getHeight(), Game.getInstance());
    	

    	w = (size - skin.getLogo().getWidth()) / 2;
    	lp = new LayoutParams(size, skin.getLogo().getHeight());
    	
    	fl.addView (new BitmapView (this, skin.getLogo(), w, 0), 0, lp);
    }
	
    public void editPreferences (View view)
    {
    	Intent intent = new Intent(getApplicationContext(), org.gunnm.simplepac.AppPreferences.class);
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
    	
    	if (scores != null)
    	{
    		lv.setAdapter (new ArrayAdapter<String> (this, R.layout.score_item, scores.getScores()));
    	}
    }
}