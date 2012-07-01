package org.gunnm.pacman;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.gunnm.pacman.model.Game;
import org.gunnm.pacman.model.Scores;
import org.gunnm.pacman.view.BasicSkin;
import org.gunnm.pacman.view.BitmapView;
import org.gunnm.pacman.view.Skin;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class CopyrightActivity extends Activity {
	private final String TAG = "CopyrightActivity";
	
	public void onCreate(Bundle savedInstanceState)
	{
		String copyright = "";
		byte[] strtmp = new byte[1024];
		int size;
	    super.onCreate(savedInstanceState);
	    
        setContentView(R.layout.copyright);

        EditText txt = (EditText) findViewById (R.id.copyright_text);
        txt.setText("");
        InputStream is;
		try {
			is = getResources().getAssets().open("copyright.txt");
			
        
	        while ( ( size = is.read(strtmp) ) > 0)
	        {
	        	copyright = copyright + new String (strtmp).substring(0, size);
	        }
	        is.close();
	        if (Skin.getInstance().getCopyrightFile() !=null)
			{
	        	copyright = copyright + "\n";
	        	is = getResources().getAssets().open(Skin.getInstance().getCopyrightFile());
		        while ( ( size = is.read(strtmp) ) > 0)
		        {
		        	copyright = copyright + new String (strtmp).substring(0, size);
		        }
		        is.close();
			}
	        
	        is.close();
        
	        txt.setText (copyright);
	        Log.i(TAG, "set text = " + copyright );

		} catch (IOException e) {
			
			Log.e(TAG, e.toString());
		}
    }
	
    public void comeBack (View view)
    {
    	Intent intent = new Intent(getApplicationContext(), org.gunnm.pacman.TitleActivity.class);
    	startActivity(intent);
    }
    
}