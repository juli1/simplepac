package org.gunnm.pacman.view;

import org.gunnm.pacman.model.Ennemy;
import org.gunnm.pacman.model.Entity;
import org.gunnm.pacman.model.Game;
import org.gunnm.pacman.model.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class GameCanvas extends View
{
	private static String TAG = "GameCanvas";
	private int size;
	private Game gameModel;
	private Skin skin;
	private int squareSize;
	
	
	public GameCanvas (Context context, Game gm)
	{
		super (context);
		
		gameModel = gm;
		setMinimumHeight(100);
		setMinimumWidth(100);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
	
		if (display.getHeight() < display.getWidth())
		{
			size = display.getHeight();
		}
		else
		{
			size = display.getWidth();
		}
		squareSize = size / gameModel.getMap().getWidth();
		skin = new BasicSkin (context.getResources().getAssets(), squareSize);
	}
	
	public void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
	{
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
	}
	
	private void drawEnnemy (Ennemy e, Canvas canvas)
	{
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
					case Entity.DIRECTION_DOWN:
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
		}
		
		if (bitmapToLoad != null)
		{
			canvas.drawBitmap(bitmapToLoad, e.getPositionX() * squareSize + 5, e.getPositionY() * squareSize + 5, new Paint());
		}
	}
	
	private void drawHero (Canvas canvas)
	{
		Bitmap bitmapToLoad;
		if (gameModel.getHero().isAlive())
		{
			bitmapToLoad = skin.getPacmanFull ();
			switch (gameModel.getHero().getDirection())
			{
				case Entity.DIRECTION_DOWN:
				{
					switch (gameModel.getHero().getState() % 3)
					{
						case 0:
						{
							bitmapToLoad = skin.getPacmanDown1 ();
							break;
						}
						case 1:
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
			canvas.drawBitmap(bitmapToLoad, 
					          gameModel.getHero().getPositionX() * squareSize + 5, 
					          gameModel.getHero().getPositionY() * squareSize + 5, 
					          new Paint());
					
		}		
	}
	
	public void draw (Canvas canvas)
	{

		int i;
		int j;
		Paint colorRed;
		Bitmap bitmapToLoad;
		colorRed = new Paint();
		colorRed.setColor(Color.RED);

	
		for (Ennemy e : gameModel.getEnnemies())
		{
			drawEnnemy (e, canvas);
		}
	
		drawHero (canvas);
		
		
		for (i = 0 ; i < gameModel.getMap().getWidth() ; i++)
		{
			for (j = 0 ; j < gameModel.getMap().getHeight() ; j++)
			{		
				//canvas.drawRect(i * squareSize + 1, j * squareSize + 1, (i + 1) * squareSize- 1, (j + 1) * squareSize- 1, colorBlack);
				if (gameModel.getMap().getPart(i, j).hasBorderLeft())
				{
					//Log.i (TAG, "Draw left line for part (" + i + "," + j + ")");
					canvas.drawLine(i * squareSize, j * squareSize, (i) * squareSize, (j + 1) * squareSize, colorRed);
				}
				if (gameModel.getMap().getPart(i, j).hasBorderTop())
				{
					//Log.i (TAG, "Draw top line for part (" + i + "," + j + ")");
					canvas.drawLine( (i) * squareSize, (j) * squareSize + 1, (i+1) * squareSize, (j) * squareSize + 1, colorRed);

					canvas.drawLine(i * squareSize, j * squareSize, (i + 1) * squareSize, j * squareSize, colorRed);
				}
				if (gameModel.getMap().getPart(i, j).hasBorderRight())
				{
					//Log.i (TAG, "Draw right line for part (" + i + "," + j + ")");
					canvas.drawLine( (i + 1) * squareSize , j * squareSize, (i + 1) * squareSize - 1, (j + 1 ) * squareSize, colorRed);
				}
				if (gameModel.getMap().getPart(i, j).hasBorderBottom())
				{
				//	Log.i (TAG, "Draw bottom line for part (" + i + "," + j + ")");
					canvas.drawLine( (i) * squareSize, (j+1) * squareSize, (i+1) * squareSize, (j+1) * squareSize, colorRed);
				}
				if (gameModel.getMap().getPart(i, j).hasPoint())
				{
					bitmapToLoad = skin.getPoint ();

					canvas.drawBitmap(bitmapToLoad, i * squareSize + 5, j * squareSize + 5, new Paint());
				}
				
				if (gameModel.getMap().getPart(i, j).hasSuperPoint())
				{

					bitmapToLoad = skin.getBonus1 ();

					canvas.drawBitmap(bitmapToLoad, i * squareSize + 5, j * squareSize + 5, new Paint());

				}
				
			}
		}
		
	}
	
	public void setModel (Game model)
	{
		this.gameModel = model;
	}
}
