package com.io2020.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Player;
import com.io2020.entities.mapEntities.Column;
import com.io2020.entities.mapEntities.LightGreenTree;
import com.io2020.entities.mapEntities.Rock;
import com.io2020.game.IOGame;
import com.io2020.map.Map;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

public class GameScreen extends BaseScreen {
    private Player player;
    private Map map;
    private TextureAtlas atlas;

    private Box2DWorld box2d;

    private TileSet tileSet;

    private int mapSize = 10;
    private float tileSize = 32.0f;

    public GameScreen(IOGame game) {
        super(game);

        box2d = new Box2DWorld();

        tileSet = new TileSet("grass_trees.png", 16, 16);
        map = new Map(mapSize, mapSize, tileSize, tileSize); // todo moze tutaj dodac box2d
        atlas = new TextureAtlas("animation/Knight.pack");
        player = new Player(atlas, box2d); // todo i tutaj

        createExampleMap();
    }

    @Override
    public void render(float dt) {
        super.render(dt);

        update(dt);
        draw();

        box2d.tick(camera);
    }

    private void update(float dt) {
        player.update(dt);
        map.update(dt);

        camera.position.lerp(new Vector3(player.getX(), player.getY(), 0.0f), 0.1f);
        camera.update();
    }

    private void draw() {
        spriteBatch.begin();
        map.draw(spriteBatch);

        player.draw(spriteBatch);

        map.drawForeground(spriteBatch);
        spriteBatch.end();
    }

    public void createExampleMap() {
        Tile grass = tileSet.getTile(0, 0);
        Tile water = tileSet.getTile(4, 11);

//        for(int i = 0; i < mapSize; i++) {
//            map.setGround(i, 0, water);
//            map.setGround(i, mapSize - 1, water);
//        }
//        for(int i = 1; i < mapSize - 1; i++) {
//            map.setGround(0, i, water);
//            map.setGround(mapSize - 1, i, water);
//        }
        for(int i = 1; i < mapSize - 1; i++) {
            for(int j = 1; j < mapSize - 1; j++) {
                map.setGround(i, j , grass);
            }
        }

        Rock rock = new Rock(tileSet, 2, 3, box2d);
        LightGreenTree tree = new LightGreenTree(tileSet, 5 ,5, box2d);
        map.placeObject(rock);
        map.placeObject(tree);

    }
}
