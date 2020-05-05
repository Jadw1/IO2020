package com.io2020.entities.Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Item {
    public Texture texture;
    public itemType type;

    public Item() {
        this.texture = null;
    }

    public void draw(SpriteBatch batch, Vector3 pos, int number) {
        if(texture != null) {
            batch.draw(texture, pos.x, pos.y, 40, 40);
            BitmapFont font = new BitmapFont();
            font.draw(batch, Integer.toString(number), pos.x + 5, pos.y + 10);
        }
    }
}
