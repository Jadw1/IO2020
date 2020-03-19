package com.io2020.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LayerElement {

    public TextureRegion texture;
    public Coord coord;

    public LayerElement(TextureRegion texture, Coord coord) {
        this.texture = texture;
        this.coord = coord;
    }
}
