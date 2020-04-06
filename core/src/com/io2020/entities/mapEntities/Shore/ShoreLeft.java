package com.io2020.entities.mapEntities.Shore;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.io2020.entities.EntityType;
import com.io2020.map.MapEntity;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

public class ShoreLeft extends MapEntity
{
    Tile water;

    public ShoreLeft(TileSet tileSet, int x, int y)
    {
        super(EntityType.RESOURCE, x, y);

        water = tileSet.getTile(5, 1);
    }

    @Override
    public void drawForeground(SpriteBatch batch)
    {
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        drawTile(batch, water, x, y);
    }

    @Override
    public void update(float dt)
    {

    }
}
