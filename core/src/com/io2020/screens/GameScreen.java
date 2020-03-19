package com.io2020.screens;

import com.io2020.entities.Player;
import com.io2020.entities.World;
import com.io2020.game.IOGame;

public class GameScreen extends BaseScreen {
    private Player player;
    private World world;

    public GameScreen(IOGame game) {
        super(game);

        world = new World();
        stage.addActor(world);

        player = new Player(0.0f,0.0f, spriteBatch);
        stage.addActor(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        update();
        spriteBatch.end();
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        stage.act();
        camera.position.set(player.getX(), player.getY(), 0);
    }
}
