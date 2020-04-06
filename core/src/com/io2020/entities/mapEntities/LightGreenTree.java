package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.io2020.entities.EntityType;
import com.io2020.map.MapEntity;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

public class LightGreenTree extends MapEntity
{
    Tile leftUp;
    Tile leftDown;
    Tile rightUp;
    Tile rightDown;

    public LightGreenTree(TileSet tileSet, int x, int y)
    {
        super(EntityType.RESOURCE, x, y);

        leftUp = tileSet.getTile(0, 1);
        leftDown = tileSet.getTile(0, 2);
        rightUp = tileSet.getTile(1, 1);
        rightDown = tileSet.getTile(1, 2);

    }

    @Override
    public void drawForeground(SpriteBatch batch)
    {
        drawTile(batch, leftDown, x, y);
        drawTile(batch, leftUp, x, y + 1);
        drawTile(batch, rightDown, x + 1, y);
        drawTile(batch, rightUp, x + 1, y + 1);
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        drawTile(batch, leftDown, x, y);
        drawTile(batch, leftUp, x, y + 1);
        drawTile(batch, rightDown, x + 1, y);
        drawTile(batch, rightUp, x + 1, y + 1);
    }

    @Override
    public void update(float dt)
    {

    }
}
