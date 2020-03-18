package com.io2020.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.io2020.map.exception.CoordBusyException;
import com.io2020.map.exception.OutOfMapRangeException;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

import java.util.HashMap;

public class Map extends Actor {

    private Tile[][] ground;
    private HashMap<Coord, MapObject> objects;

    private int mapWidth, mapHeight;
    private float tileWidth, tileHeight;

    public Map(int width, int height, float tileWidth, float tileHeight) {
        super();
        ground = new Tile[width][height];
        objects = new HashMap<>();

        mapWidth = width;
        mapHeight = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public void setTile(Tile tile, int x, int y) throws OutOfMapRangeException {
        if(x >= mapWidth || y >= mapHeight || x < 0 || y < 0) {
            throw new OutOfMapRangeException();
        }

        ground[x][y] = tile;
    }

    public void placeMapObject(MapObject object, Coord coord) throws CoordBusyException {
        if(objects.containsKey(coord)) {
            throw new CoordBusyException();
        }

        objects.put(coord, object);
    }

    public MapObject getMapObject(Coord coord) {
        if(objects.containsKey(coord)) {
            return objects.get(coord);
        }

        return null;
    }

    public MapObject removeObject(Coord coord) {
        return objects.remove(coord);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        for(int y = mapHeight - 1; y >= 0; y--) {
            for(int x = 0; x < mapWidth; x++) {
                //check if tile is on on screen?
                batch.draw(ground[x][y].getTexture(), x * tileWidth, y * tileHeight);
            }
        }
    }
}
