package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.PlayerController;
import com.scramble_like.game.component.controller.ProjectileController;
import com.scramble_like.game.component.paper2d.Flipbook;
import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.SpatialGrid;
import com.scramble_like.game.essential.chaos.SphereCollider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventHit;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.chunk.ChunkManager;
import com.scramble_like.game.game_object.Player;

import java.util.EventObject;

public abstract class Projectile extends GameObject
{
    protected int damage;
    protected float cooldown;
    protected ProjectileController projectileController;

    public Projectile(String name, Scene scene, String path, Vector2 start, Vector2 direction, float range, float speed) throws SceneIsNullException
    {
        super(name, scene);
        this.damage = 50;
        this.cooldown = 0.1f;
        this.getTransform().setLocation(start);
        this.AddComponent(new AABBCollider(50, 50, false, true));
        this.projectileController = new ProjectileController(start.cpy(), direction, range, speed);
        this.AddComponent(projectileController);
        this.AddComponent(new Sprite(path));
    }
    public Projectile(String name, Scene scene, String path, Vector2 start, Vector2 direction, float range, float speed, int count) throws SceneIsNullException
    {
        super(name, scene);
        this.damage = 50;
        this.cooldown = 0.1f;
        this.getTransform().setLocation(start);
        this.AddComponent(new AABBCollider(50, 50, false, true));
        this.projectileController = new ProjectileController(start.cpy(), direction, range, speed);
        this.AddComponent(projectileController);
        this.AddComponent(new Flipbook(path, count));
    }



    public Projectile(String name, Scene scene, String path, Vector2 start, Vector2 end, float speed) throws SceneIsNullException
    {
        super(name, scene);
        this.damage = 50;
        this.cooldown = 0.1f;
        this.getTransform().setLocation(start);
        this.AddComponent(new AABBCollider(25, 25, false, true));
        this.projectileController = new ProjectileController(start.cpy(), end.cpy(), speed);
        this.AddComponent(projectileController);
        this.AddComponent(new Sprite(path));
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();

        this.getEventDispatcher().AddListener(EventIndex.HIT, new EventListener() {
            @Override
            public void handleEvent(EventObject event) {
                EventHit e = (EventHit) event;
                if (e.otherGameObject instanceof ChunkManager) { DestroyThisInScene(); }
                if (!GameConstant.GOD_MODE && e.otherGameObject instanceof Player) { DestroyThisInScene(); e.otherGameObject.GetFirstComponentFromClass(PlayerController.class).takeDamage(damage, cooldown);}
            }
        });

        this.getEventDispatcher().AddListener(EventIndex.PROJECTILE_REACHED_DESTINATION, new EventListener() { @Override public void handleEvent(EventObject event) { DestroyThisInScene(); } });
    }

}
