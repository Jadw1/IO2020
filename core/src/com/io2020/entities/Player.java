package com.io2020.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import org.graalvm.compiler.loop.InductionVariable;

public class Player extends Image {
    private float speed = 200.0f;
    private boolean flipped = false;
    private float stateTime = 0;
    private PlayerState state = PlayerState.STANDING;

    public TextureRegion currentFrame;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> hitAnimation;
    private Animation<TextureRegion> runAnimation;


    public Player(float start_x, float start_y, TextureAtlas atlas) {
        setPosition(start_x, start_y);

        hitAnimation = new Animation<TextureRegion>(1f / 8f, atlas.findRegions("knight_m_hit_anim"), Animation.PlayMode.LOOP);
        idleAnimation = new Animation<TextureRegion>(1f / 8f, atlas.findRegions("knight_m_idle_anim"), Animation.PlayMode.LOOP);
        runAnimation = new Animation<TextureRegion>(1 / 8f, atlas.findRegions("knight_m_run_anim"), Animation.PlayMode.LOOP);

        currentFrame = idleAnimation.getKeyFrame(stateTime);
    }

    @Override
    public void act(float delta) {
        // w przyszlosci bedziemy chcieli wywolywac tutaj Action.act(float)
        // tutaj tez ogarniamy kolizje
        super.act(delta);

        handleInput(delta);
        animate(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(currentFrame, flipped ? getX() + 32 : getX(), getY(), flipped ? -32 : 32, 32);
    }

    private void animate(float delta) {
        stateTime += delta;

        if (state == PlayerState.MOVING) {
            currentFrame = runAnimation.getKeyFrame(stateTime);
        } else {
            currentFrame = idleAnimation.getKeyFrame(stateTime);
        }
    }

    private void handleInput(float delta) {
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

        flipped = moveVec.x < 0.0f;
        state = (moveVec.x != 0.0f || moveVec.y != 0.0f) ? PlayerState.MOVING : PlayerState.STANDING;
        moveVec = moveVec.nor().scl(speed * delta);
        moveBy(moveVec.x, moveVec.y);
    }
}
