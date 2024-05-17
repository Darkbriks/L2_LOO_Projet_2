package com.scramble_like.game.game_object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.component.PlayerController;
import com.scramble_like.game.component.ReachTarget;
import com.scramble_like.game.component.Sprite;
import com.scramble_like.game.component.collider.AABBCollider;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventHit;
import com.scramble_like.game.essential.exception.SceneIsNullException;

import java.util.EventObject;

public class Player extends GameObject
{
    public Player(String name, Scene scene) throws SceneIsNullException
    {
        super(name, scene);
        this.getTransform().setScale(new Vector2(0.3f, 0.3f));
    }

    public Player(String name, Scene scene, Vector3 location) throws SceneIsNullException
    {
        super(name, scene);
        this.getTransform().setLocation(location);
        this.getTransform().setScale(new Vector2(0.3f, 0.3f));

    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        PlayerController playerController = new PlayerController();
        this.AddComponent(playerController);
        this.AddComponent(new Sprite());
        this.AddComponent(new AABBCollider(100, 100, false, true));

        this.getEventDispatcher().AddListener(EventIndex.HIT, new EventListener() {
            @Override
            public void handleEvent(EventObject event) {
                EventHit e = (EventHit) event;
                if (e.otherGameObject instanceof ChunkManager) { playerController.takeDamage(10000); }
            }
        });
    }
}
