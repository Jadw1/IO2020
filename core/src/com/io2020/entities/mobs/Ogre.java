package com.io2020.entities.mobs;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Character;
import com.io2020.entities.EntityType;

public class Ogre extends Enemy
{
    public Ogre(Vector3 position, TextureAtlas atlas, Box2DWorld box2d)
    {
        super(position, 64, 64, atlas, "ogre", box2d, new Vector2(32.5f, 15.0f), 20, 20);
        speed = 30.0f;
    }
}
