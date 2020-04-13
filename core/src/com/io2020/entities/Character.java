package com.io2020.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public abstract class Character extends Entity {

    protected TextureRegion currentFrame;
    protected float speed = 200.0f;
    protected boolean flipped = false;
    protected float stateTime = 0;
    protected CharacterState state = CharacterState.STANDING;
    protected Animation<TextureRegion> idleAnimation;
    protected Animation<TextureRegion> runAnimation;

    public Character(EntityType entityType, Vector3 position, float width,
                     float height, TextureAtlas atlas, String name) {
        super(entityType, position, width, height);
        this.width = width;
        this.height = height;

        idleAnimation = new Animation<TextureRegion>(1f / 8f, atlas.findRegions(name + "_idle_anim"), Animation.PlayMode.LOOP);
        runAnimation = new Animation<TextureRegion>(1 / 8f, atlas.findRegions(name + "_run_anim"), Animation.PlayMode.LOOP);

        currentFrame = idleAnimation.getKeyFrame(stateTime);
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

    public void update(float dt) {
        stateTime += dt;

        if (state == CharacterState.MOVING) {
            currentFrame = runAnimation.getKeyFrame(stateTime);
        } else {
            currentFrame = idleAnimation.getKeyFrame(stateTime);
        }
    }
}
