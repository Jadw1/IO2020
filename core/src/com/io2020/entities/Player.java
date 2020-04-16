package com.io2020.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Timer;
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

    private Control controller;
    private Animation<TextureRegion> hitAnimation;
    ArrayList<Entity> interactEntities;

    public Player(Vector3 position, Control controller, TextureAtlas atlas, Box2DWorld box2d) {
        super(EntityType.PLAYER, position, 32.0f, 32.0f, atlas, "knight_m");

        hitAnimation = new Animation<TextureRegion>(1f / 8f,
                atlas.findRegions("knight_m_hit_anim"), Animation.PlayMode.LOOP);

        body = Box2DHandler.createBody(box2d.world, position, new Vector2(),
                16.0f, 8.0f, BodyDef.BodyType.DynamicBody);
        hashcode = body.getFixtureList().get(0).hashCode();
        sensor = null;

        interactEntities = new ArrayList<>();
        box2d.setPlayer(this);

        this.controller = controller;
    }


    @Override
    public void draw(SpriteBatch batch) {
        float x = position.x + (flipped ? width / 2 : -width / 2);
        batch.draw(currentFrame, x, position.y, flipped ? -width : width, height);
    }

    public void handleControl() {
        if(controller.interact && interactEntities.size() > 0 && controller.allowBlock) {
            hit();
        }
        else if(!controller.blockControl) {
            Vector3 moveVec = new Vector3();

            if(controller.up) {
                moveVec.y += 1.0f;
            }
            if(controller.down) {
                moveVec.y -= 1.0f;
            }
            if(controller.right) {
                moveVec.x += 1.0f;
            }
            if(controller.left) {
                moveVec.x -= 1.0f;
            }

            state = (moveVec.x != 0.0f || moveVec.y != 0.0f) ? CharacterState.MOVING : CharacterState.STANDING;

            if(state != PlayerState.STANDING) {
                flipped = moveVec.x < 0.0f;
            }
            body.setLinearVelocity(moveVec.x * speed, moveVec.y * speed);
            position.x = body.getPosition().x;
            position.y = body.getPosition().y - height / 4;
        }
    }

    private void hit() {
        controller.block();
        body.setLinearVelocity(0, 0);

        state = CharacterState.HITTING;

        Timer.schedule(new Timer.Task() {
            public void run() {
                controller.blockControl = false;
                interactEntities.get(0).interact();

                Timer.schedule(new Timer.Task() {
                    public void run() {
                        controller.allowBlock = true;
                    }
                }, 0.2f);
            }
        }, 0.16f);
    }

    public void update(float dt) {
        handleControl();

        stateTime += dt;

        if(state == PlayerState.MOVING) {
            currentFrame = runAnimation.getKeyFrame(stateTime);
        }
        else if(state == PlayerState.HITTING) {
            currentFrame = hitAnimation.getKeyFrame(stateTime);
        }
        else {
            currentFrame = idleAnimation.getKeyFrame(stateTime);
        }
    }

    @Override
    public void collision(Entity entity, boolean begin) {
        if(begin) {
            interactEntities.add(entity); // enter hitbox
        }
        else {
            interactEntities.remove(entity); // left hitbox
        }
    }
}
