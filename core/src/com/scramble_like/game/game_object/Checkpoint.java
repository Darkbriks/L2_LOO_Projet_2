package com.scramble_like.game.game_object;

import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.SphereCollider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.map.AbstractLevel;

import java.util.EventObject;

public class Checkpoint extends GameObject
{

    public Checkpoint(String name, Scene scene, float x, float y) throws SceneIsNullException
    {
        super(name, scene);
        this.getTransform().setLocation(x, y);
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        this.AddComponent(new SphereCollider(250, true, true));

        this.getEventDispatcher().AddListener(EventIndex.BEGIN_OVERLAP, new EventListener() {
            @Override
            public void handleEvent(EventObject event)
            {
                EventBeginOverlap e = (EventBeginOverlap) event;
                if (e.otherGameObject instanceof Player)
                {
                    if (getScene() instanceof AbstractLevel)
                    {
                        ((AbstractLevel) getScene()).setLastCheckpoint(Checkpoint.this);
                    }
                }
            }
        });
    }
}
