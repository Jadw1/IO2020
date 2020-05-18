    package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.EntityType;
import com.io2020.entities.Inventory.Item;
import com.io2020.entities.Inventory.Wood;
import com.io2020.entities.Player;
import com.io2020.map.MapEntity;

import java.util.ArrayList;
import java.util.Random;

public class LightGreenTree extends MapEntity {

    public LightGreenTree(TextureAtlas atlas, Vector3 position, Box2DWorld box2d) {
        super(EntityType.RESOURCE, position, 64.0f, 64.0f);

        hitPoints = 3;

        texture = atlas.findRegion("tree");
        body = Box2DHandler.createBody(box2d.world, position, new Vector2(0.0f, 17.0f), 12.0f, 30.0f, BodyDef.BodyType.StaticBody, Box2DHandler.OTHER, Box2DHandler.ALL);
        sensor = Box2DHandler.createSensor(box2d.world, position, new Vector2(0.0f, 17.0f), 50.0f, 50.0f, BodyDef.BodyType.DynamicBody, Box2DHandler.OTHER, Box2DHandler.ALL);
        hashcode = sensor.getFixtureList().get(0).hashCode();
    }

    @Override
    protected void giveItemsToPlayer(Player player) {
        super.giveItemsToPlayer(player);

        Random r = new Random();
        int woodNumber = r.nextInt(1)+2; // 2-3
        if(woodNumber > 0) {
            player.addItemsToInventory(new Wood(), woodNumber);
        }
    }
}

