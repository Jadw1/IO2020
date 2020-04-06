package com.io2020.box2d;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

public class Box2DHandler {
    public static Body createBody(World world, float height, float width, Vector3 position, BodyDef.BodyType type ) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position.x + width/2, position.y + height/2);
        bodyDef.angle = 0;
        bodyDef.fixedRotation = true;
        bodyDef.type = type; // static 0, kinetic 1, dynamic 2

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, height/2);
        fixtureDef.shape = shape;
        fixtureDef.restitution = 0.4f; // ?

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }
}
