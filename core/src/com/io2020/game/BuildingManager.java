package com.io2020.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Inventory.Inventory;
import com.io2020.entities.Inventory.Item;
import com.io2020.entities.Inventory.Pair;
import com.io2020.entities.mapEntities.Buildings.*;
import com.io2020.map.Map;
import com.io2020.map.MapEntity;

import java.util.ArrayList;


public class BuildingManager
{
    private final static int tileSize = 32;

    private final Vector3 playerPosition;
    private final OrthographicCamera camera;
    private final Inventory inventory;
    private final Control control;
    private final Map map;
    private final TextureAtlas atlas;
    private final Box2DWorld box2D;
    private final ShootingManager shootingManager;

    private Item buildingItem;

    public BuildingManager(Vector3 playerPosition, OrthographicCamera camera, Inventory inventory,
                           Control control, Map map, TextureAtlas atlas, Box2DWorld box2D, ShootingManager shootingManager) {
        this.playerPosition = playerPosition;
        this.camera = camera;
        this.inventory = inventory;
        this.control = control;
        this.map = map;
        this.atlas = atlas;
        this.box2D = box2D;
        this.buildingItem = null;
        this.shootingManager = shootingManager;
    }

    public void draw(SpriteBatch batch) {
        if (control.building) {
            Vector3 touchedFile = getTouchedTile();

            batch.draw(buildingItem.texture, touchedFile.x * tileSize,
                    touchedFile.y * tileSize, tileSize, tileSize);
        }
    }

    public void update() {
        if (control.building && control.leftMouseBtn) {
            control.building = false;

            placeObject();
        }
    }

    private Vector3 getTouchedTile() {
//        Vector2 cursorRelativeToCameraMiddle  = new Vector2((Gdx.input.getX() - camera.viewportWidth / 2) / 2,
//                (-Gdx.input.getY() + camera.viewportHeight / 2) / 2);

        Vector2 cursorRelativeToCameraMiddle  = new Vector2((Gdx.input.getX() - camera.viewportWidth / 2) * camera.zoom,
                (-Gdx.input.getY() + camera.viewportHeight / 2) * camera.zoom);

        return new Vector3((int) ((playerPosition.x + cursorRelativeToCameraMiddle.x) / 32),
                (int) ((playerPosition.y + cursorRelativeToCameraMiddle.y) / 32), 0);
    }

    public void craft(Item item) {
        ArrayList<Pair> craftReq = item.getCraftReq();
        boolean available = true;

        for (Pair req: craftReq) {
            if (!inventory.containsX(req.quantity, req.item.type)) {
                System.out.println("You cant't craft "+ item.type + " because you lack " + req.item.type);
                available = false;
                break;
            }
        }
        if (available) {
            for (Pair req: craftReq) {
                inventory.deleteX(req.quantity, req.item.type);
            }
            inventory.addItem(item);
        }
    }

    public void build(Item item) {
        control.building = true;
        buildingItem = item;
    }

    private void placeObject() {
        MapEntity mapEntity = null;
        Vector3 touchedFile = getTouchedTile();
        touchedFile.x *= tileSize;
        touchedFile.x += tileSize / 2;
        touchedFile.y *= tileSize;

        switch (buildingItem.type) {
            case FIREPLACE:
                mapEntity = new Fireplace(atlas, touchedFile, box2D);
                break;
            case WOODEN_WALL:
                mapEntity = new WoodenWall(atlas, touchedFile, box2D);
                break;
            case STONE_WALL:
                mapEntity = new StoneWall(atlas, touchedFile, box2D);
                break;
            case TOWER1:
                mapEntity = new Tower1(buildingItem.texture, touchedFile, box2D, shootingManager);
                break;
            case TOWER2:
                mapEntity = new Tower2(buildingItem.texture, touchedFile, box2D, shootingManager);
                break;
            case TOWER3:
                mapEntity = new Tower3(buildingItem.texture, touchedFile, box2D, shootingManager);
                break;
            case TOWER4:
                mapEntity = new Tower4(buildingItem.texture, touchedFile, box2D, shootingManager);
                break;
        }
        if(inventory.containsX(1, buildingItem.type)) {
            inventory.deleteX(1, buildingItem.type);
            map.placeObject(mapEntity);
        }
    }
}
