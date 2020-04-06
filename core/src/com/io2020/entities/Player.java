package com.io2020.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;

public class Player extends Character {
    private float speed = 200.0f;
    private boolean flipped = false;
    private float stateTime = 0;
    private PlayerState state = PlayerState.STANDING;
    public Body body;

    public TextureRegion currentFrame;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> hitAnimation;
    private Animation<TextureRegion> runAnimation;


    public Player(TextureAtlas atlas, Box2DWorld box2d) {
        super(EntityType.PLAYER, 32, 32);
        body = Box2DHandler.createBody(box2d.world, 15, 15, position, BodyDef.BodyType.DynamicBody);

        hitAnimation = new Animation<TextureRegion>(1f / 8f, atlas.findRegions("knight_m_hit_anim"), Animation.PlayMode.LOOP);
        idleAnimation = new Animation<TextureRegion>(1f / 8f, atlas.findRegions("knight_m_idle_anim"), Animation.PlayMode.LOOP);
        runAnimation = new Animation<TextureRegion>(1 / 8f, atlas.findRegions("knight_m_run_anim"), Animation.PlayMode.LOOP);

        currentFrame = idleAnimation.getKeyFrame(stateTime);
    }

    @Override
    public void draw(SpriteBatch batch) {
        float x = position.x + (flipped ? width/2 : -width/2);
        batch.draw(currentFrame, x, position.y,  flipped ? -width : width, height);
    }

    public void update(float dt) {
        handleInput(dt);
        animate(dt);
    }

    private void handleInput(float dt) {
        Vector3 moveVec = new Vector3();

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveVec.y += 1.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveVec.y -= 1.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveVec.x += 1.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveVec.x -= 1.0f;
        }

        state = (moveVec.x != 0.0f || moveVec.y != 0.0f) ? PlayerState.MOVING : PlayerState.STANDING;
        if(state != PlayerState.STANDING) {
            flipped = moveVec.x < 0.0f;
        }
        body.setLinearVelocity(moveVec.x*speed, moveVec.y*speed);
        position.x = body.getPosition().x - width/2;
        position.y = body.getPosition().y - height/4;
        //moveVec = moveVec.nor().scl(speed * dt);
        //move(moveVec);
    }

    private void animate(float dt) {
        stateTime += dt;

        if (state == PlayerState.MOVING) {
            currentFrame = runAnimation.getKeyFrame(stateTime);
        } else {
            currentFrame = idleAnimation.getKeyFrame(stateTime);
        }
    }
}
