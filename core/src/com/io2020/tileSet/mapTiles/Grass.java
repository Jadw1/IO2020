package com.io2020.tileSet.mapTiles;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.io2020.tileSet.Tile;

public class Grass extends Tile
{
    public Grass(TextureAtlas atlas)
    {
        texture = atlas.findRegion("grass");
    }
}
