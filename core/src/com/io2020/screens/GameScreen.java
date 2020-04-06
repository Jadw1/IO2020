package com.io2020.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Player;
import com.io2020.entities.mapEntities.LightGreenTree;
import com.io2020.entities.mapEntities.Rock;
import com.io2020.entities.mapEntities.Sapling;
import com.io2020.entities.mapEntities.Shore.*;
import com.io2020.game.IOGame;
import com.io2020.map.Map;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

public class GameScreen extends BaseScreen
{
    private Player player;
    private Map map;
    private TextureAtlas atlas;

    private Box2DWorld box2d;

    private TileSet tileSet;
    private TileSet tileSetWater;

    private int mapSize = 12;
    private float tileSize = 32.0f;

    public GameScreen(IOGame game)
    {
        super(game);

        box2d = new Box2DWorld();

        tileSet = new TileSet("grass_trees.png", 16, 16);
        tileSetWater = new TileSet("water.png", 16, 16);
        map = new Map(mapSize, mapSize, tileSize, tileSize); // todo moze tutaj dodac box2d
        atlas = new TextureAtlas("animation/Knight.pack");
        player = new Player(atlas, box2d); // todo i tutaj

        createExampleMap();
    }

    @Override
    public void render(float dt)
    {
        super.render(dt);

        update(dt);
        draw();

        box2d.tick(camera);
    }

    private void update(float dt)
    {
        player.update(dt);
        map.update(dt);

        camera.position.lerp(new Vector3(player.getX(), player.getY(), 0.0f), 0.1f);
        camera.update();
    }

    private void draw()
    {
        spriteBatch.begin();
        map.draw(spriteBatch);

        player.draw(spriteBatch);

        map.drawForeground(spriteBatch);
        spriteBatch.end();
    }

    public void createExampleMap()
    {
        Tile grass = tileSet.getTile(0, 0);

        for (int i = 0; i < mapSize; i++)
        {
            for (int j = 0; j < mapSize; j++)
            {
                map.setGround(i, j, grass);
            }
        }

        for (int i = 1; i < mapSize - 1; i++)
        {
            map.placeObject(new ShoreDown(tileSetWater, i, 0));
            map.placeObject(new ShoreUp(tileSetWater, i, mapSize - 1));
        }

        for (int i = 1; i < mapSize - 1; i++)
        {
            map.placeObject(new ShoreRight(tileSetWater, 0, i));
            map.placeObject(new ShoreLeft(tileSetWater, mapSize - 1, i));
        }

        map.placeObject(new ShoreLeftDown(tileSetWater, 0, 0));
        map.placeObject(new ShoreRightDown(tileSetWater, mapSize - 1, 0));
        map.placeObject(new ShoreLeftUp(tileSetWater, 0, mapSize - 1));
        map.placeObject(new ShoreRightUp(tileSetWater, mapSize - 1, mapSize - 1));

        map.placeObject(new Sapling(tileSet, 7, 1));
        map.placeObject(new Sapling(tileSet, 8, 1));
        map.placeObject(new Sapling(tileSet, 9, 2));
        map.placeObject(new Sapling(tileSet, 3, 4));
        map.placeObject(new Sapling(tileSet, 3, 2));
        map.placeObject(new LightGreenTree(tileSet, 5, 5, box2d));
        map.placeObject(new LightGreenTree(tileSet, 6, 6, box2d));
        map.placeObject(new LightGreenTree(tileSet, 9, 8, box2d));
        map.placeObject(new LightGreenTree(tileSet, 9, 4, box2d));
        map.placeObject(new LightGreenTree(tileSet, 5, 8, box2d));
        map.placeObject(new Rock(tileSet, 7, 7, box2d));
        map.placeObject(new Rock(tileSet, 9, 6, box2d));
        map.placeObject(new Rock(tileSet, 2, 3, box2d));

    }
}
