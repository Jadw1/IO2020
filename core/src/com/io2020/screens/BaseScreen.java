package com.io2020.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.io2020.game.Control;
import com.io2020.game.IOGame;

public abstract class BaseScreen implements Screen {

    static final float CAM_ZOOM = 0.5f;
    static final float MIN_ZOOM = 0.2f;
    static final float MAX_ZOOM = 0.7f;

    protected OrthographicCamera camera;
    protected SpriteBatch spriteBatch;
    protected Control control;

    protected IOGame game;

    public BaseScreen(IOGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.zoom = CAM_ZOOM;
        spriteBatch = new SpriteBatch();

        control = new Control(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        Gdx.input.setInputProcessor(control);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {
        handleInput();
        camera.update();

        Gdx.gl.glClearColor(0, 0, 0, 0);
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
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    private void handleInput() {
        if (control.zoomIn) {
            camera.zoom += 0.02;
        }
        if (control.zoomOut) {
            camera.zoom -= 0.02;
        }
        if (control.left) {
            camera.translate(-3, 0, 0);
        }
        if (control.right) {
            camera.translate(3, 0, 0);
        }
        if (control.down) {
            camera.translate(0, -3, 0);
        }
        if (control.up) {
            camera.translate(0, 3, 0);
        }

        camera.zoom = MathUtils.clamp(camera.zoom, MIN_ZOOM, MAX_ZOOM);
        control.zoom = camera.zoom;
    }
}
