package com.scramble_like.game.game_object.enemy;

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

public class BigSawFish extends MovingEnemy{
    public BigSawFish(String name, Scene scene, Vector2[] waypoints) throws SceneIsNullException {
        super(name, scene, "Characters/Fish/BigSawFish/Walk.png", 0, waypoints, 250);
        //this.AddComponent(new SphereCollider(75,false,true));
        System.out.println("BigSawFish created");
    }


}

