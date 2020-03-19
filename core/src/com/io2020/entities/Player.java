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

public class Player extends Actor
{
    private float speed = 200.0f;
    private boolean flipped = false;
    private SpriteBatch batch;
    private float stateTime = 0;

    private TextureAtlas idleAtlas;
    private Animation<TextureRegion> idleAnimation;
    private TextureAtlas hitAtlas;
    private Animation<TextureRegion> hitAnimation;
    private TextureAtlas runAtlas;
    private Animation<TextureRegion> runAnimation;


    public Player(float start_x, float start_y, SpriteBatch batch) {
//        super(new Texture("player.png"));
        this.batch = batch;
        Texture texture = new Texture("player.png");
        setOrigin(texture.getWidth()/2, texture.getHeight()/2);
        setSize(texture.getWidth(), texture.getHeight());
        setScale(0.3f);
        setPosition(start_x, start_y);

        idleAtlas = new TextureAtlas(Gdx.files.internal("animation/knight_idle.atlas"));
        idleAnimation = new Animation<TextureRegion>(1/30f, idleAtlas.getRegions());

        runAtlas = new TextureAtlas(Gdx.files.internal("animation/knight_run.atlas"));
        runAnimation = new Animation<TextureRegion>(1/30f, runAtlas.getRegions());

        hitAtlas = new TextureAtlas(Gdx.files.internal("animation/knight_hit.atlas"));
        hitAnimation = new Animation<TextureRegion>(1/30f, hitAtlas.getRegions());

    }

    @Override
    public void act(float delta) {
        // w przyszlosci bedziemy chcieli wywolywac tutaj Action.act(float)
        // tutaj tez ogarniamy kolizje
        super.act(delta);

        animate(delta);
        handleInput(delta);
    }

    private void animate(float delta)
    {
        stateTime += delta;
        batch.begin();
        batch.draw(idleAnimation.getKeyFrame(stateTime), getX(), getY(), 100, 100);
        batch.end();
    }

    private void handleInput(float delta) {
        Vector2 moveVec = new Vector2(0.0f, 0.0f);
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveVec.y += 1.0f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveVec.y -= 1.0f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveVec.x += 1.0f;
            flipped = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveVec.x -= 1.0f;
            flipped = false;
        }

        moveVec = moveVec.nor().scl(speed * delta);
        moveBy(moveVec.x, moveVec.y);
    }
}
