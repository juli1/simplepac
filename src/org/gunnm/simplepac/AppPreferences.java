package org.gunnm.simplepac;

import org.gunnm.simplepac.R;
import org.gunnm.simplepac.R.xml;
import org.gunnm.simplepac.configuration.FullGame;
import org.gunnm.simplepac.model.Game;
import org.gunnm.simplepac.model.Scores;

import com.scoreloop.client.android.core.model.Continuation;
import com.scoreloop.client.android.ui.ScoreloopManagerSingleton;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class AppPreferences extends PreferenceActivity {
    
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
    
    protected void onPause ()
    {
    	super.onPause();
    }
 
}
