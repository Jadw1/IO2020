package com.io2020.screens;

import com.io2020.entities.Player;
import com.io2020.game.IOGame;

public class GameScreen extends BaseScreen {

    private Player player;

    public GameScreen(IOGame game) {
        super(game);

        player = new Player(0 ,0);
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
