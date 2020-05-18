package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.EntityType;
import com.io2020.entities.Inventory.*;
import com.io2020.entities.Player;
import com.io2020.map.MapEntity;

import java.util.ArrayList;
import java.util.Random;

public class Rock extends MapEntity {

    public Rock(TextureAtlas atlas, Vector3 position, Box2DWorld box2d) {
        super(EntityType.RESOURCE, position, 16.0f, 16.0f);
        hitPoints = 5;

        texture = atlas.findRegion("rock");
        body = Box2DHandler.createBody(box2d.world, position, new Vector2(0.0f, 6.0f), width - 4.0f, 6.0f, BodyDef.BodyType.StaticBody);
        sensor = Box2DHandler.createSensor(box2d.world, position, new Vector2(0.0f, 6.0f), width + 20.0f, height + 22.0f, BodyDef.BodyType.DynamicBody);
        hashcode = sensor.getFixtureList().get(0).hashCode();
    }


    @Override
    protected void giveItemsToPlayer(Player player) {
        Random r = new Random();
        int flintsNumber = r.nextInt(1)+1; // 1-2
        int stoneNumber = 3;
        int goldNumber = (r.nextInt(10) == 0) ? 1 : 0; // 10% chance of getting a gold nugget

        if(stoneNumber > 0 ) {
            player.addItemsToInventory(new Stone(), stoneNumber);
        }
        if(goldNumber > 0) {
            player.addItemsToInventory(new Gold(), goldNumber);
        }
        if(flintsNumber > 0) {
            player.addItemsToInventory(new Flint(), flintsNumber);
        }
    }
}