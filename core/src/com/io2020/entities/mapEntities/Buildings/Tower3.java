package com.io2020.entities.mapEntities.Buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.EntityType;
import com.io2020.game.ShootingManager;
import com.io2020.map.MapEntity;

public class Tower3 extends Tower {
    protected float stateTime = 0;

    public Tower3(Texture texture, Vector3 position, Box2DWorld box2d, ShootingManager shootingManager) {
        super(EntityType.BUILDING, position, 32, 32, shootingManager);

        hitPoints = 1000;

        this.texture = new TextureRegion(texture);

        body = Box2DHandler.createBody(box2d.world, position, new Vector2(0.0f, 8.0f), width - 4.0f, 8.0f, BodyDef.BodyType.StaticBody, Box2DHandler.BUILDING, Box2DHandler.ALL);
        sensor = Box2DHandler.createSensor(box2d.world, position, new Vector2(0.0f, 10.0f), width + 10.0f, 30, BodyDef.BodyType.DynamicBody, Box2DHandler.BUILDING, Box2DHandler.ALL);
        hashcode = sensor.getFixtureList().get(0).hashCode();

        box2d.addEntity(this);
    }


    @Override
    public void update(float dt) {
        super.update(dt);
        stateTime += dt;
    }
}
