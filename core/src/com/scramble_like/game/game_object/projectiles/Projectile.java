package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.component.ReachTarget;
import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.component.collider.AABBCollider;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventHit;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;

import java.util.EventObject;

public class Projectile extends GameObject
{
    GameObject target;

    public Projectile(String name, Scene scene, GameObject target) throws SceneIsNullException
    {
        super(name, scene);
        this.getTransform().setScale(new Vector2(0.2f, 0.2f));
    }

    public Projectile(String name, Scene scene, GameObject target, Vector2 location) throws SceneIsNullException
    {
        super(name, scene);
        this.getTransform().setLocation(location);
        this.target = target;
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        this.AddComponent(new AABBCollider(10, 10, false, true));
        this.getTransform().setScale(new Vector2(0.2f, 0.2f));
        this.AddComponent(new ReachTarget(target));
        this.AddComponent(new Sprite());

        this.getEventDispatcher().AddListener(EventIndex.HIT, new EventListener() {
            @Override
            public void handleEvent(EventObject event) {
                EventHit e = (EventHit) event;
                //if (e.otherGameObject instanceof ChunkManager) { DestroyThisInScene(); }
                if (e.otherGameObject instanceof Player) { DestroyThisInScene(); }
            }
        });
    }
}
