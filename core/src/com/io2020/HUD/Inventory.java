package com.io2020.HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.io2020.entities.Player;
import com.io2020.screens.GameScreen;

public class Inventory extends Menu {

    public Inventory(Player player) {
        super(0, 0, 2, new Texture(Gdx.files.internal("HUD/main_background.png")));

        /*addButtons(3, 10, 1,
                new Texture("HUD/pink_button"),
                new Texture("HUD/selector"), 2);*/
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
