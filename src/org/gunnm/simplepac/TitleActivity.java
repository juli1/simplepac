package org.gunnm.simplepac;

import org.gunnm.simplepac.configuration.Demo;
import org.gunnm.simplepac.model.Game;


public class TitleActivity extends org.gunnm.simplepac.common.TitleActivity {

	 public void loadGameModel()
	 {
		 gameModel 		= new Game (new Demo());
	 }
}