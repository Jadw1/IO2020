package com.io2020.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.game.Control;

import java.util.ArrayList;

public class Player extends Character {
    public Body body;
    public TextureRegion currentFrame;
    private float speed = 200.0f;
    private boolean flipped = false;
    private float stateTime = 0;
    private PlayerState state = PlayerState.STANDING;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> hitAnimation;
    private Animation<TextureRegion> runAnimation;

    ArrayList<Entity> interactEntities;

    public Player(Vector3 position, TextureAtlas atlas, Box2DWorld box2d) {
        super(EntityType.PLAYER, position, 32.0f, 32.0f);
        body = Box2DHandler.createBody(box2d.world, position, new Vector2(), 16.0f, 8.0f, BodyDef.BodyType.DynamicBody);
        hashcode = body.getFixtureList().get(0).hashCode();
        sensor = null;

        hitAnimation = new Animation<TextureRegion>(1f / 8f, atlas.findRegions("knight_m_hit_anim"), Animation.PlayMode.LOOP);
        idleAnimation = new Animation<TextureRegion>(1f / 8f, atlas.findRegions("knight_m_idle_anim"), Animation.PlayMode.LOOP);
        runAnimation = new Animation<TextureRegion>(1 / 8f, atlas.findRegions("knight_m_run_anim"), Animation.PlayMode.LOOP);

        currentFrame = idleAnimation.getKeyFrame(stateTime);

        interactEntities = new ArrayList<>();
        box2d.setPlayer(this);
    }

    @Override
    public void draw(SpriteBatch batch) {
        float x = position.x + (flipped ? width / 2 : -width / 2);
        batch.draw(currentFrame, x, position.y, flipped ? -width : width, height);
    }

    public void updateControl(Control control) {
        Vector3 moveVec = new Vector3();

        if (control.up) {
            moveVec.y += 1.0f;
        }
        if (control.down) {
            moveVec.y -= 1.0f;
        }
        if (control.right) {
            moveVec.x += 1.0f;
        }
        if (control.left) {
            moveVec.x -= 1.0f;
        }

        state = (moveVec.x != 0.0f || moveVec.y != 0.0f) ? PlayerState.MOVING : PlayerState.STANDING;
        if (state != PlayerState.STANDING) {
            flipped = moveVec.x < 0.0f;
        }
        body.setLinearVelocity(moveVec.x * speed, moveVec.y * speed);
        position.x = body.getPosition().x;// - width/2;
        position.y = body.getPosition().y - height/4;

        if (control.interact && interactEntities.size() > 0){
            interactEntities.get(0).interact();
        }

        control.interact = false;
    }

    public void updatePlayer(float dt, Control control) {
        update(dt);
        updateControl(control);
    }

    public void update(float dt) {
        stateTime += dt;

        if (state == PlayerState.MOVING) {
            currentFrame = runAnimation.getKeyFrame(stateTime);
        } else {
            currentFrame = idleAnimation.getKeyFrame(stateTime);
        }
    }

    @Override
    public void collision(Entity entity, boolean begin){
        if (begin) {
            interactEntities.add(entity); // enter hitbox
        } else {
            interactEntities.remove(entity); // left hitbox
        }
    }
}
