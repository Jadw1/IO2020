package com.io2020.tileSet;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Tile {
    protected TextureRegion texture;

    public TextureRegion getTexture() {
        return texture;
    }

    public void update(float dt) {};
}
