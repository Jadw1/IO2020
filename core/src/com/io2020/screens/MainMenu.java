package com.io2020.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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

//    private Texture newGameButtonActive;
//    private Texture newGameButtonInactive;
//    private Texture loadGameButtonActive;
//    private Texture loadGameButtonInactive;
//    private Texture exitButtonActive;
//    private Texture exitButtonInactive;
    private Texture title;

    private float primalCameraWidth;
    private float primalCameraHeight;

    private TextureAtlas atlas;
    private Button newGameButton;
    private Button exitButton;
    private Button loadGameButton;

    public MainMenu (IOGame ioGame)
    {
        super(ioGame);

        atlas = new TextureAtlas("MainMenu/Menu.pack");
        TextureRegionDrawable newGameActiveDrawable = new TextureRegionDrawable(atlas.findRegion("new_game_button_inactive"));
        TextureRegionDrawable newGameInactiveDrawable = new TextureRegionDrawable(atlas.findRegion("new_game_button_active"));
        newGameButton = new Button(newGameActiveDrawable, newGameInactiveDrawable);

        TextureRegionDrawable loadGameActiveDrawable = new TextureRegionDrawable(atlas.findRegion("load_game_button_inactive"));
        TextureRegionDrawable loadGameInactiveDrawable = new TextureRegionDrawable(atlas.findRegion("load_game_button_active"));
        loadGameButton = new Button(loadGameActiveDrawable, loadGameInactiveDrawable);

        TextureRegionDrawable exitActiveDrawable = new TextureRegionDrawable(atlas.findRegion("exit_button_inactive"));
        TextureRegionDrawable exitInactiveDrawable = new TextureRegionDrawable(atlas.findRegion("exit_button_active"));
        exitButton = new Button(exitActiveDrawable, exitInactiveDrawable);

        newGameButton.setSize(NEW_GAME_BUTTON_WIDTH, NEW_GAME_BUTTON_HEIGHT);
        newGameButton.setPosition(0, NEW_GAME_BUTTON_Y);
        loadGameButton.setSize(LOAD_GAME_BUTTON_WIDTH, LOAD_GAME_BUTTON_HEIGHT);
        loadGameButton.setPosition(0, LOAD_GAME_BUTTON_Y);
        exitButton.setSize(EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        exitButton.setPosition(0, EXIT_BUTTON_Y);

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




        primalCameraWidth = camera.viewportWidth;
        primalCameraHeight = camera.viewportHeight;

        title = new Texture("MainMenu/title.png");
    }

    // TAK BYLO WCZESNIEJ I LADNIE SIE RYSOWALO I SKALOWALO
//    private void drawButton(Texture textureActive, Texture textureInactive, int width,
//                            int height, int y, float ratioWidth, float ratioHeight)
//    {
//        if (Gdx.input.getX() < (camera.position.x + (width / 2)) * ratioWidth &&
//                Gdx.input.getX() > (camera.position.x - (width / 2)) * ratioWidth &&
//                camera.viewportHeight - Gdx.input.getY() < (y + height) * ratioHeight &&
//                camera.viewportHeight - Gdx.input.getY() > y * ratioHeight)
//        {
//            game.batch.draw(textureActive, camera.position.x - (width / 2), y, width, height);
//        }
//        else
//        {
//            game.batch.draw(textureInactive, camera.position.x - (width / 2), y, width, height);
//        }
//    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(255, 255, 255, 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float ratioWidth = camera.viewportWidth / primalCameraWidth;
        float ratioHeight = camera.viewportHeight / primalCameraHeight;

        newGameButton.setPosition(camera.position.x - (NEW_GAME_BUTTON_WIDTH * ratioWidth / 2), NEW_GAME_BUTTON_Y);
        loadGameButton.setPosition(camera.position.x - (LOAD_GAME_BUTTON_WIDTH * ratioWidth / 2), LOAD_GAME_BUTTON_Y);
        exitButton.setPosition(camera.position.x - (EXIT_BUTTON_WIDTH * ratioWidth / 2), EXIT_BUTTON_Y);
        newGameButton.setSize(NEW_GAME_BUTTON_WIDTH * ratioWidth, NEW_GAME_BUTTON_HEIGHT * ratioHeight);
        loadGameButton.setSize(LOAD_GAME_BUTTON_WIDTH * ratioWidth, LOAD_GAME_BUTTON_HEIGHT * ratioHeight);
        exitButton.setSize(EXIT_BUTTON_WIDTH * ratioWidth, EXIT_BUTTON_HEIGHT * ratioHeight);

        stage.act();
        stage.draw();

        game.batch.begin();
        game.batch.draw(title, camera.position.x - (TITLE_WIDTH / 2), TITLE_BUTTON_Y, TITLE_WIDTH, TITLE_HEIGHT);
        game.batch.end();

//        drawButton(newGameButtonActive, newGameButtonInactive, NEW_GAME_BUTTON_WIDTH,
//                NEW_GAME_BUTTON_HEIGHT, NEW_GAME_BUTTON_Y, ratioWidth, ratioHeight);
//        drawButton(loadGameButtonActive, loadGameButtonInactive, LOAD_GAME_BUTTON_WIDTH,
//                LOAD_GAME_BUTTON_HEIGHT, LOAD_GAME_BUTTON_Y, ratioWidth, ratioHeight);
//        drawButton(exitButtonActive, exitButtonInactive, EXIT_BUTTON_WIDTH,
//                EXIT_BUTTON_HEIGHT, EXIT_BUTTON_Y, ratioWidth, ratioHeight);
    }
}
