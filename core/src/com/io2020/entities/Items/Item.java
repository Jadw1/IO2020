package com.io2020.entities.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.Button;

public class Item {
    public Texture texture;
    public itemType type;

    public Item() {
        this.texture = null;
    }

    public void draw(SpriteBatch batch, Button b) {
        if(texture != null) {
            batch.draw(texture, b.getPosition().x, b.getPosition().y, 40, 40);
        }
    }
}
