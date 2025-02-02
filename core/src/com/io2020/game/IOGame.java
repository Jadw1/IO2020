package com.io2020.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.io2020.screens.GameScreen;
import com.io2020.screens.MainMenu;

public class IOGame extends Game {

	public Batch batch;
	private boolean paused = false;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new GameScreen(this));
//		setScreen(new MainMenu(this));
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public void render()
	{
		super.render();
	}
}
