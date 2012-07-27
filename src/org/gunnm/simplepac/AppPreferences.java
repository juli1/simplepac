package org.gunnm.simplepac;

import org.gunnm.simplepac.R;
import org.gunnm.simplepac.R.xml;
import org.gunnm.simplepac.model.Game;
import org.gunnm.simplepac.model.Scores;

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
        	  
        		  try
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
	              catch (Exception e)
	              {
	              }
        	  }
          }

	 };
	  
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Scores scores = Scores.getInstance(this);
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
		
		if (! Game.isDemo() )
		{
			sendButton.setOnClickListener(new OnClickListener()
			{
				public void onClick(View arg0) {
	
					sendingScoresDialog.show();
	
					 Thread thread = new Thread(new Runnable ()
					 {
						 public void run()
						 {
							 if (scores == null)
							 {
							        handler.sendEmptyMessage(0);
							        return;
							 }
							if (! scores.sendAll())
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
		
		
		if (! Game.isDemo() )
		{
			resetButton.setOnClickListener(new OnClickListener() 
			{
	
				public void onClick(View arg0) {
					Scores.getInstance(getApplicationContext()).reset();
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
