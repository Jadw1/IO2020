package com.io2020.screens;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.io2020.entities.Player;
import com.io2020.game.IOGame;
import com.io2020.map.Map;
import com.io2020.map.exception.OutOfMapRangeException;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

public class GameScreen extends BaseScreen {
    private Player player;
    private Map map;

    private TileSet tileSet;

    private int mapSize = 6;
    private float tileSize = 16.0f;

    public GameScreen(IOGame game) {
        super(game);
        tileSet = new TileSet("test2.png", 16.0f, 16.0f);

        map = new Map(mapSize, mapSize, tileSize, tileSize);
        player = new Player(0.0f,0.0f);

        stage.addActor(map);
        stage.addActor(player);

        createMap();
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

    private void createMap() {
        Tile tileA = tileSet.getTile(0, 0);
        Tile tileB = tileSet.getTile(1, 0);

        for(int x = 0; x < mapSize; x++) {
            for(int y = 0; y < mapSize; y++) {
                try {
                    map.setTile((y % 2 == 0) ? tileA : tileB, x, y);
                }
                catch(OutOfMapRangeException e) {}
            }
        }
    }
}
