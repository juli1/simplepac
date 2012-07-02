package org.gunnm.pacman.view;

import java.io.IOException;

import org.gunnm.pacman.model.Game;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

public class Sound {
	private Context 			context;
	private SoundPool 			soundPool;
	private AudioManager 		audioManager;
	private Skin				skin;
	private	Game 				gameData;
	private AssetManager 		assetManager;
	private int 				introId;
	private int 				eatId;
	private int 				dyingId;
	private int 				eatBonusId;
	private int 				intermissionId;
	private static final String	TAG = "Sound";
	private static Sound		instance;
	private int					lastAction;
	
	public static Sound getInstance (Context c, Game g, Skin si)
	{
		if (Sound.instance == null)
		{
			Sound.instance = new Sound (c, g, si);
		}
		return Sound.instance;
	}

	public static Sound getInstance ()
	{
		return Sound.instance;
	}
	
	public Sound (Context c, Game g, Skin si)
	{
		context 	= c;
	    soundPool 	= new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
	    gameData 	= g;
	    skin 		= si;
	    audioManager = (AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
	    assetManager = context.getResources().getAssets();
	    lastAction   = Game.ACTION_NONE;
	    loadResources();
	    
	}
	
	public void loadResources ()
	{
		try 
		{
			introId = soundPool.load(assetManager.openFd(skin.getSoundIntro()), 1);
			eatId = soundPool.load(assetManager.openFd(skin.getSoundEat()), 1);
			eatBonusId = soundPool.load(assetManager.openFd(skin.getSoundEatBonus()), 1);
			dyingId = soundPool.load(assetManager.openFd(skin.getSoundDying()), 1);
			intermissionId = soundPool.load(assetManager.openFd(skin.getSoundIntermission()), 1);

		} 
		catch (IOException e) 
		{
			Log.e(TAG, "Error while loading sound" + e.toString());
		}

	}
	
	public void playEat ()
	{
		Log.i(TAG, "Play eat");
		playSound (eatId);
	}
	
	public void playDying ()
	{
		playSound (dyingId);
	}
	

	public void playEatBonus ()
	{
		playSound (eatBonusId);
	}
	
	public void playIntermission ()
	{
		playSound (intermissionId);
	}
	
	public void playIntro ()
	{
		playSound (introId);
	}
	 
	public void playSound (int soundId)
	{
		float streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		streamVolume = streamVolume / audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		soundPool.play(soundId, streamVolume, streamVolume, 1, 0, 1f);
	}
	
	public void reaction ()
	{
		int action;
		action = gameData.getCurrentAction();
		if (action == lastAction)
		{
			return;
		} 
		lastAction = action;
		if (gameData.getCurrentAction() != 0)
			Log.i(TAG, "current action" + gameData.getCurrentAction());
		switch (action)
		{
			case Game.ACTION_EAT:
			{
				playEat();
				break; 
			}
			case Game.ACTION_BONUS:
			{
				playEatBonus();
				break;
			}
			case Game.ACTION_DYING:
			{
				playDying();
				break;
			}
			case Game.ACTION_FINISHED:
			{
				playIntermission();
				break;
			}
		}
	}
	
}
