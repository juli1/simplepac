package org.gunnm.pacman.view;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

public class BasicSkin extends Skin
{
	public final static String TAG = "Resources";
	private Bitmap pacmanFull;
	private Bitmap ennemyLeft1;
	private Bitmap ennemyLeft2;
	private Bitmap pacmanLeft1;
	private Bitmap pacmanLeft2;
	private Bitmap pacmanLeft3;
	private Bitmap pacmanRight1;
	private Bitmap pacmanRight2;
	private Bitmap pacmanRight3;
	private Bitmap pacmanUp1;
	private Bitmap pacmanUp2;
	private Bitmap pacmanUp3;
	private Bitmap pacmanBottom1;
	private Bitmap pacmanBottom2;
	private Bitmap pacmanBottom3;
	private Bitmap pacmanDie1;
	private Bitmap pacmanDie2;
	private Bitmap pacmanDie3;
	private Bitmap pacmanDie4;
	private Bitmap pacmanDie5;
	private Bitmap pacmanDie6;
	private Bitmap logo;
	private Bitmap preferences;
	private Bitmap highScores;
	private Bitmap newGame;
	private Bitmap ennemyUp1;
	private Bitmap ennemyUp2;
	private Bitmap ennemyDown1;
	private Bitmap ennemyDown2;
	private Bitmap ennemyRight1;
	private Bitmap ennemyRight2;
	private Bitmap ennemyVulnerable1;
	private Bitmap ennemyVulnerable2;
	private Bitmap point;
	private Bitmap bonus1;
	private Bitmap bonus2;
	private Bitmap gameOver;
	private Bitmap completed;
	private Bitmap wallVertical;
	private Bitmap wallHorizontal;
	private int partSize;
	
	private static Skin instance = null;
	
	public static Skin getInstance ()
	{
		return BasicSkin.instance;
	}
	
	public static Skin getInstance (AssetManager manager, int ps)
	{
		if (BasicSkin.instance == null)
		{
			BasicSkin.instance = new BasicSkin (manager, ps);
		}
		return BasicSkin.instance;
	}
	
