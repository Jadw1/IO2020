package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.EntityType;
import com.io2020.entities.Items.Item;
import com.io2020.entities.Items.Wood;
import com.io2020.map.MapEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class LightGreenTree extends MapEntity {

    public LightGreenTree(TextureAtlas atlas, Vector3 position, Box2DWorld box2d) {
        super(EntityType.RESOURCE, position, 64.0f, 64.0f);

        hitPoints = 3;

        texture = atlas.findRegion("tree");
        body = Box2DHandler.createBody(box2d.world, position, new Vector2(0.0f, 17.0f), 12.0f, 30.0f, BodyDef.BodyType.StaticBody);
        sensor = Box2DHandler.createSensor(box2d.world, position, new Vector2(0.0f, 17.0f), 50.0f, 50.0f, BodyDef.BodyType.DynamicBody);
        hashcode = sensor.getFixtureList().get(0).hashCode();
    }

    @Override
    protected void addItemsToInventory(LinkedHashMap<String, ArrayList<Item>> inventory) {
        super.addItemsToInventory(inventory);
        Random r = new Random();
        int woodNumber = r.nextInt(1)+2; // 2-3
        System.out.println("Will add " + woodNumber + "to the inventory");

        for(int i = 0 ; i < woodNumber; i++) {
            if(!inventory.containsKey("Wood")) {
                inventory.put("Wood", new ArrayList<Item>());
            }
            inventory.get("Wood").add(new Wood());
            System.out.println("Added wood to the inventory");
        }
    }
}
