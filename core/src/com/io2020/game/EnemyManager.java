package com.io2020.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Entity;
import com.io2020.entities.Player;
import com.io2020.entities.mobs.*;
import jdk.nashorn.internal.objects.MapIterator;
import org.graalvm.compiler.lir.amd64.vector.AMD64VectorLIRInstruction;

import java.util.*;

public class EnemyManager {

    private Box2DWorld box2d;
    TextureAtlas textureAtlas;
    private Map<Integer, Enemy> enemies;
    private Player player;
    Random r;

    public EnemyManager(Box2DWorld box2d, TextureAtlas textureAtlas, Player player) {
        this.box2d = box2d;
        this.textureAtlas = textureAtlas;
        this.enemies = new HashMap<>();
        this.player = player;
        this.r = new Random(System.currentTimeMillis());
    }

    public Enemy spawnEnemy(EnemyType enemyType, Vector3 position)  {
        Enemy enemy;
        switch(enemyType) {
            case BigDemon:
                enemy = new BigDemon(position, textureAtlas, box2d);
                break;
            case Ogre:
                enemy = new Ogre(position, textureAtlas, box2d);
                break;
            case Chort:
                enemy = new Chort(position, textureAtlas, box2d);
                break;
            case BigZombie:
                enemy = new BigZombie(position, textureAtlas, box2d);
                break;
            case Necromancer:
                enemy = new Necromancer(position, textureAtlas, box2d);
                break;
            default:
                enemy = null;
        }

        //TODO: tego hasha mozna potem poprawic
        enemies.put(enemy.hashCode(), enemy);

        return enemy;
    }

    public Enemy spawnRandom(Vector3 position) {
        EnemyType[] enarr = EnemyType.values();
        int ra = r.nextInt(enarr.length);
        EnemyType e = enarr[ra];

        return spawnEnemy(e, position);
    }

    public Collection<Enemy> getEntities() {
        return enemies.values();
    }
    
    public void update(float dt) {

        Iterator<Map.Entry<Integer,Enemy>> iter = enemies.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer,Enemy> entry = iter.next();
            if(entry.getValue().remove) {
                entry.getValue().removeBodies(box2d);
                box2d.removeEntity(entry.getValue());
                iter.remove();
            } else {
                entry.getValue().update(dt);
            }
        }
    }

    public Map<Integer, Enemy> getEnemies() {
        return enemies;
    }
}
