package org.gunnm.pacman;

import android.app.Activity;
import android.os.Bundle;

public class PacmanActivity extends Activity {
	
	GameCanvas mainCanvas;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainCanvas = new GameCanvas (this);
        setContentView(mainCanvas);
        //setContentView(R.layout.main);
    }
}