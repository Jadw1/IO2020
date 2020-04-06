package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.EntityType;
import com.io2020.map.MapEntity;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

public class Rock extends MapEntity
{
    Tile rock;

    public Rock(TileSet tileSet, int x, int y, Box2DWorld box2d)
    {
        super(EntityType.RESOURCE, x, y, true);

        rock = tileSet.getTile(1, 8);
        int tileSize = 32;
        int offset = 16;
        Box2DHandler.createBody(box2d.world, tileSize/2, tileSize/2, x*tileSize+offset, y*tileSize+offset, BodyDef.BodyType.StaticBody);
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
