package com.io2020.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.io2020.tileSet.Tile;

public class TileEntity {
    private Tile tile;
    public int relX, relY;

    public TileEntity(Tile tile, int relX, int relY) {
        this.tile = tile;
        this.relX = relX;
        this.relY = relY;
    }

    public TextureRegion getTexture() {
        return tile.getTexture();
    }
}
