package com.io2020.entities.mobs;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.Character;
import com.io2020.entities.EntityType;

public class Chort extends Character
{
    public Chort(Vector3 position, TextureAtlas atlas)
    {
        super(EntityType.ENEMY, position, 32, 32, atlas, "chort");
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        batch.draw(currentFrame, position.x, position.y, flipped ? -width : width, height);
    }
}
