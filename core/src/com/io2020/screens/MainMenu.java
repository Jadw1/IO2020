package com.io2020.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.io2020.game.IOGame;

public class MainMenu implements Screen
{
    private static final int TITLE_WIDTH = 500;
    private static final int TITLE_HEIGHT = 125;
    private static final int TITLE_BUTTON_Y = 325;

    private static final int NEW_GAME_BUTTON_WIDTH = 200;
    private static final int NEW_GAME_BUTTON_HEIGHT = 40;
    private static final int NEW_GAME_BUTTON_Y = 175;

    private static final int LOAD_GAME_BUTTON_WIDTH = 230;
    private static final int LOAD_GAME_BUTTON_HEIGHT = 40;
    private static final int LOAD_GAME_BUTTON_Y = 110;

    private static final int EXIT_BUTTON_WIDTH = 100;
    private static final int EXIT_BUTTON_HEIGHT = 40;
    private static final int EXIT_BUTTON_Y = 45;

    private OrthographicCamera camera;

    private IOGame game;
    private Texture newGameButtonActive;
    private Texture newGameButtonInactive;
    private Texture loadGameButtonActive;
    private Texture loadGameButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    private Texture titleButton;

    private float primalCameraWidth;
    private float primalCameraHeight;

    public MainMenu (IOGame game)
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        primalCameraWidth = camera.viewportWidth;
        primalCameraHeight = camera.viewportHeight;


        this.game = game;
        newGameButtonActive = new Texture("MainMenu/new_game_button_active.png");
        newGameButtonInactive = new Texture("MainMenu/new_game_button_inactive.png");
        loadGameButtonActive = new Texture("MainMenu/load_game_button_active.png");
        loadGameButtonInactive = new Texture("MainMenu/load_game_button_inactive.png");
        exitButtonActive = new Texture("MainMenu/exit_button_active.png");
        exitButtonInactive = new Texture("MainMenu/exit_button_inactive.png");
        titleButton = new Texture("MainMenu/title.png");
    }

    @Override
    public void show()
    {

    }

    private void drawButton(Texture textureActive, Texture textureInactive, int width,
                            int height, int y, float ratioWidth, float ratioHeight)
    {
        if (Gdx.input.getX() < (camera.position.x - (width / 2) + width) * ratioWidth &&
                Gdx.input.getX() > camera.position.x - (width / 2) * ratioWidth &&
                camera.viewportHeight - Gdx.input.getY() < (y + height) * ratioHeight &&
                camera.viewportHeight - Gdx.input.getY() > y * ratioHeight)
        {
            game.batch.draw(textureActive, camera.position.x - (width / 2), y, width, height);

        }
        else
        {
            game.batch.draw(textureInactive, camera.position.x - (width / 2), y, width, height);

        }
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(255, 255, 255, 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        float ratioWidth = camera.viewportWidth / primalCameraWidth;
        float ratioHeight = camera.viewportHeight / primalCameraHeight;

        game.batch.draw(titleButton, camera.position.x - (TITLE_WIDTH / 2), TITLE_BUTTON_Y, TITLE_WIDTH, TITLE_HEIGHT);

        drawButton(newGameButtonActive, newGameButtonInactive, NEW_GAME_BUTTON_WIDTH,
                NEW_GAME_BUTTON_HEIGHT, NEW_GAME_BUTTON_Y, ratioWidth, ratioHeight);
        drawButton(loadGameButtonActive, loadGameButtonInactive, LOAD_GAME_BUTTON_WIDTH,
                LOAD_GAME_BUTTON_HEIGHT, LOAD_GAME_BUTTON_Y, ratioWidth, ratioHeight);
        drawButton(exitButtonActive, exitButtonInactive, EXIT_BUTTON_WIDTH,
                EXIT_BUTTON_HEIGHT, EXIT_BUTTON_Y, ratioWidth, ratioHeight);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height)
    {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {

    }
}
