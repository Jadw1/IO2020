package com.io2020.entities;

import com.badlogic.gdx.math.Vector3;

public abstract class Character extends Entity {

    public Character(EntityType entityType, Vector3 position, float width, float height) {
        super(entityType, position, width, height);
        this.width = width;
        this.height = height;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void move(Vector3 moveBy) {
        position.add(moveBy);
    }
}
