package org.gunnm.pacman.view;

import java.io.IOException;
import java.io.InputStream;

import org.gunnm.pacman.model.Game;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

public class BasicSkin extends Skin
{
	private int currentOrientation;
	private AssetManager assetManager;
	public final static String TAG = "Resources";
	public final static int NB_BITMAPS = 50;
	public final static int NB_ORIENTATIONS = 2;
	
	private final static int pacmanFull = 1;
	private final static int ennemyLeft1 = 2;
	private final static int ennemyLeft2 = 3;
	private final static int pacmanLeft1 = 4;
	private final static int pacmanLeft2 = 5;
	private final static int pacmanLeft3 = 6;
	private final static int pacmanRight1 = 7;
	private final static int pacmanRight2 = 8;
	private final static int pacmanRight3 = 9;
	private final static int pacmanUp1 = 10;
	private final static int pacmanUp2 = 11;
	private final static int pacmanUp3 = 12;
	private final static int pacmanBottom1 = 13;
	private final static int pacmanBottom2 = 14;
	private final static int pacmanBottom3 = 15;
	private final static int pacmanDie1 = 16;
	private final static int pacmanDie2 = 17;
	private final static int pacmanDie3 = 18;
	private final static int pacmanDie4 = 19;
	private final static int pacmanDie5 = 20;
	private final static int pacmanDie6 = 21;
	private final static int logo = 22;
	private final static int instructions = 23;
	private final static int preferences = 24;
	private final static int highScores = 25;
	private final static int newGame = 26;
	private final static int ennemyUp1 = 27;
	private final static int ennemyUp2 = 28;
	private final static int ennemyDown1 = 29;
	private final static int ennemyDown2 = 30;
	private final static int ennemyRight1 = 31;
	private final static int ennemyRight2 = 32;
	private final static int ennemyVulnerable1 = 33;
	private final static int ennemyVulnerable2 = 34;
	private final static int point = 35;
	private final static int superPoint = 36;
	private final static int specialSmall = 37;
	private final static int specialMedium = 38;
	private final static int specialBig = 39;
	private final static int bonus1 = 40;
	private final static int bonus2 = 41;
	private final static int gameOver = 42;
	private final static int nextLevel = 43;
	private final static int completed = 44;
	private final static int demoCompleted = 45;
	private final static int wallVertical = 46;
	private final static int wallHorizontal = 47;
	private final static int highScoresLogo = 48;
	private final static int copyright = 49;
	private Bitmap[][] skinBitmaps = new Bitmap[NB_BITMAPS][NB_ORIENTATIONS];
	private int screenWidth;
	private int screenHeight;
	private int gameWidth;
	private int gameHeight;	
	
	
	public static Skin getInstance ()
	{
		return BasicSkin.instance;
	}
	
	public static Skin getInstance (AssetManager manager, int sw, int sh, int gw, int gh)
	{
		if (BasicSkin.instance == null)
		{
			BasicSkin.instance = new BasicSkin (manager, sw, sh, gw, gh);
		}
		return BasicSkin.instance;
	}
	
	public static Skin getInstance (AssetManager manager, int sw, int sh, Game g)
	{
		return getInstance (manager, sw, sh, g.getMap().getWidth(), g.getMap().getHeight());
	}
	
	public BasicSkin (AssetManager manager, int sw, int sh, int gw, int gh)
	{
		if (BasicSkin.instance != null)
		{
			return;
		}
		
		if (sw < sh) 
		{
			screenWidth  = sw;
			screenHeight = sh;
			this.currentOrientation = ORIENTATION_PORTRAIT;
		} 
		else
		{
			screenWidth  = sh;
			screenHeight = sw;
			this.currentOrientation = ORIENTATION_LANDSCAPE;
		}
		gameWidth = gw;
		gameHeight = gh;
		this.assetManager = manager;
		this.loadResources(manager, ORIENTATION_PORTRAIT);
		this.loadResources(manager, ORIENTATION_LANDSCAPE);
		Log.i(TAG, "Start with sw=" + sw + "sh=" + sh);
		BasicSkin.instance = this;
	}
	
