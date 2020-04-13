package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.EntityType;
import com.io2020.map.MapEntity;

public class Fireplace extends MapEntity
{
    private final Animation<TextureRegion> fireAnimation;
    protected float stateTime = 0;

    public Fireplace(TextureAtlas atlas, Vector3 position, Box2DWorld box2d) {
        super(EntityType.BUILDING, position, 16, 32);

        hitPoints = 1000;

        fireAnimation = new Animation<TextureRegion>(1 / 8f,
                atlas.findRegions("fireplace"), Animation.PlayMode.LOOP);
        texture = fireAnimation.getKeyFrame(stateTime);

        body = Box2DHandler.createBody(box2d.world, position, new Vector2(-8.0f, 10.0f), width - 4.0f, 10.0f, BodyDef.BodyType.StaticBody);
        sensor = Box2DHandler.createSensor(box2d.world, position, new Vector2(-8.0f, 10.0f), width + 10.0f, 30, BodyDef.BodyType.DynamicBody);
        hashcode = sensor.getFixtureList().get(0).hashCode();
    }

    @Override
    public void update(float dt) {
        stateTime += dt;
        texture = fireAnimation.getKeyFrame(stateTime);
    }
}
