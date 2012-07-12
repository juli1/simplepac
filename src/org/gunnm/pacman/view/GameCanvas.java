package org.gunnm.pacman.view;

import org.gunnm.pacman.model.Ennemy;
import org.gunnm.pacman.model.Entity;
import org.gunnm.pacman.model.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class GameCanvas extends View
{
	private static String TAG = "GameCanvas";
	private int size;
	private int mapAlignX;
	private int mapAlignY;
	private volatile Game gameModel;
	private Skin skin;
	private int squareSize;
	private static float STEP_INCR = 0.17f;
	private Paint defaultPaint;
	 
	
	public GameCanvas (Context context, Game gm, Skin s)
	{
		super (context);
		
		gameModel = gm;
		setMinimumHeight(100);
		setMinimumWidth(100);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		defaultPaint = new Paint();
		if (display.getHeight() < display.getWidth())
		{
			size = display.getHeight();
		}
		else
		{ 
			size = display.getWidth(); 
		}
		
		squareSize = computeSquareSize(display.getWidth(), display.getHeight(), gm);
		
		if ((squareSize * gm.getMap().getWidth()) < display.getWidth())
		{
			mapAlignX = (display.getWidth() - (squareSize * gm.getMap().getWidth()))/2;
		}
		else
		{
			mapAlignX = 0;
		}
		
		if ((squareSize * gm.getMap().getHeight()) < (display.getHeight() - s.getLogo().getHeight() - s.getLogo().getHeight() / 3))
		{
			mapAlignY = (display.getHeight() - s.getLogo().getHeight() - s.getLogo().getHeight() / 3- (squareSize * gm.getMap().getHeight()))/2;
		}
		else
		{
			mapAlignY = 0;
		}
		skin = s;
		STEP_INCR = (float)(squareSize ) / (2 * Game.INTERNAL_STEP_THRESHOLD) ;
//		Log.i(TAG, "squareSize=" + squareSize + " incr = " + STEP_INCR);
	}
	
	
	public static int computeSquareSize (int screenWidth, int screenHeight, Game g)
	{
		return computeSquareSize(screenWidth, screenHeight, g.getMap().getWidth(),g.getMap().getHeight());
	}
	
	public static int computeSquareSize (int screenWidth, int screenHeight, int gameWidth, int gameHeight)
	{
		int byWidth;
		int byHeight;
		byWidth = screenWidth / gameWidth;
		byHeight = ( screenHeight - (screenHeight / 4)) / gameHeight;
		if (byWidth < byHeight)
		{
			return byWidth;
		}
		return byHeight;
	}
	
	
	public void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
	{
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
	}
	
	private void drawEnnemy (Ennemy e, Canvas canvas)
	{
	//	Log.e(TAG,"Intent to draw ennemy "+ e.toString() +"at coord=(" + e.getPositionX() + "," + e.getPositionY() + ") state=" +e.getState());

		Bitmap bitmapToLoad = null;
		if (e.isAlive())
		{
			if (! gameModel.getHero().isVulnerable())
			{
				if ((e.getState() % 2) == 1)
				{
					bitmapToLoad = skin.getEnnemyVulnerable1 ();
				}
				else
				{
					bitmapToLoad = skin.getEnnemyVulnerable2 ();
				}
			}
			else
			{		
				switch (e.getDirection())
				{
					case Entity.DIRECTION_LEFT:
					{
						if ((e.getState() % 2) == 1)
						{
							bitmapToLoad = skin.getEnnemyLeft1 ();
						}
						else
						{
							bitmapToLoad = skin.getEnnemyLeft2 ();
						}
						break;
					}
					case Entity.DIRECTION_RIGHT:
					{
						if ((e.getState() % 2) == 1)
						{
							bitmapToLoad = skin.getEnnemyRight1 ();
						}
						else
						{
							bitmapToLoad = skin.getEnnemyRight2 ();
						}
						break;
					}
					case Entity.DIRECTION_UP:
					{
						if ((e.getState() % 2) == 1)
						{
							bitmapToLoad = skin.getEnnemyUp1 ();
						}
						else
						{
							bitmapToLoad = skin.getEnnemyUp2 ();
						}
						break;
					}
					default:
					{
						if ((e.getState() % 2) == 1)
						{
							bitmapToLoad = skin.getEnnemyDown1 ();
						}
						else
						{
							bitmapToLoad = skin.getEnnemyDown2 ();
						}
						break;
					}
				}
			}
			if (bitmapToLoad != null)
			{
				//Log.e(TAG,"Draw ennemy "+ e.toString() +"at coord=(" + e.getPositionX() + "," + e.getPositionY() + ") state=" +e.getState());
				canvas.drawBitmap(bitmapToLoad, 
								  mapAlignX + e.getPositionX() * squareSize + 5  + e.getInternalStepValueX() * STEP_INCR, 
								  mapAlignY + e.getPositionY() * squareSize + 5 + e.getInternalStepValueY() * STEP_INCR, 
								  defaultPaint);
			}
		}
	}
	
	private void drawHero (Canvas canvas)
	{ 
		Bitmap bitmapToLoad;
		bitmapToLoad = null;
		
		if (gameModel.getHero().isDying())
		{
			switch (gameModel.getDyingCounter() / 4)
			{
				case 0:
				{
					bitmapToLoad = skin.getPacmanDie1();
					break;
				}
				case 1:
				{
					bitmapToLoad = skin.getPacmanDie2();
					break;
				}
				case 2:
				{
					bitmapToLoad = skin.getPacmanDie3();
					break;
				}
				case 3:
				{
					bitmapToLoad = skin.getPacmanDie4();
					break;
				}
				case 4:
				{
					bitmapToLoad = skin.getPacmanDie5();
					break;
				}
				case 5:
				{
					bitmapToLoad = skin.getPacmanDie6();
					break;
				}
			}
		}
		else
		{
			if (gameModel.getHero().isAlive())
			{
				
				switch (gameModel.getHero().getDirection())
				{
					case Entity.DIRECTION_NONE:
					case Entity.DIRECTION_DOWN:
					{
						switch (gameModel.getHero().getState())
						{
							case 0:
							{
								bitmapToLoad = skin.getPacmanDown1 ();
								break;
							}
							case 1:
							case 3:
							{
								bitmapToLoad = skin.getPacmanDown2 ();
								break;
							}
							case 2:
							{
								bitmapToLoad = skin.getPacmanDown3 ();
								break;
							}

						}
						break;
					}
					case Entity.DIRECTION_UP:
					{
						switch (gameModel.getHero().getState() % 3)
						{
							case 0:
							{
								bitmapToLoad = skin.getPacmanUp1 ();
								break;
							}
							case 1:
							case 3:
							{
								bitmapToLoad = skin.getPacmanUp2 ();
								break;
							}
							case 2:
							{
								bitmapToLoad = skin.getPacmanUp3 ();
								break;
							}
						}
						break;
					}
					case Entity.DIRECTION_LEFT:
					{
						switch (gameModel.getHero().getState() % 3)
						{
							case 0:
							{
								bitmapToLoad = skin.getPacmanLeft1 ();
								break;
							}
							case 1:
							case 3:
							{
								bitmapToLoad = skin.getPacmanLeft2 ();
								break;
							}
							case 2:
							{
								bitmapToLoad = skin.getPacmanLeft3 ();
								break;
							}
						}
						break;
					}
					case Entity.DIRECTION_RIGHT:
					{
						switch (gameModel.getHero().getState() % 3)
						{
							case 0:
							{
								bitmapToLoad = skin.getPacmanRight1 ();
								break;
							}
							case 1:
							case 3:
							{
								bitmapToLoad = skin.getPacmanRight2 ();
								break;
							}
							case 2:
							{
								bitmapToLoad = skin.getPacmanRight3 ();
								break;
							}
						}
						break;
					}
				}
			}
		}	
		
		if (bitmapToLoad != null)
		{
			canvas.drawBitmap(bitmapToLoad, 
							  mapAlignX + gameModel.getHero().getPositionX() * squareSize + 5 + gameModel.getHero().getInternalStepValueX() * STEP_INCR, 
							  mapAlignY + gameModel.getHero().getPositionY() * squareSize + 5 + gameModel.getHero().getInternalStepValueY() * STEP_INCR, 
					          defaultPaint);
		}
		
	}
	
	public void drawGrid (Canvas canvas)
	{
		int i;
		int j;
		Paint colorRed;
		Bitmap bitmapToLoad;
		colorRed = new Paint();
		colorRed.setColor(Color.RED);
		
		for (i = 0 ; i < gameModel.getMap().getWidth() ; i++)
		{
			for (j = 0 ; j < gameModel.getMap().getHeight() ; j++)
			{		
				//canvas.drawRect(i * squareSize + 1, j * squareSize + 1, (i + 1) * squareSize- 1, (j + 1) * squareSize- 1, colorBlack);
				if (gameModel.getMap().getPart(i, j).hasBorderLeft())
				{
					//Log.i (TAG, "Draw left line for part (" + i + "," + j + ")");
					//canvas.drawLine(i * squareSize, j * squareSize, (i) * squareSize, (j + 1) * squareSize, colorRed);
					canvas.drawBitmap(skin.getWallVertical(),
									  mapAlignX + (i) * squareSize, 
									  mapAlignY + (j) * squareSize, 
									  defaultPaint);
				}
				if (gameModel.getMap().getPart(i, j).hasBorderTop())
				{
					//Log.i (TAG, "Draw top line for part (" + i + "," + j + ")");
					//canvas.drawLine( (i) * squareSize, (j) * squareSize + 1, (i+1) * squareSize, (j) * squareSize + 1, colorRed);

					//canvas.drawLine(i * squareSize, j * squareSize, (i + 1) * squareSize, j * squareSize, colorRed);
					canvas.drawBitmap(skin.getWallHorizontal(), 
							          mapAlignX + (i) * squareSize, 
							          mapAlignY + (j) * squareSize, 
							          defaultPaint);
				}
				if (gameModel.getMap().getPart(i, j).hasBorderRight())
				{
					//Log.i (TAG, "Draw right line for part (" + i + "," + j + ")");
					//canvas.drawLine( (i + 1) * squareSize , j * squareSize, (i + 1) * squareSize - 1, (j + 1 ) * squareSize, colorRed);
					canvas.drawBitmap (skin.getWallVertical(),
							           mapAlignX + (i + 1) * squareSize - 1,
							           mapAlignY + (j) * squareSize, 
							           defaultPaint);

				}
				if (gameModel.getMap().getPart(i, j).hasBorderBottom())
				{
				//	Log.i (TAG, "Draw bottom line for part (" + i + "," + j + ")");
					//canvas.drawLine( (i) * squareSize, (j+1) * squareSize, (i+1) * squareSize, (j+1) * squareSize, colorRed);
					canvas.drawBitmap  (skin.getWallHorizontal(), 
										mapAlignX + (i) * squareSize, 
										mapAlignY + (j+1) * squareSize, defaultPaint);
				}
				if (gameModel.getMap().getPart(i, j).hasPoint())
				{
					bitmapToLoad = skin.getPoint ();

					canvas.drawBitmap (bitmapToLoad, 
							           mapAlignX + i * squareSize + 5, 
							           mapAlignY + j * squareSize + 5, 
							           defaultPaint);
				}
				
				if (gameModel.getMap().getPart(i, j).hasSuperPoint())
				{

					bitmapToLoad = skin.getSuperPoint ();

					canvas.drawBitmap (bitmapToLoad,
							           mapAlignX + i * squareSize + 5, 
							           mapAlignY + j * squareSize + 5,
							           defaultPaint);

				}
				
				if (gameModel.getMap().getPart(i, j).hasSpecialSmall())
				{
					bitmapToLoad = skin.getSpecialSmall();
					canvas.drawBitmap (bitmapToLoad,  
							           mapAlignX + i * squareSize + 5, 
							           mapAlignY + j * squareSize + 5, 
							           defaultPaint);
				}
				
				if (gameModel.getMap().getPart(i, j).hasSpecialMedium())
				{
					bitmapToLoad = skin.getSpecialMedium();
					canvas.drawBitmap (bitmapToLoad,
							           mapAlignX + i * squareSize + 5, 
							           mapAlignY + j * squareSize + 5, 
							           defaultPaint);
				}
				
				if (gameModel.getMap().getPart(i, j).hasSpecialBig())
				{
					bitmapToLoad = skin.getSpecialBig();
					canvas.drawBitmap  (bitmapToLoad, 
										mapAlignX + i * squareSize + 5, 
										mapAlignY + j * squareSize + 5, 
										defaultPaint);
				}
				
			}
		}
	}
	
	public void draw (Canvas canvas)
	{

		drawGrid (canvas);
		for (Ennemy e : gameModel.getEnnemies())
		{
			drawEnnemy (e, canvas);
		
		}

		drawHero (canvas);

		if (gameModel.getHero().getLifes() == 0)
		{
			canvas.drawBitmap(skin.getGameOver(), 
			          (this.size - skin.getGameOver().getWidth()) / 2, 
			          (this.size - skin.getGameOver().getHeight()) / 2, 
			          defaultPaint);
		}
		if (gameModel.isFinished())
		{
			if (gameModel.getCurrentMapIndex() >= (Game.NB_MAPS - 1))
			{
				if (Game.isDemo)
				{
					canvas.drawBitmap(skin.getDemoCompleted(), 
					          (this.size - skin.getDemoCompleted().getWidth()) / 2, 
					          (this.size - skin.getDemoCompleted().getHeight()) / 2, 
					          defaultPaint);
				}
				else
				{
					canvas.drawBitmap(skin.getCompleted(), 
					          (this.size - skin.getCompleted().getWidth()) / 2, 
					          (this.size - skin.getCompleted().getHeight()) / 2, 
					          defaultPaint);
				}
			}
			else
			{
				canvas.drawBitmap(skin.getNextLevel(), 
						(this.size - skin.getNextLevel().getWidth()) / 2, 
						(this.size - skin.getNextLevel().getHeight()) / 2, 
						defaultPaint);
			}
		}
	}
	
}
