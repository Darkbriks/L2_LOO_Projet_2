package com.scramble_like.game.game_object;

import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.paper2d.Flipbook;
import com.scramble_like.game.component.PlayerController;
import com.scramble_like.game.component.collider.AABBCollider;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventHit;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.enemy.Enemy;

import java.util.EventObject;

public class Player extends GameObject
{
    public Player(String name, Scene scene) throws SceneIsNullException
    {
        super(name, scene);
    }

    public Player(String name, Scene scene, Vector2 location) throws SceneIsNullException
    {
        super(name, scene);
        this.getTransform().setLocation(location);
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        PlayerController playerController = new PlayerController();
        this.AddComponent(playerController);
        this.AddComponent(new Flipbook(GameConstant.CHARACTER_PATH("UnderwaterCharacterPack", "MermaidGuard_1") + "/Idle.png", 4));
        this.AddComponent(new AABBCollider(25, 50, -10, 0, false, true));

        this.getEventDispatcher().AddListener(EventIndex.HIT, new EventListener() {
            @Override
            public void handleEvent(EventObject event)
            {
                if (Controllers.getCurrent() != null) { Controllers.getCurrent().startVibration(100, 1); }
                if (GameConstant.GOD_MODE) { return; }
                EventHit e = (EventHit) event;
                if (e.otherGameObject instanceof ChunkManager) { playerController.takeDamage(10000, 0.0f); }
                if (e.otherGameObject instanceof Enemy) { playerController.takeDamage(10, 0.5f); }
            }
        });
    }
}
