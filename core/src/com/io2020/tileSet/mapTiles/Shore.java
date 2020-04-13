package com.io2020.tileSet.mapTiles;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.io2020.tileSet.Tile;

public class Shore extends Tile
{
    private Animation<TextureRegion> shoreAnimation;
    private float stateTime = 0;

    public Shore(TextureAtlas atlas, String name)
    {
        shoreAnimation = new Animation<TextureRegion>(5f,
                atlas.findRegions(name), Animation.PlayMode.LOOP);

        texture = shoreAnimation.getKeyFrame(stateTime);
    }

    @Override
    public void update(float dt) {
        stateTime += dt;
        texture = shoreAnimation.getKeyFrame(stateTime);
    }
}
