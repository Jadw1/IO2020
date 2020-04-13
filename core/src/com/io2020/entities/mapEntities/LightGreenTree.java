package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.EntityType;
import com.io2020.map.MapEntity;
import com.io2020.tileSet.TileSet;

public class LightGreenTree extends MapEntity {
    private TextureRegion texture;

    public LightGreenTree(TileSet tileSet, Vector3 position, Box2DWorld box2d) {
        super(EntityType.RESOURCE, position, 64.0f, 64.0f);

        texture = tileSet.getTextureRegion(0, 1, 2, 2);
        body = Box2DHandler.createBody(box2d.world, position, new Vector2(0.0f, 17.0f), 20.0f, 30.0f, BodyDef.BodyType.StaticBody);
        sensor = Box2DHandler.createSensor(box2d.world, position, new Vector2(0.0f, 17.0f), 50.0f, 50.0f, BodyDef.BodyType.DynamicBody);
        hashcode = sensor.getFixtureList().get(0).hashCode();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x - width / 2, position.y, width, height);
    }

    @Override
    public void update(float dt) {

    }
}
