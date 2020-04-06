package com.io2020.box2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DWorld {
    public World world;
    private Box2DDebugRenderer debugRenderer;

    public Box2DWorld () {
        world = new World(new Vector2(0.0f, 0.0f), true); // no gravity in any direction and doSleep for conserving CPU usage
        debugRenderer = new Box2DDebugRenderer();
    }

    public void tick(OrthographicCamera camera){
        //debugRenderer.render(world, camera.combined); // DEBUG show hitboxes
        world.step(Gdx.app.getGraphics().getDeltaTime(), 6, 2);
        world.clearForces();
    }
}