	public BasicSkin (AssetManager manager, int ps)
	{
		if (BasicSkin.instance != null)
		{
			return;
		}
		
		partSize = ps;
		this.loadResources(manager);
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
	
	private Bitmap scaleImageWidth(Bitmap bitmap, int newWidth) 
	{
		Bitmap newBitmap;
		
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		float scaleWidth = ((float) newWidth) / width;
		float ratio = ((float) bitmap.getWidth()) / newWidth;
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
	
	
	public void loadResources (AssetManager manager)
	{
		int entitySize = partSize - 5;
		try 
		{
			pacmanLeft1 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-left-full.png")), entitySize);
			pacmanLeft2 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-left-half.png")), entitySize);
			pacmanLeft3 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-full.png")), entitySize);

			pacmanRight1 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-right-full.png")), entitySize);
			pacmanRight2 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-right-half.png")), entitySize);
			pacmanRight3 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-full.png")), entitySize);

			
			pacmanUp1 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-up-full.png")), entitySize);
			pacmanUp2 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-up-half.png")), entitySize);
			pacmanUp3 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-full.png")), entitySize);

			pacmanBottom1 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-bottom-full.png")), entitySize);
			pacmanBottom2 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-bottom-half.png")), entitySize);
			pacmanBottom3 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-full.png")), entitySize);

			pacmanDie1 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-die1.png")), entitySize);
			pacmanDie2 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-die2.png")), entitySize);
			pacmanDie3 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-die3.png")), entitySize);
			pacmanDie4 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-die4.png")), entitySize);
			pacmanDie5 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-die5.png")), entitySize);
			pacmanDie6 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-die6.png")), entitySize);

			logo 			= BitmapFactory.decodeStream(manager.open("logo.png"));
			gameOver 		= BitmapFactory.decodeStream(manager.open("gameover.png"));
			newGame 		= BitmapFactory.decodeStream(manager.open("newgame.png"));
			highScores 		= BitmapFactory.decodeStream(manager.open("highscores.png"));
			preferences 	= BitmapFactory.decodeStream(manager.open("preferences.png"));

			completed 		= BitmapFactory.decodeStream(manager.open("completed.png"));

			pacmanFull 		= scaleImage (BitmapFactory.decodeStream(manager.open("pacman-full.png")), entitySize);
			bonus1 			= scaleImage (BitmapFactory.decodeStream(manager.open("bonus1.png")), entitySize);
			bonus2 			= scaleImage (BitmapFactory.decodeStream(manager.open("bonus2.png")), entitySize);
			ennemyLeft1 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-left1.png")), entitySize);
			ennemyLeft2 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-left2.png")), entitySize);
			ennemyUp1 		= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-up1.png")), entitySize);
			ennemyUp2 		= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-up2.png")), entitySize);
			ennemyRight1 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-right1.png")), entitySize);
			ennemyRight2 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-right2.png")), entitySize);
			ennemyDown1 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-down1.png")), entitySize);
			ennemyDown2 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-down2.png")), entitySize);
			ennemyVulnerable1 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-blue1.png")), entitySize);
			ennemyVulnerable2 	= scaleImage (BitmapFactory.decodeStream(manager.open("ennemy-blue2.png")), entitySize);
			point 			= scaleImage (BitmapFactory.decodeStream(manager.open("point.png")), entitySize);
			wallVertical    = scaleImageHeight (BitmapFactory.decodeStream(manager.open("wallv.png")), partSize);
			wallHorizontal  = scaleImageWidth (BitmapFactory.decodeStream(manager.open("wallh.png")), partSize);
		}
		catch(IOException ex)
		{
			Log.e(TAG, "Exception: " + ex.toString());
		}
	}
	
	
	public Bitmap getPacmanFull ()
	{
		return this.pacmanFull;
	}
	public Bitmap getEnnemyLeft1 ()
	{
		return this.ennemyLeft1;
	}
	
	public Bitmap getEnnemyLeft2 ()
	{
		return this.ennemyLeft2;
	}
	
	public Bitmap getEnnemyUp1 ()
	{
		return this.ennemyUp1;
	}
	
	public Bitmap getEnnemyUp2 ()
	{
		return this.ennemyUp2;
	}
	
	public Bitmap getEnnemyDown1 ()
	{
		return this.ennemyDown1;
	}
	
	public Bitmap getEnnemyDown2 ()
	{
		return this.ennemyDown2;
	}
	public Bitmap getEnnemyRight1 ()
	{
		return this.ennemyRight1;
	}
	
	public Bitmap getEnnemyRight2 ()
	{
		return this.ennemyRight2;
	}
	
	public Bitmap getBonus1 ()
	{
		return this.bonus1;
	}
	
	public Bitmap getBonus2()
	{
		return this.bonus2;
	}
	public Bitmap getPoint()
	{
		return this.point;
	}

	public Bitmap getEnnemyVulnerable1() 
	{
		return this.ennemyVulnerable1;
	}

	public Bitmap getEnnemyVulnerable2() 
	{
		return this.ennemyVulnerable2;
	}

	public Bitmap getPacmanDown1() {
		return pacmanBottom1;
	}

	public Bitmap getPacmanDown2() {
		return pacmanBottom2;
	}

	public Bitmap getPacmanDown3() {
		return pacmanBottom3;
	}

	public Bitmap getPacmanRight1() {
		return pacmanRight1;
	}

	public Bitmap getPacmanRight2() {
		return pacmanRight2;
	}

	public Bitmap getPacmanRight3() {
		return pacmanRight3;
	}

	public Bitmap getPacmanLeft1() {
		return pacmanLeft1;
	}

	public Bitmap getPacmanLeft2() {
		return pacmanLeft2;
	}

	public Bitmap getPacmanLeft3() {
		return pacmanLeft3;
	}

	public Bitmap getPacmanUp1() {
		return pacmanUp1;
	}

	public Bitmap getPacmanUp2() {
		return pacmanUp2;
	}

	public Bitmap getPacmanUp3() {
		return pacmanUp3;
	}

	public Bitmap getPacmanDie1() {
		return pacmanDie1;
	}

	public Bitmap getPacmanDie2() {
		return pacmanDie2;
	}

	public Bitmap getPacmanDie3() {
		return pacmanDie3;
	}

	public Bitmap getPacmanDie4() {
		return pacmanDie4;
	}

	public Bitmap getPacmanDie5() {
		return pacmanDie5;
	}

	public Bitmap getPacmanDie6() {
		return pacmanDie6;
	}

	public Bitmap getGameOver() {
		return gameOver;
	}
	

	public Bitmap getCompleted() {
		return completed;
	}
	
	public Bitmap getWallHorizontal() {
		return wallHorizontal;
	}

	public Bitmap getNewGame() {
		return newGame;
	}	

	public Bitmap getHighScores() {
		return highScores;
	}
	

	public Bitmap getPreferences() {
		return preferences;
	}
	
	public Bitmap getLogo() {
		return logo;
	}
	
	public Bitmap getWallVertical() {
		return wallVertical;
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
	
}
