package com.io2020.tileSet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/*
 * Tile (0, 0) is in top left corner.
 * X axis goes left, Y axis goes down.
 */
public class TileSet {
    private Texture tileSetTexture;
    private float tileWidth;
    private float tileHeight;

    public TileSet(String internalPath, float tileWidth, float tileHeight) {
        tileSetTexture = new Texture(internalPath);
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public Tile getTile(int x, int y) {
        float u = x * tileWidth / tileSetTexture.getWidth();
        float v = y * tileHeight / tileSetTexture.getHeight();
        float u2 =  (x+1) * tileWidth / tileSetTexture.getWidth();
        float v2 =  (y+1) * tileHeight / tileSetTexture.getHeight();
        return new Tile(new TextureRegion(tileSetTexture, u, v, u2, v2));
    }
}
