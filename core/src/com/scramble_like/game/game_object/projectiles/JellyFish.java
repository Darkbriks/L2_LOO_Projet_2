package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.component.controller.ProjectileController;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.chaos.SphereCollider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.enemy.MovingEnemy;

import java.util.EventObject;

public class JellyFish extends Projectile
{
    public JellyFish(Scene scene, Vector2 location) throws SceneIsNullException
    {
        super("JellyFish", scene, "Characters/Fish/JellyFish/Walk.png", location, new Vector2(0, 1), 1000, 300, 4);
        this.getTransform().setScale(new Vector2(1, 1));
        this.AddComponent(new AABBCollider(150,1200,true,true));
        this.damage = 10;
    }

    private void LaunchMeduse(){
        this.GetFirstComponentFromClass(ProjectileController.class).SetActive(true);
    }

    @Override
    public void BeginPlay(){

        super.BeginPlay();
        this.getEventDispatcher().AddListener(EventIndex.BEGIN_OVERLAP, new EventListener() {
            @Override
            public void handleEvent(EventObject event)
            {
                EventBeginOverlap e = (EventBeginOverlap) event;
                if (e.otherGameObject instanceof Player)
                {
                    LaunchMeduse();
                }
            }
        });
        this.GetFirstComponentFromClass(ProjectileController.class).SetActive(false);
    }
}
