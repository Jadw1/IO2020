package com.io2020.entities.mobs;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.Character;
import com.io2020.entities.EntityType;

public class Necromancer extends Character
{
    public Necromancer(Vector3 position, TextureAtlas atlas)
    {
        super(EntityType.ENEMY, position, 32, 32, atlas, "necromancer");
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        batch.draw(currentFrame, position.x, position.y, flipped ? -width : width, height);
    }
}
