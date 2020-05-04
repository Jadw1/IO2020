package com.io2020.box2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.io2020.entities.Entity;
import com.io2020.entities.Player;
import com.io2020.map.MapEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class Box2DWorld {
    public World world;
    private Box2DDebugRenderer debugRenderer;
    private HashMap<Integer, Entity> entityHashMap;
    private Player player;

    public Box2DWorld () {
        world = new World(new Vector2(0.0f, 0.0f), true); // no gravity in any direction and doSleep for conserving CPU usage
        debugRenderer = new Box2DDebugRenderer();
        entityHashMap = new HashMap<>();

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                processCollisions(fixtureA, fixtureB, true);
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                processCollisions(fixtureA, fixtureB, false);
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }

        });
    }

    public void tick(OrthographicCamera camera){
        //debugRenderer.render(world, camera.combined); // DEBUG show hitboxes
        world.step(Gdx.app.getGraphics().getDeltaTime(), 6, 2);
        world.clearForces();
    }

    private void processCollisions(Fixture aFixture, Fixture bFixture, boolean begin) {
        Entity entityA = entityHashMap.get(aFixture.hashCode());
        Entity entityB = entityHashMap.get(bFixture.hashCode());

        if (entityA != null && entityB != null) {
            if (aFixture.isSensor() && !bFixture.isSensor()) {
                entityB.collision(entityA, begin);
            } else if (bFixture.isSensor() && !aFixture.isSensor()) {
                entityA.collision(entityB, begin);
            }
        }
    }

    public void addEntity(Entity entity) {
        entityHashMap.put(entity.getHashcode(), entity);
    }

    public void removeEntity(Entity entity) {
        entityHashMap.remove(entity.getHashcode());
    }

    public void populateEntityHashMap(ArrayList<MapEntity> entities) {
        entityHashMap.clear();
        for (Entity entity: entities) {
            entityHashMap.put(entity.getHashcode(), entity);
        }

        entityHashMap.put(player.getHashcode(), player);
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }
}
