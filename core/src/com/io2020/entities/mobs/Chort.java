package com.io2020.entities.mobs;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Character;
import com.io2020.entities.EntityType;

public class Chort extends Enemy
{

    public Chort(Vector3 position, TextureAtlas atlas, Box2DWorld box2d)
    {
        super(position, 32, 32, atlas, "chort", box2d, new Vector2(32.5f, 15.0f), 20, 20);
        speed = 80.0f;
        health = 30;
    }

}
