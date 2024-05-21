package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.component.controller.ProjectileController;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.enemy.MovingEnemy;

import java.util.EventObject;

public class DeepDeaMonster extends Projectile {
    public DeepDeaMonster(Scene scene, Vector2 location) throws SceneIsNullException
    {
        super("DeepDeaMonster", scene, "Characters/Fish/DeepDeaMonster/Walk.png", location, new Vector2(1, 0), 1000, 300, 4);
        this.getTransform().setScale(new Vector2(1, 1));
        this.AddComponent(new AABBCollider(1000,20,true,true));
        this.damage = 10;
    }

    private void LaunchDeepDeaMonster(Vector2 vector){
        this.getTransform().setScale(vector);
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
                    if(e.otherGameObject.getTransform().getLocation().x<e.sourceGameObject.getTransform().getLocation().x)
                    {
                        LaunchDeepDeaMonster(new Vector2(-1,0));
                    }
                    else{
                        LaunchDeepDeaMonster(new Vector2(1,0));
                        }
                }
            }
        });
        this.GetFirstComponentFromClass(ProjectileController.class).SetActive(false);
    }
}
