package com.scramble_like.game.game_object.enemy;

import com.badlogic.gdx.Gdx;
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

public class ElectricEel extends MovingEnemy {
    public ElectricEel(String name, Scene scene, Vector2[] waypoints) throws SceneIsNullException {
        super(name, scene, "Characters/Fish/ElectricEel/Walk.png", 0, waypoints, 150);
        this.AddComponent(new SphereCollider(75,true,false));
        System.out.println("ElectricEel created");
    }

    private void aoe() {
        try {
            this.GetFirstComponentFromClass(SphereCollider.class).setSimulatePhysics(true);
            wait(50);
        } catch (Exception e) { Gdx.app.error("ElectricEel", "Error: " + e.getMessage()); }
        this.GetFirstComponentFromClass(SphereCollider.class).setSimulatePhysics(false);
        try {
            wait(5000);
        } catch (Exception e) { Gdx.app.error("ElectricEel", "Error: " + e.getMessage()); }
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
                    aoe();
                }
            }
        });
        this.GetFirstComponentFromClass(ProjectileController.class).SetActive(false);
    }
}
