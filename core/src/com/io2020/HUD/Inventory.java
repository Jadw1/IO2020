package com.io2020.HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.io2020.entities.Items.Item;
import com.io2020.entities.Player;
import com.io2020.screens.GameScreen;

import java.util.*;

public class Inventory extends Menu {

    public Inventory(Player player) {
        super(32*2.25f, 0, 0.7f, new Texture(Gdx.files.internal("HUD/main_background.png")));
    }

    public void draw(SpriteBatch batch, LinkedHashMap<String, ArrayList<Item>> inventory) {
        super.draw(batch);
        float index = 0.5f;
        for (Map.Entry<String, ArrayList<Item>> e : inventory.entrySet()) {
            ArrayList<Item> value = e.getValue();
            value.get(0).draw(batch, index);
            index += 0.5f;
        }
    }
}
