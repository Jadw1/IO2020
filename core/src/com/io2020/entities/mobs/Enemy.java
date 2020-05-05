package com.io2020.entities.mobs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Character;
import com.io2020.entities.Entity;
import com.io2020.entities.EntityType;

import java.util.Random;

public abstract class Enemy extends Character {

    protected Vector2 goTo;
    protected float minDistance;
    protected EnemyAction action;
    protected Vector2 goOverwatch;
    protected Entity toFollow;

    public Enemy(Vector3 position, float width, float height, TextureAtlas atlas, String name, Box2DWorld box2d, Vector2 offset, float colliderWidth, float colliderHeight) {
        super(EntityType.ENEMY, position, width, height, atlas, name);
        action = EnemyAction.OTHER;
       // body = Box2DHandler.createBody(box2d.world, position, offset, colliderWidth, colliderHeight, BodyDef.BodyType.StaticBody);
        //hashcode = body.getFixtureList().get(0).hashCode();
        //sensor = null;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame, position.x, position.y, flipped ? -width : width, height);
    }

    public void goTo(Vector2 position, float minDistance) {
        goTo = position;
        this.minDistance = minDistance;
        action = EnemyAction.GOTO;
    }

    public void overwatch(float distance) {
        goTo = new Vector2(position.x, position.y);
        minDistance = distance;
        action = EnemyAction.OVERWATCH;
        goOverwatch = null;
    }

    public void follow(Entity entity, float minDistance) {
        action = EnemyAction.FOLLOW;
        toFollow = entity;
        this.minDistance = minDistance;
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        Vector2 move = Vector2.Zero;
        switch(action) {
            case OTHER:
                return;

                //dont work!
            case OVERWATCH:
                Random r = new Random(System.currentTimeMillis());
                if(goOverwatch == null) {
                    goOverwatch = new Vector2(r.nextFloat() % minDistance, r.nextFloat() % minDistance).add(goTo);
                }
                else if(goOverwatch.sub(position.x, position.y).len2() <= 5) {
                    goOverwatch = null;
                    return;
                }

                //move = new Vector2(goOverwatch).sub(position.x, position.y).nor().scl(speed * dt);
                break;

            case FOLLOW:
                goTo = new Vector2(toFollow.getPosition().x, toFollow.getPosition().y);

            case GOTO:
                Vector2 pos = new Vector2(goTo).sub(position.x, position.y);

                if(pos.len2() > minDistance) {
                    move = pos.nor().scl(speed * dt);
                }
                else if(action == EnemyAction.GOTO) {
                    action = EnemyAction.OTHER;
                }
                break;
        }

        this.move(new Vector3(move.x, move.y, 0.0f));
    }

    public EnemyAction getAction() {
        return action;
    }
}