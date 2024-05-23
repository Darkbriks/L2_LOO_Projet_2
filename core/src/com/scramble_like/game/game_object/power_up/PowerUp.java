package com.scramble_like.game.game_object.power_up;

import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.SphereCollider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;

import java.util.EventObject;

public abstract class PowerUp extends GameObject
{
    public PowerUp(String name, Scene scene, float x, float y, float radius) throws SceneIsNullException
    {
        super(name, scene);
        this.AddComponent(new SphereCollider(radius, true, true));
        this.getTransform().setLocation(x, y);
    }

    protected abstract void Triggered(Player player);

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
                    Triggered((Player) e.otherGameObject);
                }
            }
        });
    }
}
