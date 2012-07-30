package org.gunnm.simplepac;

import java.io.IOException;
import java.io.InputStream;

import org.gunnm.simplepac.R;
import org.gunnm.simplepac.R.id;
import org.gunnm.simplepac.R.layout;
import org.gunnm.simplepac.model.Game;
import org.gunnm.simplepac.view.BasicSkin;
import org.gunnm.simplepac.view.Skin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class CopyrightActivity extends Activity {
	private final String TAG = "CopyrightActivity";
	
	public void onCreate(Bundle savedInstanceState)
	{
        WindowManager 	wm;
		Display 		display;
		String 			copyright;
		Skin 			skin;
		int 			size;
		TextView 		txt;
		byte[] 			strtmp;
		
		

	    super.onCreate(savedInstanceState);
	    
        setContentView(R.layout.copyright);

        wm 				= (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		display 		= wm.getDefaultDisplay();
		copyright 		= "";
		skin 			= BasicSkin.getInstance (this.getResources().getAssets(), display.getWidth(), display.getHeight(), Game.getInstance());
		strtmp 			= new byte[1024];
		
        
        txt = (TextView) findViewById (R.id.copyright_text);
        txt.setText("");
        InputStream is;
		try {
			is = getResources().getAssets().open("copyright.txt");
			
        
	        while ( ( size = is.read(strtmp) ) > 0)
	        {
	        	copyright = copyright + new String (strtmp).substring(0, size - 1);
	        	
	        }
	        is.close();
	        if (skin.getCopyrightFile() !=null)
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
	        copyright.replace ("\r\n" , "");
	        copyright.replace (System.getProperty("line.separator"), "");
	        txt.setText (copyright);
//	        Log.i(TAG, "set text = " + copyright );

		} catch (IOException e) {
			
//			Log.e(TAG, e.toString());
		}
    }
	
	   public void comeBack (View view)
	    {
	    	Intent intent = new Intent(getApplicationContext(), org.gunnm.simplepac.TitleActivity.class);
	    	startActivity(intent);
	    }
}