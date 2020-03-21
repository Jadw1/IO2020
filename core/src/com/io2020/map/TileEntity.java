package com.io2020.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.io2020.tileSet.Tile;

public class TileEntity {
    private Tile tile;
    private Coord relativeCoord;
    private int layer;

    public TileEntity(Tile tile, Coord relativeCoord, int layer) {
        this.tile = tile;
        this.relativeCoord = relativeCoord;
        this.layer = layer;
    }

    public TextureRegion getTexture() {
        return tile.getTexture();
    }

    public Coord getRelativeCoord() {
        return relativeCoord;
    }

    public int getLayer() {
        return layer;
    }

}
