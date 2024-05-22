package com.scramble_like.game.game_object.enemy.triggered_by_player;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.enemy.Enemy;

import java.util.EventObject;

public abstract class PlayerDeclanchedEnemy extends Enemy
{
    public PlayerDeclanchedEnemy(String name, Scene scene, String spriteFolderPath, int life, float shootSpeed, int[] animationFrames, Vector2[] waypoints, float movementSpeed, float timeBetweenWaypoints, Vector2 overlapSize) throws SceneIsNullException
    {
        super(name, scene, spriteFolderPath, life, shootSpeed, animationFrames, waypoints, movementSpeed, timeBetweenWaypoints);
        this.AddComponent(new AABBCollider(overlapSize.x, overlapSize.y, true, true));
    }

    protected abstract void Triggered(EventBeginOverlap e);

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
                    Triggered(e);
                }
            }
        });
    }
}
