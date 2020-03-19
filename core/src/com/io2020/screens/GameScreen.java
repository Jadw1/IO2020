package com.io2020.screens;

import com.io2020.entities.Player;
import com.io2020.game.IOGame;
import com.io2020.map.Coord;
import com.io2020.map.Ground;
import com.io2020.map.Map;
import com.io2020.map.TileEntity;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

import java.util.ArrayList;

public class GameScreen extends BaseScreen {
    private Player player;
    private Map map;

    private TileSet tileSet;

    private int mapSize = 6;
    private float tileSize = 16.0f;

    public GameScreen(IOGame game) {
        super(game);
        tileSet = new TileSet("test3.png", 16.0f, 16.0f);

        map = new Map(mapSize, mapSize, tileSize, tileSize);
        player = new Player(0.0f,0.0f);

        map.setStage(stage); //this must be first!
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
        Tile grassTile = tileSet.getTile(0, 0);
        Tile waterTile = tileSet.getTile(1, 0);

        ArrayList<TileEntity> grassTiles = new ArrayList<>();
        for(int x = 0; x < 5; x++) {
            for(int y = 0; y < 5; y++) {
                grassTiles.add(new TileEntity(grassTile, new Coord(x, y), 0));
            }
        }
        Ground grass = new Ground();
        grass.setTileEntities(grassTiles.toArray(new TileEntity[grassTiles.size()]));
        map.setGround(0, 0, grass);


        TileEntity[] water = new TileEntity[5];
        for(int i = 0; i < 5; i++) {
            water[i] = new TileEntity(waterTile, new Coord(0, i), 0);
        }
        Ground river = new Ground();
        river.setTileEntities(water);
        map.setGround(3, 0, river);
    }
}