	private Bitmap scaleImage(Bitmap bitmap, int newWidth) 
	{
		Bitmap newBitmap;
		
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		float scaleWidth = ((float) newWidth) / width;
		float ratio = ((float) bitmap.getWidth()) / newWidth;
		int newHeight = (int) (height / ratio);
		float scaleHeight = ((float) newHeight) / height;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		return newBitmap;
	}
	
	private Bitmap scaleImageByHeight(Bitmap bitmap, int newHeight) 
	{
		Bitmap newBitmap;
		
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		float scaleHeight = ((float) newHeight) / height;
		float ratio = ((float) bitmap.getHeight()) / newHeight;
		int newWidth = (int) (width / ratio);
		float scaleWidth = ((float) newWidth) / width;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		return newBitmap;
	}
	
	private Bitmap scaleImageWidth(Bitmap bitmap, int newWidth) 
	{
		Bitmap newBitmap;
		
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		float scaleWidth = ((float) newWidth) / width;
		int newHeight = (int) height;
		float scaleHeight = ((float) newHeight) / height;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		return newBitmap;
	}
	
	private Bitmap scaleImageHeight(Bitmap bitmap, int nh) 
	{
		Bitmap newBitmap;
		
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		float scaleWidth = ((float) width) / width;
		
		int newHeight = (int) nh;
		float scaleHeight = ((float) newHeight) / height;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		return newBitmap;
	}
	
	
	public void loadResources (AssetManager manager, int orientation)
	{
		int partSize;
		int entitySize;
		
		if (orientation == Skin.ORIENTATION_LANDSCAPE)
		{
			partSize = GameCanvas.computeSquareSize(screenHeight, screenWidth, gameWidth, gameHeight);
		}
		else
		{
			partSize = GameCanvas.computeSquareSize(screenWidth, screenHeight, gameWidth, gameHeight);
		}
		entitySize = partSize - 5;
		try 
		{
			skinBitmaps[pacmanLeft1][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-left-full.png")), entitySize);
			skinBitmaps[pacmanLeft2][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-left-half.png")), entitySize);
			skinBitmaps[pacmanLeft3][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-full.png")), entitySize);

			skinBitmaps[pacmanRight1][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-right-full.png")), entitySize);
			skinBitmaps[pacmanRight2][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-right-half.png")), entitySize);
			skinBitmaps[pacmanRight3][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-full.png")), entitySize);
 
			
			skinBitmaps[pacmanUp1][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-up-full.png")), entitySize);
			skinBitmaps[pacmanUp2][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-up-half.png")), entitySize);
			skinBitmaps[pacmanUp3][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-full.png")), entitySize);

			skinBitmaps[pacmanBottom1][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-bottom-full.png")), entitySize);
			skinBitmaps[pacmanBottom2][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-bottom-half.png")), entitySize);
			skinBitmaps[pacmanBottom3][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-full.png")), entitySize);

			skinBitmaps[pacmanDie1][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-die1.png")), entitySize);
			skinBitmaps[pacmanDie2][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-die2.png")), entitySize);
			skinBitmaps[pacmanDie3][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-die3.png")), entitySize);
			skinBitmaps[pacmanDie4][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-die4.png")), entitySize);
			skinBitmaps[pacmanDie5][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-die5.png")), entitySize);
			skinBitmaps[pacmanDie6][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-die6.png")), entitySize);
			if (orientation == ORIENTATION_LANDSCAPE)
			{
				skinBitmaps[logo][orientation] 			= scaleImageByHeight(BitmapFactory.decodeStream(manager.open("logo.png")), screenWidth / 6);

				skinBitmaps[gameOver][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("gameover.png")), screenHeight / 2);
				skinBitmaps[nextLevel][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("nextlevel.png")), screenHeight / 2);
				
				skinBitmaps[newGame][orientation] 		= scaleImage( BitmapFactory.decodeStream(manager.open("newgame.png")), screenHeight / 3);
				skinBitmaps[highScores][orientation] 		= scaleImage(BitmapFactory.decodeStream(manager.open("highscores.png")), screenHeight / 3);
				skinBitmaps[preferences][orientation] 	= scaleImage(BitmapFactory.decodeStream(manager.open("preferences.png")), screenHeight / 3);

				skinBitmaps[instructions][orientation] 	= scaleImage(BitmapFactory.decodeStream(manager.open("instructions.png")), screenHeight / 3);
				
				skinBitmaps[copyright][orientation] 		= scaleImage(BitmapFactory.decodeStream(manager.open("copyright.png")), screenHeight / 3);
				skinBitmaps[completed][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("completed.png")), screenHeight / 2);
				
				skinBitmaps[demoCompleted][orientation] 	= scaleImage (BitmapFactory.decodeStream(manager.open("democompleted.png")), screenHeight / 3 * 2);
	  
			}
			else
			{ 
				skinBitmaps[logo][orientation] 			= scaleImageByHeight(BitmapFactory.decodeStream(manager.open("logo.png")), screenHeight / 6);

				skinBitmaps[gameOver][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("gameover.png")), screenWidth / 2);
				skinBitmaps[nextLevel][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("nextlevel.png")), screenWidth / 2);
				
				skinBitmaps[newGame][orientation] 		= scaleImage( BitmapFactory.decodeStream(manager.open("newgame.png")), screenWidth / 3);
				skinBitmaps[highScores][orientation] 		= scaleImage(BitmapFactory.decodeStream(manager.open("highscores.png")), screenWidth / 3);
				skinBitmaps[preferences][orientation] 	= scaleImage(BitmapFactory.decodeStream(manager.open("preferences.png")), screenWidth / 3);

				skinBitmaps[instructions][orientation] 	= scaleImage(BitmapFactory.decodeStream(manager.open("instructions.png")), screenWidth / 3);
				
				skinBitmaps[copyright][orientation] 		= scaleImage(BitmapFactory.decodeStream(manager.open("copyright.png")), screenWidth / 3);
				skinBitmaps[completed][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("completed.png")), screenWidth / 2);
				
				skinBitmaps[demoCompleted][orientation] 	= scaleImage (BitmapFactory.decodeStream(manager.open("democompleted.png")), screenWidth / 3 * 2);
	  
			}
			skinBitmaps[highScoresLogo][orientation]	= BitmapFactory.decodeStream(manager.open("logohighscores.png"));
			skinBitmaps[pacmanFull][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-full.png")), entitySize);
			skinBitmaps[bonus1][orientation] 			= scaleImage (BitmapFactory.decodeStream(manager.open("bonus1.png")), entitySize);
			skinBitmaps[bonus2][orientation] 			= scaleImage (BitmapFactory.decodeStream(manager.open("bonus2.png")), entitySize);
			skinBitmaps[specialSmall][orientation]	= scaleImage (BitmapFactory.decodeStream(manager.open("bonus1.png")), entitySize);
			skinBitmaps[specialMedium][orientation]	= scaleImage (BitmapFactory.decodeStream(manager.open("bonus2.png")), entitySize);
			skinBitmaps[specialBig][orientation]		= scaleImage (BitmapFactory.decodeStream(manager.open("bonus3.png")), entitySize);
			skinBitmaps[ennemyLeft1][orientation] 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-left1.png")), entitySize);
			skinBitmaps[ennemyLeft2][orientation] 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-left2.png")), entitySize);
			skinBitmaps[ennemyUp1][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-up1.png")), entitySize);
			skinBitmaps[ennemyUp2][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-up2.png")), entitySize);
			skinBitmaps[ennemyRight1][orientation] 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-right1.png")), entitySize);
			skinBitmaps[ennemyRight2][orientation] 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-right2.png")), entitySize);
			skinBitmaps[ennemyDown1][orientation] 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-down1.png")), entitySize);
			skinBitmaps[ennemyDown2][orientation] 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-down2.png")), entitySize);
			skinBitmaps[ennemyVulnerable1][orientation] 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-blue1.png")), entitySize);
			skinBitmaps[ennemyVulnerable2][orientation] 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-blue2.png")), entitySize);
			skinBitmaps[point][orientation] 			= scaleImage (BitmapFactory.decodeStream(manager.open("point.png")), entitySize);
			
			skinBitmaps[superPoint][orientation] 		= scaleImage (BitmapFactory.decodeStream(manager.open("superpoint.png")), entitySize);
			skinBitmaps[wallVertical][orientation]    = scaleImageHeight (BitmapFactory.decodeStream(manager.open("wallv.png")), partSize + 3 );
			
			skinBitmaps[wallHorizontal][orientation]  = scaleImageWidth (BitmapFactory.decodeStream(manager.open("wallh.png")), partSize + 3);
		}
		catch(IOException ex)
		{
//			Log.e(TAG, "Exception: " + ex.toString());
		}
	}
	public Bitmap getCopyright ()
	{
		return this.skinBitmaps[copyright][this.currentOrientation];
	}
	
	public Bitmap getInstructions ()
	{
		return this.skinBitmaps[instructions][this.currentOrientation];
	}
	
	
	public String getInstructionsURL ()
	{
		return "http://games.gunnm.org/simplepac-instructions.html";
	}
	
	public Bitmap getPacmanFull ()
	{
		return this.skinBitmaps[pacmanFull][this.currentOrientation];
	}
	public Bitmap getEnnemyLeft1 ()
	{
		return this.skinBitmaps[ennemyLeft1][this.currentOrientation];
	}
	
	public Bitmap getEnnemyLeft2 ()
	{
		return this.skinBitmaps[ennemyLeft2][this.currentOrientation];
	}
	
	public Bitmap getEnnemyUp1 ()
	{
		return this.skinBitmaps[ennemyUp1][this.currentOrientation];
	}
	
	public Bitmap getEnnemyUp2 ()
	{
		return this.skinBitmaps[ennemyUp2][this.currentOrientation];
	}
	
	public Bitmap getEnnemyDown1 ()
	{
		return this.skinBitmaps[ennemyDown1][this.currentOrientation];
	}
	
	public Bitmap getEnnemyDown2 ()
	{
		return this.skinBitmaps[ennemyDown2][this.currentOrientation];
	}
	public Bitmap getEnnemyRight1 ()
	{
		return this.skinBitmaps[ennemyRight1][this.currentOrientation];
	}
	
	public Bitmap getEnnemyRight2 ()
	{
		return this.skinBitmaps[ennemyRight2][this.currentOrientation];
	}
	
	public Bitmap getBonus1 ()
	{
		return this.skinBitmaps[bonus1][this.currentOrientation];
	}
	
	public Bitmap getSpecialSmall ()
	{
		return this.skinBitmaps[specialSmall][this.currentOrientation];
	}	

	public Bitmap getSpecialMedium ()
	{
		return this.skinBitmaps[specialMedium][this.currentOrientation];
	}

	public Bitmap getSpecialBig ()
	{
		return this.skinBitmaps[specialBig][this.currentOrientation];
	}
	
	public Bitmap getBonus2()
	{
		return this.skinBitmaps[bonus2][this.currentOrientation];
	}
	
	public Bitmap getSuperPoint()
	{
		return this.skinBitmaps[superPoint][this.currentOrientation];
	}
	
	public Bitmap getPoint()
	{
		return this.skinBitmaps[point][this.currentOrientation];
	}

	public Bitmap getEnnemyVulnerable1() 
	{
		return this.skinBitmaps[ennemyVulnerable1][this.currentOrientation];
	}

	public Bitmap getEnnemyVulnerable2() 
	{
		return this.skinBitmaps[ennemyVulnerable2][this.currentOrientation];
	}

	public Bitmap getPacmanDown1() {
		return this.skinBitmaps[pacmanBottom1][this.currentOrientation];
	}

	public Bitmap getPacmanDown2() {
		return this.skinBitmaps[pacmanBottom2][this.currentOrientation];
	}

	public Bitmap getPacmanDown3() {
		return this.skinBitmaps[pacmanBottom3][this.currentOrientation];
	}

	public Bitmap getPacmanRight1() {
		return this.skinBitmaps[pacmanRight1][this.currentOrientation];
	}

	public Bitmap getPacmanRight2() {
		return this.skinBitmaps[pacmanRight2][this.currentOrientation];
	}

	public Bitmap getPacmanRight3() {
		return this.skinBitmaps[pacmanRight3][this.currentOrientation];
	}

	public Bitmap getPacmanLeft1() {
		return this.skinBitmaps[pacmanLeft1][this.currentOrientation];
	}

	public Bitmap getPacmanLeft2() {
		return this.skinBitmaps[pacmanLeft2][this.currentOrientation];
	}

	public Bitmap getPacmanLeft3() {
		return this.skinBitmaps[pacmanLeft3][this.currentOrientation];
	}

	public Bitmap getPacmanUp1() {
		return this.skinBitmaps[pacmanUp1][this.currentOrientation];
	}

	public Bitmap getPacmanUp2() {
		return this.skinBitmaps[pacmanUp2][this.currentOrientation];
	}

	public Bitmap getPacmanUp3() {
		return this.skinBitmaps[pacmanUp3][this.currentOrientation];
	}

	public Bitmap getPacmanDie1() {
		return this.skinBitmaps[pacmanDie1][this.currentOrientation];
	}

	public Bitmap getPacmanDie2() {
		return this.skinBitmaps[pacmanDie2][this.currentOrientation];
	}

	public Bitmap getPacmanDie3() {
		return this.skinBitmaps[pacmanDie3][this.currentOrientation];
	}

	public Bitmap getPacmanDie4() {
		return this.skinBitmaps[pacmanDie4][this.currentOrientation];
	}

	public Bitmap getPacmanDie5() {
		return this.skinBitmaps[pacmanDie5][this.currentOrientation];
	}

	public Bitmap getPacmanDie6() {
		return this.skinBitmaps[pacmanDie6][this.currentOrientation];
	}

	public Bitmap getGameOver() {
		return this.skinBitmaps[gameOver][this.currentOrientation];
	}
	
	public Bitmap getNextLevel() {
		return this.skinBitmaps[nextLevel][this.currentOrientation];
	}

	public Bitmap getCompleted() {
		return this.skinBitmaps[completed][this.currentOrientation];
	}
	
	public Bitmap getDemoCompleted() {
		return this.skinBitmaps[demoCompleted][this.currentOrientation];
	}
	
	public Bitmap getWallHorizontal() {
		return this.skinBitmaps[wallHorizontal][this.currentOrientation];
	}

	public Bitmap getNewGame() {
		return this.skinBitmaps[newGame][this.currentOrientation];
	}	

	public Bitmap getHighScores() {
		return this.skinBitmaps[highScores][this.currentOrientation];
	}
	

	public Bitmap getPreferences() {
		return this.skinBitmaps[preferences][this.currentOrientation];
	}
	
	public Bitmap getLogo() {
		return this.skinBitmaps[logo][this.currentOrientation];
	}
	
	public Bitmap getWallVertical() {
		return this.skinBitmaps[wallVertical][this.currentOrientation];
	}
	
	public String getSoundIntro ()
	{
		return "intro.ogg";
	}

	public String getSoundEat() {
		return "eat.ogg";
	}

	public String getSoundEatBonus() {
		return "eatbonus.ogg";
	}

	public String getSoundDying() {
		return "dying.ogg";
	}

	public String getSoundIntermission() {
		return "intermission.ogg";
	}
	
	public Bitmap getHighScoresLogo()
	{
		return this.skinBitmaps[highScoresLogo][this.currentOrientation];
	}
	
	public String getCopyrightFile ()
	{
		return "copyright-skin.txt";
	}
	
	
	public void setOrientation (int o)
	{
		this.currentOrientation = o;
	}
}
