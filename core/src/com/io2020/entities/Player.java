package com.io2020.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import org.graalvm.compiler.loop.InductionVariable;

public class Player extends Actor
{
    private float speed = 200.0f;
    private boolean flipped = false;
    private SpriteBatch batch;
    private float stateTime = 0;
    private PlayerState state = PlayerState.STANDING;

    private TextureAtlas idleAtlas;
    private Animation<TextureRegion> idleAnimation;
    private TextureAtlas hitAtlas;
    private Animation<TextureRegion> hitAnimation;
    private TextureAtlas runAtlas;
    private Animation<TextureRegion> runAnimation;


    public Player(float start_x, float start_y, SpriteBatch batch) {
        this.batch = batch;
        Texture texture = new Texture("player.png");
        setOrigin(texture.getWidth()/2, texture.getHeight()/2);
        setSize(texture.getWidth(), texture.getHeight());
        setScale(0.3f);
        setPosition(start_x, start_y);


        idleAtlas = new TextureAtlas(Gdx.files.internal("animation/knight_idle.atlas"));
        idleAnimation = new Animation<TextureRegion>(1f/8f, idleAtlas.getRegions(), Animation.PlayMode.LOOP);

        runAtlas = new TextureAtlas(Gdx.files.internal("animation/knight_run.atlas"));
        runAnimation = new Animation<TextureRegion>(1/8f, runAtlas.getRegions(), Animation.PlayMode.LOOP);

        hitAtlas = new TextureAtlas(Gdx.files.internal("animation/knight_hit.atlas"));
        hitAnimation = new Animation<TextureRegion>(1/30f, hitAtlas.getRegions(), Animation.PlayMode.LOOP);

    }

    @Override
    public void act(float delta) {
        // w przyszlosci bedziemy chcieli wywolywac tutaj Action.act(float)
        // tutaj tez ogarniamy kolizje
        super.act(delta);

        handleInput(delta);
        animate(delta);
    }

    private void animate(float delta)
    {
        TextureRegion currentFrame;

        stateTime += delta;
        if (state == PlayerState.MOVING)
            currentFrame = runAnimation.getKeyFrame(stateTime);
        else
            currentFrame = idleAnimation.getKeyFrame(stateTime);


        batch.draw(currentFrame, flipped ? getX() + 100 : getX(), getY(), flipped ? -100 : 100, 100);
    }

    private void handleInput(float delta) {
        Vector2 moveVec = new Vector2(0.0f, 0.0f);
        state = PlayerState.STANDING;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveVec.y += 1.0f;
            state = PlayerState.MOVING;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveVec.y -= 1.0f;
            state = PlayerState.MOVING;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveVec.x += 1.0f;
            flipped = false;
            state = PlayerState.MOVING;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveVec.x -= 1.0f;
            flipped = true;
            state = PlayerState.MOVING;
        }

        moveVec = moveVec.nor().scl(speed * delta);
        moveBy(moveVec.x, moveVec.y);
    }
}
