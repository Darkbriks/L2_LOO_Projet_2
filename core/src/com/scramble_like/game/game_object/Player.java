package com.scramble_like.game.game_object;

import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.AnimationController;
import com.scramble_like.game.component.controller.FireController;
import com.scramble_like.game.component.paper2d.Flipbook;
import com.scramble_like.game.component.controller.PlayerController;
import com.scramble_like.game.essential.CoreConstant;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chunk.ChunkManager;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventHit;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.boss_fight.boss.Boss;
import com.scramble_like.game.game_object.enemy.Enemy;
import com.scramble_like.game.game_object.projectiles.PlayerBomb;
import com.scramble_like.game.game_object.projectiles.SimpleBullet;

import java.util.EventObject;

public class Player extends GameObject
{
    protected PlayerController playerController;
    protected FireController mainFireController;
    protected FireController secondaryFireController;
    protected AnimationController animationController;
    protected Flipbook flipbook;
    protected AABBCollider collider;

    public Player(String name, Scene scene, Vector2 location) throws SceneIsNullException
    {
        super(name, scene);
        this.getTransform().setLocation(location);
        this.getTransform().setZIndex(CoreConstant.MIN_Z_INDEX + 1);
    }

    public PlayerController getPlayerController() { return playerController; }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();

        this.collider = new AABBCollider(GameConstant.PLAYER_IDLE_COLLIDER.x, GameConstant.PLAYER_IDLE_COLLIDER.y, GameConstant.PLAYER_IDLE_COLLIDER.z, GameConstant.PLAYER_IDLE_COLLIDER.w, false, true);
        this.AddComponent(collider);

        this.flipbook = new Flipbook(GameConstant.CHARACTER_PATH("UnderwaterCharacterPack", "MermaidGuard_2") + "/Idle.png", 4);
        this.AddComponent(flipbook);

        this.animationController = new AnimationController(GameConstant.CHARACTER_PATH("UnderwaterCharacterPack", "MermaidGuard_2"), flipbook, new int[]{ 4, 6, 2, 6, 6 });
        this.AddComponent(animationController);

        this.playerController = new PlayerController(animationController, collider);
        this.AddComponent(playerController);

        this.mainFireController = new FireController(0.5f, true, SimpleBullet.class);
        this.AddComponent(mainFireController);

        this.secondaryFireController = new FireController(3f, true, true, PlayerBomb.class);
        this.AddComponent(secondaryFireController);

        this.getScene().setPlayer(this);

        this.getEventDispatcher().AddListener(EventIndex.HIT, new EventListener() {
            @Override
            public void handleEvent(EventObject event)
            {
                if (GameConstant.GOD_MODE) { return; }
                EventHit e = (EventHit) event;

                if (e.otherGameObject instanceof ChunkManager || e.otherGameObject instanceof Boss)
                {
                    if (Controllers.getCurrent() != null) { Controllers.getCurrent().startVibration(100, 1); }
                    playerController.takeDamage(10000, 0.0f); animationController.setState(AnimationController.AnimationState.DIE, 1);
                }
                if (e.otherGameObject instanceof Enemy)
                {
                    if (Controllers.getCurrent() != null) { Controllers.getCurrent().startVibration(100, 1); }
                    Enemy enemy = (Enemy) e.otherGameObject;
                    playerController.takeDamage(enemy.getCollisionDamage(), 0.5f);
                }
            }
        });
    }
}
