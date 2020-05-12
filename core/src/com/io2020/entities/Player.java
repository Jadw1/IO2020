package com.io2020.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Timer;
import com.io2020.box2d.Box2DHandler;
import com.io2020.box2d.Box2DWorld;
import com.io2020.entities.Inventory.*;
import com.io2020.game.Control;

import java.util.ArrayList;

public class Player extends Character {

    public Control controller;
    private Animation<TextureRegion> hitAnimation;
    ArrayList<Entity> interactEntities;
    public Inventory inventory;

    public Player(Vector3 position, Control controller, TextureAtlas atlas, Box2DWorld box2d) {
        super(EntityType.PLAYER, position, 32.0f, 32.0f, atlas, "knight_m");

        hitAnimation = new Animation<TextureRegion>(1f / 8f,
                atlas.findRegions("knight_m_hit_anim"), Animation.PlayMode.LOOP);

        body = Box2DHandler.createBody(box2d.world, position, new Vector2(),
                16.0f, 8.0f, BodyDef.BodyType.DynamicBody);
        hashcode = body.getFixtureList().get(0).hashCode();
        sensor = null;

        interactEntities = new ArrayList<>();
        box2d.setPlayer(this);

        int inventorySize = 22;
        inventory = new Inventory(inventorySize);
        inventory.addItem(new WoodenSword());
        inventory.addItem(new Axe());
        inventory.addItem(new Fireplace());
        inventory.addItem(new Tower1());
        inventory.addItem(new Tower2());
        inventory.addItem(new Tower3());
        inventory.addItem(new Tower4());
        inventory.addItem(new WoodenWall());
        inventory.addItem(new WoodenWall());
        inventory.addItem(new WoodenWall());
        inventory.addItem(new StoneWall());
        inventory.addItem(new StoneWall());
        inventory.addItem(new StoneWall());

        // PRINTF DEBUGGING do wyrzucenia jak bedzie wiadomo ze wszystko dziala
/*        if (inventory.containsX(1, itemType.AXE)) {
            System.out.println("inventory contains 1 axe");
        } else {
            System.out.println("error inventory should contain 1 axe");
        }
        if (!inventory.containsX(2, itemType.AXE)) {
            System.out.println("inventory doesnt contain 2 axes");
        } else {
            System.out.println("error inventory shouldnt contain 2 axes");
        }
        if (inventory.deleteX(2, itemType.AXE)) {
            System.out.println("error: should be able to delete two axes");
        } else {
            System.out.println("correctly couldn't delete two axes");
        }
        if (inventory.deleteX(1, itemType.AXE)) {
            System.out.println("correctly deleted one axe");
        } else {
            System.out.println("error: couldn't delete one axe");
        }
        if (inventory.containsX(0, itemType.AXE)) {
            System.out.println("inventory contains 0 axes");
        }
        inventory.addItem(new Axe());
        System.out.println("added 1 axe");
        if (inventory.containsX(1, itemType.AXE)) {
            System.out.println("inventory contains 1 axe");
        } else {
            System.out.println("error: inventory doesn't contain 1 axe");
        }

        inventory.addItems(new Wood(), 10);
        System.out.println("adding 10 woods");
        if (inventory.containsX(10, itemType.WOOD)) {
            System.out.println("inv contains 10 woods");
        } else {
            System.out.println("error: inv doesnt contain 10 woods");
        }
        if (!inventory.containsX(20, itemType.WOOD)) {
            System.out.println("inv doesnt contain 20 woods");
        } else {
            System.out.println("error: inv shouldn't contain 20 woods");
        }
        if(inventory.deleteX(20, itemType.WOOD)){
            System.out.println("error: deleted 20 woods");
        } else {
            System.out.println("couldnt delete 20 woods");
        }
        if(inventory.deleteX(10, itemType.WOOD)){
            System.out.println("deleted 10 woods");
        } else {
            System.out.println("error: couldnt delete 10 woods");
        }
        if(inventory.containsX(0, itemType.WOOD)){
            System.out.println("inv contains 0 woods");
        } else {
            System.out.println("error: inv doesnt contain 0 woods");
        }
        if(inventory.containsX(10, itemType.WOOD)){
            System.out.println("error: inv contains 10 woods");
        } else {
            System.out.println("inv doesnt contain 10 woods");
        }
        if(inventory.deleteX(10, itemType.WOOD)){
            System.out.println("error: deleted 10 woods");
        } else {
            System.out.println("couldnt delete 10 woods");
        }*/

        this.controller = controller;
    }


    @Override
    public void draw(SpriteBatch batch) {
        float x = position.x + (flipped ? width / 2 : -width / 2);
        batch.draw(currentFrame, x, position.y, flipped ? -width : width, height);
    }

    public void handleControl() {
        if (controller.interact && interactEntities.size() > 0 && controller.allowBlock) {
            hit();
        } else if (!controller.blockControl) {
            Vector3 moveVec = new Vector3();

            if (controller.up) {
                moveVec.y += 1.0f;
            }
            if (controller.down) {
                moveVec.y -= 1.0f;
            }
            if (controller.right) {
                moveVec.x += 1.0f;
            }
            if (controller.left) {
                moveVec.x -= 1.0f;
            }

            state = (moveVec.x != 0.0f || moveVec.y != 0.0f) ? CharacterState.MOVING : CharacterState.STANDING;

            if (state != CharacterState.STANDING) {
                flipped = moveVec.x < 0.0f;
            }
            body.setLinearVelocity(moveVec.x * speed, moveVec.y * speed);
            position.x = body.getPosition().x;
            position.y = body.getPosition().y - height / 4;
        }
    }

    private void hit() {
        controller.block();
        body.setLinearVelocity(0, 0);

        state = CharacterState.HITTING;
        final Player p = this;

        Timer.schedule(new Timer.Task() {
            public void run() {
                controller.blockControl = false;

                if (!interactEntities.isEmpty()) {
                    interactEntities.get(0).interact(p);
                }

                Timer.schedule(new Timer.Task() {
                    public void run() {
                        controller.allowBlock = true;
                    }
                }, 0.2f);
            }
        }, 0.16f);
    }

    public void update(float dt) {
        handleControl();

        stateTime += dt;

        if (state == CharacterState.MOVING) {
            currentFrame = runAnimation.getKeyFrame(stateTime);
        } else if (state == CharacterState.HITTING) {
            currentFrame = hitAnimation.getKeyFrame(stateTime);
        } else {
            currentFrame = idleAnimation.getKeyFrame(stateTime);
        }
    }

    @Override
    public void collision(Entity entity, boolean begin) {
        if (begin) {
            interactEntities.add(entity); // enter hitbox
        } else {
            interactEntities.remove(entity); // left hitbox
        }
    }

    // tutaj tez beda metody do sprawdzania czy cos jest w ekwipunku (czy da sie zbudowac)
    // i zabierania przedmiotow jak sie cos uda zbudowac
    public void addItemsToInventory(Item item, Integer quantity) {
        inventory.addItems(item, quantity);
    }
}
