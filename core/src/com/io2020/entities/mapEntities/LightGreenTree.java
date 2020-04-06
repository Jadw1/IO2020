package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.EntityType;
import com.io2020.map.MapEntity;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

public class LightGreenTree extends MapEntity
{
    TextureRegion texture;

    public LightGreenTree(TileSet tileSet, Vector3 position) {
        super(EntityType.RESOURCE, position, 32.0f, 32.0f);

        texture = tileSet.getTextureRegion(0, 1, 2, 2);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x - width/2, position.y, width, height);
    }

    @Override
    public void update(float dt)
    {

    }
}
