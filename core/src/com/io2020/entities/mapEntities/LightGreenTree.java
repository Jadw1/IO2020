package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
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

    public LightGreenTree(TileSet tileSet, int x, int y, Box2DWorld box2D)
    {
        super(EntityType.RESOURCE, x, y, true);

        leftUp = tileSet.getTile(0, 1);
        leftDown = tileSet.getTile(0, 2);
        rightUp = tileSet.getTile(1, 1);
        rightDown = tileSet.getTile(1, 2);

        int tileSize = 32;
        int offset = 16;
        Box2DHandler.createBody(box2D.world, tileSize, tileSize/2, x*tileSize+2*offset, y*tileSize+offset, BodyDef.BodyType.StaticBody);

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
