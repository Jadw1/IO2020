package com.io2020.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

public class Box2DHandler {
    public static final short ALL     =~0x0000;
    public static final short PLAYER  = 0x0001;
    public static final short ENEMIES = 0x0002;
    public static final short OTHER   = 0x0004;
    public static final short BULLET  = 0x0008;
    public static final short BUILDING = 0x0010;

    public static Body createBody(World world, Vector3 entityPos, Vector2 offset, float boxWidth, float boxHeight, BodyDef.BodyType type, short category, short mask) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(entityPos.x, entityPos.y).add(offset));
        bodyDef.angle = 0;
        bodyDef.fixedRotation = true;
        bodyDef.type = type; // static 0, kinetic 1, dynamic 2

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(boxWidth / 2.0f, boxHeight / 2.0f);
        fixtureDef.shape = shape;
        fixtureDef.restitution = 0.4f;
        fixtureDef.filter.categoryBits = category;
        fixtureDef.filter.maskBits = mask;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    public static Body createSensor(World world, Vector3 entityPos, Vector2 offset, float boxWidth, float boxHeight, BodyDef.BodyType type, short category, short mask) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(entityPos.x, entityPos.y).add(offset));
        bodyDef.angle = 0;
        bodyDef.fixedRotation = true;
        bodyDef.type = type; // static 0, kinetic 1, dynamic 2

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(boxWidth / 2.0f, boxHeight / 2.0f);
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = category;
        fixtureDef.filter.maskBits = mask;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }
}