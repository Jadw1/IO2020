package com.io2020.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;

public class Player extends Character {
    private float speed = 200.0f;
    private boolean flipped = false;
    private float stateTime = 0;
    private PlayerState state = PlayerState.STANDING;

    public TextureRegion currentFrame;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> hitAnimation;
    private Animation<TextureRegion> runAnimation;


    public Player(TextureAtlas atlas) {
        super(EntityType.PLAYER, 32, 32);

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

    @Override
    public void update(float dt) {
        handleInput(dt);
        animate(dt);
    }

    private void handleInput(float dt) {
        Vector2 moveVec = new Vector2(0.0f, 0.0f);

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
        moveVec = moveVec.nor().scl(speed * dt);
        move(moveVec);
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
