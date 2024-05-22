package com.scramble_like.game.game_object.enemy;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.ProjectileController;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.SphereCollider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;

import java.util.EventObject;

public class BigSawFish extends Enemy
{
    public BigSawFish(String name, Scene scene, Vector2[] waypoints) throws SceneIsNullException
    {
        super(name, scene, GameConstant.CHARACTER_PATH("Fish", "BigSawFish"), 0, new int[]{ 4, 4, 2, 6, 6 }, waypoints, 250);
    }
}

