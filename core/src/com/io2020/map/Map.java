package com.io2020.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.io2020.tileSet.Tile;

public class Map {
    private Tile[][] ground;
    private MapEntity[][] entities;
    private int width, height;
    private float tileWidth, tileHeight;

    public Map(int width, int height, float tileWidth, float tileHeight) {
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        ground = new Tile[width][height];
        entities = new MapEntity[width][height];
    }

    public boolean placeObject(MapEntity object) {
        int x = object.x;
        int y = object.y;
        if(x >= 0 && x < width && y >= 0 && y < height) {
            if(entities[x][y] != null) {
                return false;
            }
            entities[x][y] = object;
            object.setTileWidth(tileWidth);
            object.setTileHeight(tileHeight);

            return true;
        }
        return false;
    }

    public void setGround(int x, int y, Tile groundObject) {
        if(x >= 0 && x < width && y >= 0 && y < height) {
            ground[x][y] = groundObject;
        }
        //TODO: throw exception?
    }

    public void draw(SpriteBatch batch) {
        for(int y = height - 1; y >= 0; y--) {
            for(int x = 0; x < width; x++) {
                if(ground[x][y] != null) {
                    batch.draw(ground[x][y].getTexture(), x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                }
                if(entities[x][y] != null) {
                    entities[x][y].draw(batch);
                }
            }
        }
    }

    public void drawForeground(SpriteBatch batch) {
        for(int y = height - 1; y >= 0; y--) {
            for(int x = 0; x < width; x++) {
                if(entities[x][y] != null) {
                    entities[x][y].drawForeground(batch);
                }
            }
        }
    }

    public void update(float dt) {
        for (MapEntity[] entitiesArray: entities) {
            for(MapEntity entity: entitiesArray) {
                if(entity != null) {
                    entity.update(dt);
                }
            }
        }
    }
}
