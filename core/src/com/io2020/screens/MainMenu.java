/*package com.io2020.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.io2020.game.IOGame;

public class MainMenu extends BaseScreen
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

    private static final int WORLD_WIDTH = 640;
    private static final int WORLD_HEIGHT = 480;

    private Texture title;

    private TextureAtlas atlas;
    private Button newGameButton;
    private Button exitButton;
    private Button loadGameButton;

    public MainMenu (IOGame ioGame)
    {
        super(ioGame);

        stage = new Stage(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("MainMenu/Menu.pack");

        newGameButton = makeButton("new_game_button_inactive", "new_game_button_active");
        configButton(newGameButton, NEW_GAME_BUTTON_WIDTH, NEW_GAME_BUTTON_HEIGHT, NEW_GAME_BUTTON_Y);

        loadGameButton = makeButton("load_game_button_inactive", "load_game_button_active");
        configButton(loadGameButton, LOAD_GAME_BUTTON_WIDTH, LOAD_GAME_BUTTON_HEIGHT, LOAD_GAME_BUTTON_Y);

        exitButton = makeButton("exit_button_inactive", "exit_button_active");
        configButton(exitButton, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT, EXIT_BUTTON_Y);

        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game));
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                Gdx.app.exit();
            }
        });

        stage.addActor(newGameButton);
        stage.addActor(loadGameButton);
        stage.addActor(exitButton);

        title = new Texture("MainMenu/title.png");
    }

    private Button makeButton(String active, String inactive)
    {
        TextureRegionDrawable activeDrawable = new TextureRegionDrawable(atlas.findRegion(active));
        TextureRegionDrawable inactiveDrawable = new TextureRegionDrawable(atlas.findRegion(inactive));
        return new Button(activeDrawable, inactiveDrawable);
    }

    private void configButton(Button button, int width, int height, int y)
    {
        button.setSize(width, height);
        button.setPosition((WORLD_WIDTH - width) / 2, y);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(255, 255, 255, 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        game.batch.begin();
        game.batch.draw(title, camera.position.x - (TITLE_WIDTH / 2), TITLE_BUTTON_Y, TITLE_WIDTH, TITLE_HEIGHT);
        game.batch.end();
    }
}
*/