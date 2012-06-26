package org.gunnm.pacman.controller;

import java.util.TimerTask;

import org.gunnm.pacman.GameCanvas;
import org.gunnm.pacman.model.Game;

import android.util.Log;
import android.view.View;

public class MainLoop extends TimerTask{
	private final static String TAG = "MainLoop";
	View view;
	Game gameModel;
	
	public MainLoop (Game model, View v)
	{
		this.view = v;
		this.gameModel = model;
	}
	
	public void run() 
	{


	}
	
}
