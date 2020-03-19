package com.io2020.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.io2020.map.exception.CoordBusyException;
import com.io2020.tileSet.Tile;

public class Map extends Actor {

    private int mapWidth, mapHeight;
    private Ground[][] ground;
    private MapObject[][] objects;

    //TODO: idea: player always collide with objects on layer 1?
    /*
     layer 0 - ground
     layer 1 - behind player
     layer 2 - before player
     */
    private int layersCount = 3;
    private MapLayer[] layers;

    public Map(int width, int height, float tileWidth, float tileHeight) {
        super();

        mapWidth = width;
        mapHeight = height;

        ground = new Ground[mapWidth][mapHeight];
        objects = new MapObject[mapWidth][mapHeight];
        layers = new MapLayer[layersCount];
        initLayers(mapWidth, mapWidth, tileWidth, tileHeight);
    }

    private void initLayers(int width, int height, float tileWidth, float tileHeight) {
        for(int i = 0; i < layers.length; i++) {
            layers[i] = new MapLayer(width, height, tileWidth, tileHeight);
        }
    }

    public void setStage(Stage stage) {
        stage.addActor(this);
        for(int i = 0; i < layers.length; i++) {
            stage.addActor(layers[i]);
        }
        layers[layers.length - 1].setZIndex(Integer.MAX_VALUE);
    }

    public void setObject(int x, int y, MapObject object) throws CoordBusyException {
        if(x >= 0 && x < mapWidth && y >= 0 && y < mapHeight) {
            if(objects[x][y] != null) {
                throw new CoordBusyException();
            }
            objects[x][y] = object;
        }
        //TODO: throw exception?
    }

    public void setGround(int x, int y, Ground groundObject) {
        if(x >= 0 && x < mapWidth && y >= 0 && y < mapHeight) {
            ground[x][y] = groundObject;
        }
        //TODO: throw exception?
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        for(int y = mapHeight - 1; y >= 0; y--) {
            for(int x = 0; x < mapWidth; x++) {
                if(ground[x][y] != null) {
                    addTilesToLayers(x, y, ground[x][y].getTileEntities());
                }
                if(objects[x][y] != null) {
                    addTilesToLayers(x, y, objects[x][y].getTileEntities());
                }
            }
        }
    }

    //TODO: what to do if some tile is outside the map? ignore/draw/exception
    //now tiles outside map are drawing
    private void addTilesToLayers(int x, int y, TileEntity[] tileEntities) {
        for(TileEntity tile: tileEntities) {
            Coord rel = tile.getRelativeCoord();
            Coord drawingCoord = new Coord(x + rel.getX(), y + rel.getY());
            layers[tile.getLayer()].addToDrawingQueue(new LayerElement(tile.getTexture(), drawingCoord));
        }
    }
}
