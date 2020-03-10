package com.io2020.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Player extends Image {
    private float speed = 200.0f;

    public Player(float start_x, float start_y) {
        super(new Texture("player.png"));

        Texture texture = new Texture("player.png");
        setOrigin(texture.getWidth()/2, texture.getHeight()/2);
        setSize(texture.getWidth(), texture.getHeight());
        setScale(0.3f);
        setPosition(start_x, start_y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        handleInput(delta);
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
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveVec.x -= 1.0f;
        }

        moveVec = moveVec.nor().scl(speed * delta);
        moveBy(moveVec.x, moveVec.y);
    }
}
