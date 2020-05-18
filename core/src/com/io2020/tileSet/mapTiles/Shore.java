package com.io2020.tileSet.mapTiles;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.tileSet.Tile;

public class Shore extends Tile
{
    private Animation<TextureRegion> shoreAnimation;
    private float stateTime = 0;

    transient final private Body body;

    public Shore(TextureAtlas atlas, String name, Box2DWorld box2d, float x, float y)
    {
        shoreAnimation = new Animation<TextureRegion>(1f,
                atlas.findRegions(name), Animation.PlayMode.LOOP);

        body = Box2DHandler.createBody(box2d.world, new Vector3(x + setOffsetX(name), y + setOffsetY(name), 0.0f),
                new Vector2(0.0f, 0.0f), 32.0f, 32.0f, BodyDef.BodyType.StaticBody, Box2DHandler.BUILDING, Box2DHandler.ALL);
        texture = shoreAnimation.getKeyFrame(stateTime);
    }

    private float setOffsetY(String name)
    {
        if (name.contains("down")) return -16;
        else return 16;
    }

    private float setOffsetX(String name)
    {
        if (name.contains("left")) return -16;
        else return 16;
    }

    @Override
    public void update(float dt) {
        stateTime += dt;
        texture = shoreAnimation.getKeyFrame(stateTime);
    }
}
