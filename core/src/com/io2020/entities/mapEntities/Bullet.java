package com.io2020.entities.mapEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Entity;
import com.io2020.entities.EntityType;
import com.io2020.entities.mapEntities.Buildings.Tower;
import com.io2020.map.MapEntity;

import static com.io2020.box2d.Box2DHandler.createBody;
import static com.io2020.box2d.Box2DHandler.createSensor;

public class Bullet extends MapEntity {
    Tower tower;
    Texture texture;
    protected float speed = 2000.0f;
    public Vector2 direction;

    public Bullet(EntityType entityType, Vector3 position, float width, float height, Tower tower) {
        super(entityType, position, width, height);
        this.tower = tower;
        texture = new Texture(Gdx.files.internal("WorldAnimation/fireball32.png"));
        this.direction = new Vector2();
    }

    public void addBox2DBody(Box2DWorld box2d) {
        sensor = createSensor(box2d.world, position, new Vector2(0, 30), 20, 20, BodyDef.BodyType.DynamicBody,
                (short) Box2DHandler.BULLET, (short) Box2DHandler.ENEMIES);
        /*body = createBody(box2d.world, position, new Vector2(0, 30), 10, 10, BodyDef.BodyType.DynamicBody,
                (short) Box2DHandler.BULLET, (short) Box2DHandler.ENEMIES);*/
        hashcode = sensor.getFixtureList().get(0).hashCode();
        box2d.addEntity(this);

    }

    public void addDirection(Vector2 direction) {
        this.direction = direction;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x - width/2, position.y, width, height);
    }

    public void update() {
        sensor.setLinearVelocity(direction.x * speed, direction.y * speed);
        position.x = sensor.getPosition().x;
        position.y = sensor.getPosition().y;
    }

    @Override
    public void collision(Entity entity, boolean begin) {
        if (begin) {
            if(entity.getType() == EntityType.ENEMY) {
                System.out.println("zetknalem sie z wrogiem");
            } else {
                System.out.println("zetknalem sie z czyms");
            }
        } else {
            if(entity.getType() == EntityType.ENEMY) {
                System.out.println("odetknalem sie z wrogiem");
            } else {
                System.out.println("odetknalem sie z czyms");
            }
        }
    }

    public void removeBody() {
        body = null;
    }
}
