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
        super(32*2, 0, 0.6f, new Texture(Gdx.files.internal("HUD/main_background.png")));
    }

    public void draw(SpriteBatch batch, Player player) {
        super.draw(batch);
        float index = 1;
        Item weapon = player.weapon;
        weapon.draw(batch, index);
        index++;
        ArrayList<ArrayList<Item>> inventory = player.inventory;
        for (int i = 0; i < inventory.size(); i++) {
            inventory.get(i).get(0).draw(batch, index);
            index += 1;
        }
    }
}
