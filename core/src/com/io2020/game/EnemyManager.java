package com.io2020.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Entity;
import com.io2020.entities.Player;
import com.io2020.entities.mobs.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnemyManager {

    private Box2DWorld box2d;
    TextureAtlas textureAtlas;
    private Map<Integer, Enemy> enemies;
    private Player player;

    public EnemyManager(Box2DWorld box2d, TextureAtlas textureAtlas, Player player) {
        this.box2d = box2d;
        this.textureAtlas = textureAtlas;
        this.enemies = new HashMap<>();
        this.player = player;
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

    public Collection<Enemy> getEntities() {
        return enemies.values();
    }
    
    public void update(float dt) {
        Vector3 playerPos = player.getPosition();
        for(Map.Entry<Integer, Enemy> entry: enemies.entrySet()) {
            //entry.getValue().goTo(new Vector2(playerPos.x, playerPos.y), 100.0f);
            //entry.getValue().overwatch(4000.0f);
            entry.getValue().update(dt);
        }
    }
}
