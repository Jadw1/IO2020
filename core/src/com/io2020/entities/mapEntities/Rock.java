package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.EntityType;
import com.io2020.map.MapEntity;
import com.io2020.tileSet.Tile;
import com.io2020.tileSet.TileSet;

public class Rock extends MapEntity {
    TextureRegion texture;

    public Rock(TileSet tileSet, Vector3 position)
    {
        super(EntityType.RESOURCE, position, 16.0f, 16.0f);

        texture = tileSet.getTextureRegion(1, 8, 1, 1);
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
