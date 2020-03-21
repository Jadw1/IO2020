package com.io2020.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.io2020.game.IOGame;

public abstract class BaseScreen implements Screen {
    protected Stage stage;
    protected OrthographicCamera camera;
    protected SpriteBatch spriteBatch;

    protected IOGame game;

    public BaseScreen(IOGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        stage = new Stage(new ScreenViewport(camera));
        spriteBatch = new SpriteBatch();

        //nie wiem co to robi do konca, czy to potrzebne
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0 , 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }

    @Override
    public void pause() {
        game.setPaused(true);
    }

    @Override
    public void resume() {
        game.setPaused(false);
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}