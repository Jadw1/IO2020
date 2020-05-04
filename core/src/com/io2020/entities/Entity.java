package com.io2020.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Items.Item;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class Entity implements Comparable<Entity> {

    protected EntityType type;

    protected Vector3 position;

    protected float width, height;
    transient protected Body body;
    transient protected Body sensor;
    protected int hashcode;
    public boolean remove;
    public int hitPoints;

    public Entity(EntityType entityType, Vector3 position, float width, float height) {
        this.type = entityType;
        this.position = position.cpy();
        this.width = width;
        this.height = height;
    }

    @Override
    public int compareTo(Entity entity) {
        return (position.z > entity.position.z) ? 1 : ((position.y > entity.position.y) ? -1 : 1);
    }

    public abstract void draw(SpriteBatch batch);

    public void interact(Player player){

    }

    public void collision(Entity entity, boolean begin){}

    public Vector3 getPosition() {
        return position.cpy();
    }

    public int getHashcode()
    {
        return hashcode;
    }

    public abstract void update(float dt);

    public void removeBodies(Box2DWorld box2D) {
        if (sensor != null) box2D.world.destroyBody(sensor);
        if (body != null) box2D.world.destroyBody(body);
    }
}
