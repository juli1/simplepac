package org.gunnm.pacman;

import org.gunnm.pacman.model.Game;
import org.gunnm.pacman.model.Scores;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class AppPreferences extends PreferenceActivity {
    
	 private ProgressDialog sendingScoresDialog;
	 AlertDialog.Builder builder;
	 private Handler handler = new Handler() {

          public void handleMessage(Message msg) {
        	  sendingScoresDialog.dismiss();
        	  
        	  if (msg.what == 0)
        	  {
        	  
	        	  builder.setMessage("Error while sending scores, please check your internet connection");  
	              builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {  
	                   public void onClick(DialogInterface dialog, int which) {  
	                        dialog.cancel();  
	                   }  
	              });  
	              AlertDialog alert = builder.create();  
	              alert.show();  
        	  }
          }

	 };
	  
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Button sendButton  = new Button(this);
        sendButton.setText("Send all scores");
        Button resetButton = new Button(this);
        resetButton.setText("Reset scores");

        builder = new AlertDialog.Builder(this);
        
		sendingScoresDialog = new ProgressDialog(this);
		sendingScoresDialog.setMessage("Sending scores ...");
		sendingScoresDialog.setTitle("Working ...");
		sendingScoresDialog.setCancelable(true);
		if (! Game.isDemo)
		{
			sendButton.setOnClickListener(new OnClickListener()
			{
				public void onClick(View arg0) {
	
					sendingScoresDialog.show();
	
					 Thread thread = new Thread(new Runnable ()
					 {
						 public void run()
						 {
								if (! Scores.getInstance().sendAll())
								{
							        handler.sendEmptyMessage(0);
								}
								else
								{
									 handler.sendEmptyMessage(1);
								}
						 }
					 });
	
					 thread.start();
				}
			});
		}
		else
		{		
			sendButton.setOnClickListener(new OnClickListener()
			{
				public void onClick(View arg0) {
	

			      	  builder.setMessage("Online score available with full version only");  
			          builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {  
			               public void onClick(DialogInterface dialog, int which) {  
			                    
			               }  
			          });  
			          AlertDialog alert = builder.create();  
			          alert.show();
				}
			});  
		}
		
		
		if (! Game.isDemo)
		{
			resetButton.setOnClickListener(new OnClickListener() 
			{
	
				public void onClick(View arg0) {
					Scores.getInstance().reset();
				}
			});
		}
		else
		{
			resetButton.setOnClickListener(new OnClickListener() 
			{
	
				public void onClick(View arg0) {

			      	  builder.setMessage("Scores functions available in full version only");  
			          builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {  
			               public void onClick(DialogInterface dialog, int which) {  
			                    
			               }  
			          });  
			          AlertDialog alert = builder.create();  
			          alert.show();
				}
			});
		}
				
        this.getListView().addFooterView (sendButton);
        this.getListView().addFooterView (resetButton);
        
    }
 
}
