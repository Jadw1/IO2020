package com.io2020.screens;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.io2020.entities.Player;
import com.io2020.game.IOGame;
import com.io2020.map.Map;

public class GameScreen extends BaseScreen {
    private Player player;
    private Map map;

    public GameScreen(IOGame game) {
        super(game);

        map = new Map(5, 5, 16.0f);
        player = new Player(0.0f,0.0f);

        stage.addActor(map);
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
