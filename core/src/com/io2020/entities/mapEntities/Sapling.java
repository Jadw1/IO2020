package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.io2020.entities.EntityType;
import com.io2020.map.MapEntity;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

public class Sapling extends MapEntity
{
    Tile sapling;

    public Sapling(TileSet tileSet, int x, int y)
    {
        super(EntityType.RESOURCE, x, y);

        sapling = tileSet.getTile(3, 5);
    }

    @Override
    public void drawForeground(SpriteBatch batch)
    {
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        drawTile(batch, sapling, x, y);
    }

    @Override
    public void update(float dt)
    {

    }
}
