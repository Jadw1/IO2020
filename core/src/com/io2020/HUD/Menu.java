package com.io2020.HUD;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.io2020.entities.Entity;

public class Menu {
    public Vector2 pos;
    public Texture texture;
    public float width;
    public float height;
    public float scale;
    //public MenuState state;
    public float time;
    public float coolDown;
    //public Rectangle hitbox;
    //public ArrayList<Button> buttons;

    public Menu(float x, float y, float scale, Texture texture){
        pos = new Vector2(x,y);
        this.texture = texture;
        width = texture.getWidth() * scale;
        height = texture.getHeight() * scale;
        //buttons = new ArrayList<Button>();
        //hitbox = new Rectangle(x,y,width,height);
        //setActive();
    }
    public void draw(SpriteBatch batch){
        if(texture != null) batch.draw(texture, pos.x, pos.y, width, height);
    }

}
