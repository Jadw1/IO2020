package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.io2020.entities.EntityType;
import com.io2020.map.MapEntity;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

public class Rock extends MapEntity
{
    Tile rock;

    public Rock(TileSet tileSet, int x, int y)
    {
        super(EntityType.RESOURCE, x, y);

        rock = tileSet.getTile(1, 8);
    }

    @Override
    public void drawForeground(SpriteBatch batch)
    {
//        drawTile(batch, rock, x, y);
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        drawTile(batch, rock, x, y);
    }

    @Override
    public void update(float dt)
    {

    }
}
