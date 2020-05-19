package com.io2020.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Entity;
import com.io2020.entities.EntityType;
import com.io2020.entities.Player;
import com.io2020.entities.mapEntities.*;
import com.io2020.entities.mapEntities.Buildings.Fireplace;
import com.io2020.entities.mobs.*;
import com.io2020.game.BuildingManager;
import com.io2020.game.EnemyManager;
import com.io2020.game.IOGame;
import com.io2020.game.ShootingManager;
import com.io2020.game.Lighting;
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

    private final Matrix4 screenMatrix;
    private final SquareMenu squareMenu;

    private final Box2DWorld box2d;
    private final EnemyManager enemyManager;
    private final Lighting light;
    private final BuildingManager buildingManager;
    private final ShootingManager shootingManager;

    Random r;

    private final int mapSize = 25;
    private final float tileSize = 32.0f;

    public GameScreen(IOGame game) {
        super(game);
        r = new Random(System.nanoTime());
        box2d = new Box2DWorld();

        map = new Map(2, mapSize, mapSize, tileSize, tileSize);
        characterAtlas = new TextureAtlas("animation/characterAnimation.pack");
        mapAtlas = new TextureAtlas("mapAssets.pack");
        player = new Player(new Vector3(tileSize + 16, tileSize + 8, 0), control,  characterAtlas, box2d);
        enemyManager = new EnemyManager(box2d, characterAtlas, player);
        shootingManager = new ShootingManager(enemyManager.getEnemies(), box2d);
        buildingManager = new BuildingManager(player.getPosition(), camera,
                player.inventory, player.controller, map, mapAtlas, box2d, shootingManager);


        screenMatrix = new Matrix4(spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0,
                control.screenWidth, control.screenHeight));
        squareMenu = new SquareMenu(control, player.inventory, buildingManager);

        createExampleMap();

        light = new Lighting();
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
        if(light.update(dt)) {
            spawnEnemies();
        }
        player.update(dt);
        enemyManager.update(dt);
        map.update(dt);
        control.update();
        buildingManager.update();
        shootingManager.update();

        processMenu();

        camera.position.lerp(new Vector3(player.getX(), player.getY(), 0.0f), 0.2f);
        camera.update();
    }

    private void draw() {
        //TODO: moze lepiej ta liste stworzyc raz i tylka ja czyscic? co szybsze?
        ArrayList<Entity> entities = new ArrayList<>();
        map.collectEntities(entities);
        entities.add(player);
        entities.addAll(enemyManager.getEntities());
        entities.addAll(shootingManager.getBulletsShot());
        Collections.sort(entities);

        spriteBatch.begin();
        light.setLight(spriteBatch);
        map.draw(spriteBatch);
        buildingManager.draw(spriteBatch);


        for (Entity entity : entities) {
            if(entity instanceof Fireplace) {
                light.resetLight(spriteBatch);
            }

            entity.draw(spriteBatch);

            if(entity instanceof Fireplace) {
                light.setLight(spriteBatch);
            }
        }

        spriteBatch.setProjectionMatrix(screenMatrix);
        squareMenu.draw(spriteBatch);

        spriteBatch.end();

    }

    private void debugDraw(float x, float y) {
        ShapeRenderer debug = new ShapeRenderer();
        debug.setProjectionMatrix(camera.combined);
        debug.setColor(Color.RED);

        debug.begin(ShapeRenderer.ShapeType.Filled);
        debug.circle(x * tileSize + 16, y * tileSize, 3.0f);
        debug.end();
    }

    public void processMenu() {
//        squareMenu.setPlayersInventory(player.inventory);

        // Menu Logic
        control.processedClick = squareMenu.checkClick(control.mouseClickPos, control.processedClick);
        if (squareMenu.inventory.isActive()) {
            control.processedClick = squareMenu.inventory.checkClick(control.mouseClickPos, control.processedClick);
        }
        else if (squareMenu.crafting.isActive()) {
            control.processedClick = squareMenu.crafting.checkClick(control.mouseClickPos, control.processedClick);
        }
        squareMenu.checkHover(control.mousePos);

        screenMatrix.setToOrtho2D(0,0, control.screenWidth, control.screenHeight);
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

        enemyManager.spawnRandom(new Vector3(100, 50, 0)).follow(player, 100.0f);
        enemyManager.spawnRandom(new Vector3(250, 300, 0)).follow(player, 50.0f);
        enemyManager.spawnRandom(new Vector3(50, 250, 0)).follow(player, 20);
        enemyManager.spawnRandom(new Vector3(300, 50, 0)).follow(player, 50.0f);
        enemyManager.spawnRandom(new Vector3(150, 300, 0)).follow(player, 50.0f);

        for (int i = 0; i < 20; i++) {
            float x = MathUtils.random(1.5f * tileSize, (mapSize - 1.5f) * tileSize);
            float y = MathUtils.random(1.5f * tileSize, (mapSize - 1.5f) * tileSize);
            map.placeObject(new Rock(mapAtlas, new Vector3(x, y, 0.0f), box2d));
        }

        for (int i = 0; i < 15; i++) {
            float x = MathUtils.random(2.0f * tileSize, (mapSize - 2.0f) * tileSize);
            float y = MathUtils.random(2.0f * tileSize, (mapSize - 2.0f) * tileSize);
            map.placeObject(new LightGreenTree(mapAtlas, new Vector3(x, y, 0.0f), box2d));
        }

        for (int i = 0; i < 20; i++) {
            float x = MathUtils.random(2.0f * tileSize, (mapSize - 2.0f) * tileSize);
            float y = MathUtils.random(2.0f * tileSize, (mapSize - 2.0f) * tileSize);
            map.placeObject(new Sapling(mapAtlas, new Vector3(x, y, 0.0f), box2d));
        }

        for (int i = 0; i < 3; i++) {
            float x = MathUtils.random(2.0f * tileSize, (mapSize - 2.0f) * tileSize);
            float y = MathUtils.random(2.0f * tileSize, (mapSize - 2.0f) * tileSize);
            map.placeObject(new Fireplace(mapAtlas, new Vector3(x, y, 0.0f), box2d));
        }



        for (int i = 0; i < 30; i++) {
            float x = MathUtils.random(1.5f * tileSize, (mapSize - 1.5f) * tileSize);
            float y = MathUtils.random(1.5f * tileSize, (mapSize - 1.5f) * tileSize);
            map.placeObject(new Flower(mapAtlas, new Vector3(x, y, 0.0f), box2d, r.nextInt(4) + 1));
        }

    }

    private void spawnEnemies() {
        int am = r.nextInt(6) + 2;
        for(int i = 0; i< am; i++) {
            float x = MathUtils.random(1.5f * tileSize, (mapSize - 1.5f) * tileSize);
            float y = MathUtils.random(1.5f * tileSize, (mapSize - 1.5f) * tileSize);
            enemyManager.spawnRandom(new Vector3(x, y, 0)).follow(player, 50);
        }
    }
}
