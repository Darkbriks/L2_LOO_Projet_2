package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.component.controller.ProjectileController;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.SphereCollider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;

import java.util.EventObject;

public class PlayerDeclanchedProjectile extends Projectile
{
    public PlayerDeclanchedProjectile(String name, Scene scene, Vector2 start, Vector2 direction, float range, float speed, int damage, float cooldown, float radius) throws SceneIsNullException
    {
        super(name, scene, start, direction, range, speed);
        this.damage = damage;
        this.cooldown = cooldown;
        this.AddComponent(new SphereCollider(radius, true, true));
    }

    protected void LaunchProjectile(EventBeginOverlap e) { this.GetFirstComponentFromClass(ProjectileController.class).SetActive(true); }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        this.getEventDispatcher().AddListener(EventIndex.BEGIN_OVERLAP, new EventListener() {
            @Override
            public void handleEvent(EventObject event)
            {
                EventBeginOverlap e = (EventBeginOverlap) event;
                if (e.otherGameObject instanceof Player)
                {
                    LaunchProjectile(e);
                }
            }
        });
        this.GetFirstComponentFromClass(ProjectileController.class).SetActive(false);
    }
}
