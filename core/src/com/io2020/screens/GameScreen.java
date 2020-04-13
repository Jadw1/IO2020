package com.io2020.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Entity;
import com.io2020.entities.Player;
import com.io2020.entities.mapEntities.*;
import com.io2020.entities.mobs.*;
import com.io2020.game.Control;
import com.io2020.game.IOGame;
import com.io2020.map.Map;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.mapTiles.Grass;
import com.io2020.tileSet.mapTiles.Shore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameScreen extends BaseScreen {
    private final Player player;
    private final Map map;
    private final TextureAtlas characterAtlas;
    private final TextureAtlas mapAtlas;
    private final BigDemon bigDemon;

    private final Box2DWorld box2d;

    private final int mapSize = 12;
    private final float tileSize = 32.0f;

    public GameScreen(IOGame game) {
        super(game);

        box2d = new Box2DWorld();

        map = new Map(2, mapSize, mapSize, tileSize, tileSize);
        characterAtlas = new TextureAtlas("animation/characterAnimation.pack");
        mapAtlas = new TextureAtlas("mapAssets.pack");
        player = new Player(new Vector3(), characterAtlas, box2d);
        bigDemon = new BigDemon(new Vector3(), characterAtlas);

        createExampleMap();

        box2d.populateEntityHashMap(map.getEntities());
    }

    @Override
    public void render(float dt) {
        super.render(dt);

        update(dt);
        draw();

        box2d.tick(camera);
        map.clearRemovedEntities(box2d);
    }

    private void update(float dt) {
        player.updatePlayer(dt, control);
        bigDemon.update(dt);
        map.update(dt);

        camera.position.lerp(new Vector3(player.getX(), player.getY(), 0.0f), 0.2f);
        camera.update();
    }

    private void draw() {
        //TODO: moze lepiej ta liste stworzyc raz i tylka ja czyscic? co szybsze?
        ArrayList<Entity> entities = new ArrayList<>();
        map.collectEntities(entities);
        entities.add(player);
        entities.add(bigDemon);
        Collections.sort(entities);

        spriteBatch.begin();
        map.draw(spriteBatch);

        for (Entity entity : entities) {
            entity.draw(spriteBatch);
        }
        spriteBatch.end();

//        debugDraw();
    }

    private void debugDraw() {
        ShapeRenderer debug = new ShapeRenderer();
        debug.setProjectionMatrix(camera.combined);
        debug.setColor(Color.RED);

        debug.begin(ShapeRenderer.ShapeType.Filled);
        debug.circle(player.getX(), player.getY(), 3.0f);
        debug.end();
    }

    public void createExampleMap() {
        Grass grass = new Grass(mapAtlas);
        Shore shoreRight = new Shore(mapAtlas, "ocean_right");
        Shore shoreLeft = new Shore(mapAtlas, "ocean_left");
        Shore shoreDown = new Shore(mapAtlas, "ocean_down");
        Shore shoreUp = new Shore(mapAtlas, "ocean_up");
        Shore shoreRightDown = new Shore(mapAtlas, "ocean_right_down");
        Shore shoreRightUp = new Shore(mapAtlas, "ocean_right_up");
        Shore shoreLeftDown = new Shore(mapAtlas, "ocean_left_down");
        Shore shoreLeftUp = new Shore(mapAtlas, "ocean_left_up");

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map.setGround(i, j, 0, grass);
            }
        }

        for (int i = 1; i < mapSize - 1; i++) {
            map.setGround(i, 0, 1, shoreUp);
            map.setGround(i, mapSize - 1, 1, shoreDown);
        }

        for (int i = 1; i < mapSize - 1; i++) {
            map.setGround(0, i, 1, shoreRight);
            map.setGround(mapSize - 1, i, 1, shoreLeft);
        }

        map.setGround(0, 0, 1, shoreRightUp);
        map.setGround(mapSize - 1, 0, 1, shoreLeftUp);
        map.setGround(0, mapSize - 1, 1, shoreRightDown);
        map.setGround(mapSize - 1, mapSize - 1, 1, shoreLeftDown);

        for (int i = 0; i < 12; i++) {
            float x = MathUtils.random(1.5f * tileSize, (mapSize - 1.5f) * tileSize);
            float y = MathUtils.random(1.5f * tileSize, (mapSize - 1.5f) * tileSize);
            map.placeObject(new Rock(mapAtlas, new Vector3(x, y, 0.0f), box2d));
        }

        for (int i = 0; i < 6; i++) {
            float x = MathUtils.random(2.0f * tileSize, (mapSize - 2.0f) * tileSize);
            float y = MathUtils.random(2.0f * tileSize, (mapSize - 2.0f) * tileSize);
            map.placeObject(new LightGreenTree(mapAtlas, new Vector3(x, y, 0.0f), box2d));
        }

        for (int i = 0; i < 6; i++) {
            float x = MathUtils.random(2.0f * tileSize, (mapSize - 2.0f) * tileSize);
            float y = MathUtils.random(2.0f * tileSize, (mapSize - 2.0f) * tileSize);
            map.placeObject(new Sapling(mapAtlas, new Vector3(x, y, 0.0f), box2d));
        }

        for (int i = 0; i < 3; i++) {
            float x = MathUtils.random(2.0f * tileSize, (mapSize - 2.0f) * tileSize);
            float y = MathUtils.random(2.0f * tileSize, (mapSize - 2.0f) * tileSize);
            map.placeObject(new Fireplace(mapAtlas, new Vector3(x, y, 0.0f), box2d));
        }

        Random r = new Random();

        for (int i = 0; i < 12; i++) {
            float x = MathUtils.random(1.5f * tileSize, (mapSize - 1.5f) * tileSize);
            float y = MathUtils.random(1.5f * tileSize, (mapSize - 1.5f) * tileSize);
            map.placeObject(new Flower(mapAtlas, new Vector3(x, y, 0.0f), box2d, r.nextInt(4) + 1));
        }

    }
}
