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
	
	public void draw (Canvas canvas)
	{
		Paint colorBlack;
		Paint colorBlue;
		Paint colorRed;
		Paint colorGreen;
		Paint colorYellow;
		Paint colorCyan;
		Paint colorMagenta;
		Paint colorHero;
		Bitmap bitmapToLoad;
		int i;
		int j;
		
		colorBlack = new Paint ();
		colorBlue = new Paint ();
		colorGreen = new Paint ();
		colorRed = new Paint ();
		colorYellow = new Paint ();
		colorCyan = new Paint ();
		colorMagenta = new Paint ();

		colorBlack.setColor(Color.BLACK);
		colorBlue.setColor(Color.BLUE);
		colorRed.setColor(Color.RED);
		colorGreen.setColor(Color.GREEN);
		colorYellow.setColor(Color.YELLOW);
		colorCyan.setColor(Color.CYAN);
		colorMagenta.setColor(Color.MAGENTA);
		/*
		 * Draw a yellow rectangle for Pacman
		 */
		if (gameModel.getHero().isAlive())
		{
			if (! gameModel.getHero().isVulnerable())
			{
				colorHero = colorRed;
			}
			else
			{
				colorHero = colorYellow;
			}
			/*
			canvas.drawRect(gameModel.getHero().getPositionX() * squareSize , 
							gameModel.getHero().getPositionY() * squareSize,
							(gameModel.getHero().getPositionX() + 1) * squareSize,
							(gameModel.getHero().getPositionY() + 1) * squareSize,
							colorHero);
							*/
			canvas.drawBitmap(skin.getPacmanFull (), 
					          gameModel.getHero().getPositionX() * squareSize + 5, 
					          gameModel.getHero().getPositionY() * squareSize + 5, 
					          colorYellow);
					
		}
		
		
		for (Ennemy e : gameModel.getEnnemies())
		{
			bitmapToLoad = skin.getPacmanFull ();
			if (e.isAlive())
			{
				switch (e.getDirection())
				{
					case Entity.DIRECTION_LEFT:
					{
						bitmapToLoad = skin.getEnnemyLeft1 ();
						break;
					}
					case Entity.DIRECTION_RIGHT:
					{
						bitmapToLoad = skin.getEnnemyRight1 ();
						break;
					}
					case Entity.DIRECTION_UP:
					{
						bitmapToLoad = skin.getEnnemyUp1 ();
						break;
					}
					case Entity.DIRECTION_DOWN:
					{
						bitmapToLoad = skin.getEnnemyDown1 ();
						break;
					}
				}
				canvas.drawBitmap(bitmapToLoad, e.getPositionX() * squareSize + 5, e.getPositionY() * squareSize + 5, colorYellow);
			}
		}
//		Log.i (TAG, "Draw hero at (" + gameModel.getHero().getPositionX()  + "," + gameModel.getHero().getPositionY()  + ")");

		
		for (i = 0 ; i < gameModel.getMap().getWidth() ; i++)
		{
			for (j = 0 ; j < gameModel.getMap().getHeight() ; j++)
			{		
				//canvas.drawRect(i * squareSize + 1, j * squareSize + 1, (i + 1) * squareSize- 1, (j + 1) * squareSize- 1, colorBlack);
				if (gameModel.getMap().getPart(i, j).hasBorderLeft())
				{
					//Log.i (TAG, "Draw left line for part (" + i + "," + j + ")");
					canvas.drawLine(i * squareSize, j * squareSize, (i) * squareSize, (j + 1) * squareSize, colorBlue);
				}
				if (gameModel.getMap().getPart(i, j).hasBorderTop())
				{
					//Log.i (TAG, "Draw top line for part (" + i + "," + j + ")");
					canvas.drawLine( (i) * squareSize, (j) * squareSize + 1, (i+1) * squareSize, (j) * squareSize + 1, colorYellow);

					canvas.drawLine(i * squareSize, j * squareSize, (i + 1) * squareSize, j * squareSize, colorYellow);
				}
				if (gameModel.getMap().getPart(i, j).hasBorderRight())
				{
					//Log.i (TAG, "Draw right line for part (" + i + "," + j + ")");
					canvas.drawLine( (i + 1) * squareSize , j * squareSize, (i + 1) * squareSize - 1, (j + 1 ) * squareSize, colorGreen);
				}
				if (gameModel.getMap().getPart(i, j).hasBorderBottom())
				{
				//	Log.i (TAG, "Draw bottom line for part (" + i + "," + j + ")");
					canvas.drawLine( (i) * squareSize, (j+1) * squareSize, (i+1) * squareSize, (j+1) * squareSize, colorRed);
				}
				if (gameModel.getMap().getPart(i, j).hasPoint())
				{
					bitmapToLoad = skin.getPoint ();

					canvas.drawBitmap(bitmapToLoad, i * squareSize + 5, j * squareSize + 5, colorYellow);
				}
				
				if (gameModel.getMap().getPart(i, j).hasSuperPoint())
				{

					bitmapToLoad = skin.getBonus1 ();

					canvas.drawBitmap(bitmapToLoad, i * squareSize + 5, j * squareSize + 5, colorYellow);

				}
				
			}
		}
		
	}
	
	public void setModel (Game model)
	{
		this.gameModel = model;
	}
}
