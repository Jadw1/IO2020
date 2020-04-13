package com.io2020.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Entity;
import com.io2020.entities.Player;
import com.io2020.tileSet.Tile;

import java.util.ArrayList;
import java.util.Iterator;

public class Map {
    private int width, height;
    private float tileWidth, tileHeight;
    private int layerCount;

    private Tile[][][] ground;
    private ArrayList<MapEntity> entities;

    public Map(int layerCount, int width, int height, float tileWidth, float tileHeight) {
        this.layerCount = layerCount;
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        ground = new Tile[layerCount][width][height];
        entities = new ArrayList<>();
    }

    public void placeObject(MapEntity entity) {
        entities.add(entity);
    }

    public void setGround(int x, int y, int layer, Tile groundObject) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            ground[layer][x][y] = groundObject;
        }
        //TODO: throw exception?
    }

    public void draw(SpriteBatch batch) {
        for (int l = 0; l < layerCount; l++) {
            for (int y = height - 1; y >= 0; y--) {
                for (int x = 0; x < width; x++) {
                    if (ground[l][x][y] != null) {
                        batch.draw(ground[l][x][y].getTexture(), x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                    }
                }
            }
        }
    }

    public void update(float dt) {
        for (MapEntity entity : entities) {
            entity.update(dt);
        }

        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                if (ground[1][x][y] != null) {
                    ground[1][x][y].update(dt);
                }
            }
        }
    }

    public void collectEntities(ArrayList<Entity> entitiesArray) {
        entitiesArray.addAll(entities);
    }

    public void clearRemovedEntities(Box2DWorld box2D) {
        Iterator<MapEntity> it = entities.iterator();
        while(it.hasNext()) {
            Entity entity = it.next();

            if (entity.remove) {
                entity.removeBodies(box2D);
                box2D.removeEntity(entity);

                it.remove();
            }
        }
    }

    public ArrayList<MapEntity> getEntities()
    {
        return entities;
    }

    /*public void generateHitboxes(Box2DWorld box2D) {
        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[0].length; j++) {
                if(i < width && j < height && entities[i][j] != null) {
                    MapEntity entity = entities[i][j];
                    if (entity.isBlocking()) {
                        Box2DHandler.createBody(box2D.world, entity.tileHeight, entity.tileWidth, entity.position, BodyDef.BodyType.StaticBody);
                    }
                }
            }
        }
    }*/
}
