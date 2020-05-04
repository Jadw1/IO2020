package com.io2020.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Entity;
import com.io2020.entities.Player;
import com.io2020.entities.mapEntities.*;
import com.io2020.entities.mobs.*;
import com.io2020.game.IOGame;
import com.io2020.map.Map;
import com.io2020.tileSet.mapTiles.Grass;
import com.io2020.tileSet.mapTiles.Shore;
import ui.Menu;
import ui.SquareMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameScreen extends BaseScreen {
    private final Player player;
    private final Map map;
    private final TextureAtlas characterAtlas;
    private final TextureAtlas mapAtlas;
    private final BigDemon bigDemon;

    private Matrix4 screenMatrix;
    private final SquareMenu squareMenu;

    private final Box2DWorld box2d;

    private final int mapSize = 12;
    private final float tileSize = 32.0f;

    public GameScreen(IOGame game) {
        super(game);

        box2d = new Box2DWorld();

        map = new Map(2, mapSize, mapSize, tileSize, tileSize);
        characterAtlas = new TextureAtlas("animation/characterAnimation.pack");
        mapAtlas = new TextureAtlas("mapAssets.pack");
        player = new Player(new Vector3(50, 50, 0), control,  characterAtlas, box2d);
        bigDemon = new BigDemon(new Vector3(100, 50, 0), characterAtlas);

        screenMatrix = new Matrix4(spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0,
                control.screenWidth, control.screenHeight));
        squareMenu = new SquareMenu(control);

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
        player.update(dt);
        map.update(dt);
        control.update();

        // Menu Logic
        control.processedClick = squareMenu.checkClick(control.mouseClickPos, control.processedClick);
        control.processedClick = squareMenu.build.checkClick(control.mouseClickPos, control.processedClick);
        squareMenu.checkHover(control.mousePos);

        screenMatrix.setToOrtho2D(0,0, control.screenWidth, control.screenHeight);

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

        spriteBatch.setProjectionMatrix(screenMatrix);
        squareMenu.draw(spriteBatch);

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

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map.setGround(i, j, 0, grass);
            }
        }

        for (int i = 1; i < mapSize - 1; i++) {
            map.setGround(i, 0, 1, new Shore(mapAtlas, "ocean_up", box2d, tileSize * i, 0));
            map.setGround(i, mapSize - 1, 1, new Shore(mapAtlas, "ocean_down", box2d, tileSize * i, mapSize * tileSize));
        }

        for (int i = 1; i < mapSize - 1; i++) {
            map.setGround(0, i, 1, new Shore(mapAtlas, "ocean_right", box2d, 0, tileSize * i));
            map.setGround(mapSize - 1, i, 1, new Shore(mapAtlas, "ocean_left", box2d, mapSize * tileSize, i * tileSize));
        }

        map.setGround(0, 0, 1, new Shore(mapAtlas, "ocean_right_up", box2d, 0, 0));
        map.setGround(mapSize - 1, 0, 1, new Shore(mapAtlas, "ocean_left_up", box2d, mapSize * tileSize, 0));
        map.setGround(0, mapSize - 1, 1, new Shore(mapAtlas, "ocean_right_down", box2d, 0, mapSize * tileSize));
        map.setGround(mapSize - 1, mapSize - 1, 1, new Shore(mapAtlas, "ocean_left_down", box2d, mapSize * tileSize, mapSize * tileSize));

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
