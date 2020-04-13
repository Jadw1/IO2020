package com.io2020.map;

import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.Entity;
import com.io2020.entities.EntityType;

public abstract class MapEntity extends Entity {

    public MapEntity(EntityType entityType, Vector3 position, float width, float height) { // default value of blocking = false
        super(entityType, position, width, height);
    }

    public void interact() {
        hitPoints--;
        if (hitPoints == 0) {
            //TODO: tutaj mozna np wrzucic item ze zniszczonego enitity do ekwipunku
            remove = true;
        }
    }
}
