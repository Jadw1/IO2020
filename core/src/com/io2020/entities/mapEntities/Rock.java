package com.io2020.entities.mapEntities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.EntityType;
import com.io2020.entities.Items.Flint;
import com.io2020.entities.Items.Gold;
import com.io2020.entities.Items.Item;
import com.io2020.entities.Items.Stone;
import com.io2020.map.MapEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

    protected void addItemsToInventory(LinkedHashMap<String, ArrayList<Item>> inventory) {
        Random r = new Random();
        int flintsNumber = r.nextInt(1)+1; // 1-2
        int stoneNumber = 3;
        int goldNumber = (r.nextInt(9) == 9) ? 1 : 0; // 10% chance of getting a gold nugget

        for(int i = 0 ; i < flintsNumber; i++) {
            if(!inventory.containsKey("Flint")) {
                inventory.put("Flint", new ArrayList<Item>());
            }
            inventory.get("Flint").add(new Flint());
        }
        for(int i = 0 ; i < stoneNumber; i++) {
            if(!inventory.containsKey("Stone")) {
                inventory.put("Stone", new ArrayList<Item>());
            }
            inventory.get("Stone").add(new Stone());
        }
        for(int i = 0; i < goldNumber; i++) {
            if(!inventory.containsKey("Gold")) {
                inventory.put("Gold", new ArrayList<Item>());
            }
            inventory.get("Gold").add(new Gold());
        }

    }
}
