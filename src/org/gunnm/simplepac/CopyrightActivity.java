package org.gunnm.simplepac;

import java.io.IOException;
import java.io.InputStream;

import org.gunnm.simplepac.R;
import org.gunnm.simplepac.R.id;
import org.gunnm.simplepac.R.layout;
import org.gunnm.simplepac.view.Skin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CopyrightActivity extends Activity {
	private final String TAG = "CopyrightActivity";
	
	public void onCreate(Bundle savedInstanceState)
	{
		String copyright = "";
		byte[] strtmp = new byte[1024];
		int size;
	    super.onCreate(savedInstanceState);
	    
        setContentView(R.layout.copyright);

        TextView txt = (TextView) findViewById (R.id.copyright_text);
        txt.setText("");
        InputStream is;
		try {
			is = getResources().getAssets().open("copyright.txt");
			
        
	        while ( ( size = is.read(strtmp) ) > 0)
	        {
	        	copyright = copyright + new String (strtmp).substring(0, size - 1);
	        	
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