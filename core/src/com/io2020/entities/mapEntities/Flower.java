package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.EntityType;
import com.io2020.entities.Inventory.Item;
import com.io2020.entities.Inventory.PickedFlower;
import com.io2020.entities.Player;
import com.io2020.map.MapEntity;

import java.util.ArrayList;

public class Flower extends MapEntity
{
    public Flower(TextureAtlas atlas, Vector3 position, Box2DWorld box2d, int type)
    {
        super(EntityType.RESOURCE, position, 16.0f, 16.0f);

        hitPoints = 1;

        texture = atlas.findRegion("flower" + type);
        sensor = Box2DHandler.createSensor(box2d.world, position, new Vector2(0.0f, 8.0f), width + 20.0f, height + 10.0f, BodyDef.BodyType.DynamicBody);
        hashcode = sensor.getFixtureList().get(0).hashCode();

    }
    @Override
    protected void giveItemsToPlayer(Player player) {
        super.giveItemsToPlayer(player);

        int flowerNumber = 1;
        player.addItemsToInventory(new PickedFlower(), flowerNumber);
    }
}
