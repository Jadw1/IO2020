package com.io2020.entities.mapEntities.Buildings;

import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.EntityType;
import com.io2020.entities.mapEntities.Bullet;
import com.io2020.game.ShootingManager;
import com.io2020.map.MapEntity;

public class Tower extends MapEntity {

    Integer tick;
    ShootingManager shootingManager;

    public Tower(EntityType entityType, Vector3 position, float width, float height, ShootingManager shootingManager) {
        super(entityType, position, width, height);
        this.shootingManager = shootingManager;
        tick = 0;
    }

    @Override
    public void update(float dt) {
        tick = (tick+1)%60;
        if(tick == 1) {
            shootingManager.addBullet(new Bullet(EntityType.BULLET, new Vector3(this.position.x, this.position.y, 0), 30, 30, this));
        }
    }
}