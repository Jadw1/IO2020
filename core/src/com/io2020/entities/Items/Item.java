package com.io2020.entities.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Item {
    public Texture texture;
    public itemType type;

    public Item() {
        this.texture = null;
    }

    public void draw(SpriteBatch batch, float index) {
        if(texture != null) {
            float tileSize = 32;
            float x = 1 + index;
            float y = 0.2f;
            batch.draw(texture, x * tileSize, y * tileSize, tileSize*2/3, tileSize*2/3);
        }
    }
}
