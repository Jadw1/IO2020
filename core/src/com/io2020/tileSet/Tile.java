package com.io2020.tileSet;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
    private TextureRegion texture;

    public Tile(TextureRegion texture) {
        this.texture = texture;
    }

    public TextureRegion getTexture() {
        return texture;
    }
}
