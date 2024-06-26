package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.CharacterController;
import com.scramble_like.game.component.controller.ProjectileController;
import com.scramble_like.game.essential.CoreConstant;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventHit;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.chunk.ChunkManager;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.boss_fight.boss.Boss;
import com.scramble_like.game.game_object.enemy.Enemy;

import java.util.EventObject;

public abstract class Projectile extends GameObject
{
    protected boolean generatedByPlayer;
    protected int damage;
    protected float cooldown;
    protected ProjectileController projectileController;

    public Projectile(String name, Scene scene, Vector2 start, Vector2 direction, float range, float speed, boolean generatedByPlayer) throws SceneIsNullException
    {
        super(name, scene);
        this.generatedByPlayer = generatedByPlayer;
        this.damage = 50;
        this.cooldown = 0.1f;
        this.getTransform().setLocation(start);
        this.AddComponent(new AABBCollider(50, 50, false, true));
        this.projectileController = new ProjectileController(start.cpy(), direction, range, speed);
        this.AddComponent(projectileController);
        this.getTransform().setZIndex(CoreConstant.MIN_Z_INDEX + 1);
    }

    /*public Projectile(String name, Scene scene, Vector2 start, Vector2 end, float speed) throws SceneIsNullException
    {
        super(name, scene);
        this.damage = 50;
        this.cooldown = 0.1f;
        this.getTransform().setLocation(start);
        this.AddComponent(new AABBCollider(25, 25, false, true));
        this.projectileController = new ProjectileController(start.cpy(), end.cpy(), speed);
        this.AddComponent(projectileController);
    }*/

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();

        this.getEventDispatcher().AddListener(EventIndex.HIT, new EventListener() {
            @Override
            public void handleEvent(EventObject event) {
                EventHit e = (EventHit) event;
                if (e.otherGameObject instanceof ChunkManager) { DestroyThisInScene(); }
                if (!GameConstant.GOD_MODE && e.otherGameObject instanceof Player && !generatedByPlayer)
                {
                    DestroyThisInScene();
                    e.otherGameObject.GetFirstComponentFromClass(CharacterController.class).takeDamage(damage, cooldown);
                }
                if (e.otherGameObject instanceof Enemy && generatedByPlayer)
                {
                    DestroyThisInScene();
                    e.otherGameObject.GetFirstComponentFromClass(CharacterController.class).takeDamage(damage, cooldown);
                }
                if (e.otherGameObject instanceof Boss && generatedByPlayer)
                {
                    DestroyThisInScene();
                    Boss boss = (Boss) e.otherGameObject;
                    boss.TakeDamage(damage);
                }
            }
        });

        this.getEventDispatcher().AddListener(EventIndex.PROJECTILE_REACHED_DESTINATION, new EventListener() { @Override public void handleEvent(EventObject event) { DestroyThisInScene(); } });
    }
}