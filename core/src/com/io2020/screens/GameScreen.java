package com.io2020.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.io2020.entities.Player;
import com.io2020.entities.World;
import com.io2020.game.IOGame;

public class GameScreen extends BaseScreen {
    private Player player;
    private World world;
    private TextureAtlas atlas;

    public GameScreen(IOGame game) {
        super(game);

        atlas = new TextureAtlas("animation/Knight.pack");

        world = new World();
        stage.addActor(world);

        player = new Player(0.0f,0.0f, atlas);
        stage.addActor(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        stage.act();
        camera.position.set(player.getX(), player.getY(), 0);
    }
}
