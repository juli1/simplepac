package org.gunnm.pacman;

import org.gunnm.pacman.model.Game;
import org.gunnm.pacman.model.Scores;
import org.gunnm.pacman.view.BitmapView;
import org.gunnm.pacman.view.Skin;
import org.gunnm.pacman.view.TitleScreen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ScoresActivity extends Activity {
	
	TitleScreen titleCanvas;
	Scores		scores;
	
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