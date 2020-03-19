package com.io2020.tileSet;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.io2020.map.Coord;

public class Tile {
    private TextureRegion texture;
    private Coord relativeCoord;
    int layer;

    public Tile(TextureRegion texture) {
        this.texture = texture;
    }

    public TextureRegion getTexture() {
        return texture;
    }
}
