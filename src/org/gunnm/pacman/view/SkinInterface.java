package org.gunnm.pacman.view;

import android.graphics.Bitmap;

public interface SkinInterface {
	
	public Bitmap getPacmanFull ();
	public Bitmap getEnnemyLeft1 ();
	public Bitmap getEnnemyLeft2 ();
	public Bitmap getEnnemyUp1 ();
	public Bitmap getEnnemyUp2 ();
	public Bitmap getEnnemyDown1 ();
	public Bitmap getEnnemyDown2 ();
	public Bitmap getEnnemyRight1 ();
	public Bitmap getEnnemyRight2 ();
	public Bitmap getEnnemyVulnerable1 ();
	public Bitmap getEnnemyVulnerable2 ();
	public Bitmap getBonus1 ();
	public Bitmap getBonus2();
	public Bitmap getPoint();
	public Bitmap getPacmanDown1();
	public Bitmap getPacmanDown2();
	public Bitmap getPacmanDown3();
	public Bitmap getPacmanRight1();
	public Bitmap getPacmanRight2();
	public Bitmap getPacmanRight3();
	public Bitmap getPacmanLeft1();
	public Bitmap getPacmanLeft2();
	public Bitmap getPacmanLeft3();
	public Bitmap getPacmanUp1();
	public Bitmap getPacmanUp2();
	public Bitmap getPacmanUp3();
	
	public Bitmap getPacmanDie1();
	public Bitmap getPacmanDie2();
	public Bitmap getPacmanDie3();
	public Bitmap getPacmanDie4();
	public Bitmap getPacmanDie5();
	public Bitmap getPacmanDie6();

	public Bitmap getLogo();
	public Bitmap getHighScoresLogo();
	
	public Bitmap getNewGame();
	public Bitmap getPreferences();
	public Bitmap getHighScores();

	
	public Bitmap getWallHorizontal();
	public Bitmap getWallVertical();
	
	public Bitmap getCopyright();
	
	public Bitmap getGameOver();
	public Bitmap getCompleted();
	
	public String getCopyrightFile ();
	
	public String getSoundIntro();
	public String getSoundEat();
	public String getSoundEatBonus();
	public String getSoundDying();
	public String getSoundIntermission();



}
