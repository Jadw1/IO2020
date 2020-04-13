package com.io2020.entities.mobs;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.Character;
import com.io2020.entities.EntityType;

public class BigZombie extends Character
{
    public BigZombie(Vector3 position, TextureAtlas atlas)
    {
        super(EntityType.ENEMY, position, 64, 64, atlas, "big_zombie");
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        batch.draw(currentFrame, position.x, position.y, flipped ? -width : width, height);
    }
}
