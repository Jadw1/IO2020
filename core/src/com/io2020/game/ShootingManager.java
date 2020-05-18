package com.io2020.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.mapEntities.Bullet;
import com.io2020.entities.mobs.Enemy;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

public class ShootingManager {
    private final ArrayList<Bullet> bulletsShot;
    private Map<Integer, Enemy> enemies;
    private final Box2DWorld box2d;

    public ShootingManager(Map<Integer, Enemy> enemies, Box2DWorld box2d) {
        this.bulletsShot = new ArrayList<>();
        this.enemies = enemies;
        this.box2d = box2d;
    }

    public void addBullet(Bullet bullet) {
        bullet.addBox2DBody(box2d);
        bulletsShot.add(bullet);
        Vector3 nearestEnemy = findNearestEnemy(bullet, enemies);
        // teoretycznie lepsze ale cos zbugowane
/*        Vector2 direction = new Vector2();
        if((nearestEnemy.x - bullet.getPosition().x) != 0) {
            System.out.println("halo1");
            direction = new Vector2(1.0f, (nearestEnemy.y - bullet.getPosition().y)/Math.abs(nearestEnemy.x - bullet.getPosition().x));
        } else if((nearestEnemy.y - bullet.getPosition().y) != 0) {
            System.out.println("halo2");
            direction = new Vector2((nearestEnemy.x - bullet.getPosition().x)/Math.abs(nearestEnemy.y - bullet.getPosition().y), 1.0f);
        }
        bullet.addDirection(direction);*/
        bullet.addDirection(new Vector2(nearestEnemy.x - bullet.getPosition().x, nearestEnemy.y - bullet.getPosition().y));

    }

    public void update() {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for(Bullet bullet : bulletsShot) {
            bullet.update();
            if(bullet.getPosition().x > 12*32.0f || bullet.getPosition().x < 0
                    || bullet.getPosition().y > 12*32.0f || bullet.getPosition().y < 0 || bullet.remove) {
                bulletsToRemove.add(bullet);
                bullet.removeBodies(box2d);
            }
        }
        bulletsShot.removeAll(bulletsToRemove);
    }

    public ArrayList<Bullet> getBulletsShot() {
        return bulletsShot;
    }

    private static Vector3 findNearestEnemy(Bullet bullet, Map<Integer, Enemy> enemies) {
        Vector3 ans = new Vector3();
        float x = bullet.getPosition().x;
        float y = bullet.getPosition().y;
        double curr = Double.MAX_VALUE;
        double alterntive = 0;
        for(Map.Entry<Integer, Enemy> e : enemies.entrySet()) {
            alterntive = Math.sqrt(Math.pow(x - e.getValue().getPosition().x, 2) + Math.pow(y - e.getValue().getPosition().y, 2));
            if(alterntive < curr) {
                curr = alterntive;
                ans = e.getValue().getPosition();
            }

        }
        return ans;
    }

    /*private static boolean collidesWithEnemies(Bullet bullet, Map<Integer, Enemy> enemies) {
        for(Map.Entry<Integer, Enemy> e : enemies.entrySet()) {
            if(collides(e.getValue().getPosition(), e.getValue().getWidth(), e.getValue().getHeight(), bullet.getPosition(), bullet.getWidth(), bullet.getHeight())){
                e.getValue().gotHit(bullet);
                bullet.collision();
            }
        }
        return false;
    }
    private static boolean collides(Vector3 pos1, float w1, float h1, Vector3 pos2, float w2, float h2) {
        if (RectA.X1 < RectB.X2 && RectA.X2 > RectB.X1 &&
                RectA.Y1 < RectB.Y2 && RectA.Y2 > RectB.Y1)
            return false;
    }*/
}
