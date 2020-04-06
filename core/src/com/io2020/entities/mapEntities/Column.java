package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.io2020.entities.EntityType;
import com.io2020.map.MapEntity;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

public class Column extends MapEntity {

    Tile floor;
    Tile down;
    Tile up;


    public Column(TileSet tileSet, int x, int y) {
        super(EntityType.RESOURCE, x, y);

        floor = tileSet.getTile(5, 7);
        down = tileSet.getTile(5, 6);
        up = tileSet.getTile(5, 5);
    }

    @Override
    public void drawForeground(SpriteBatch batch) {
        drawTile(batch, up, x, y + 1);
    }

    @Override
    public void draw(SpriteBatch batch) {
        //rysowanie za pomocą drawTile to tylko jeden ze sposobów
        //można tez rysowac tak jak np. player
        //pozycje mozna policzyc za pomocą x, y, tileWidth, tileHeight
        drawTile(batch, down, x, y);
        drawTile(batch, floor, x, y - 1);
    }

    @Override
    public void update(float dt) {

    }



}
