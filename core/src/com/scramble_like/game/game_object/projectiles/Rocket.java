package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.CharacterController;
import com.scramble_like.game.component.paper2d.Flipbook;
import com.scramble_like.game.essential.CoreConstant;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.chaos.SphereCollider;
import com.scramble_like.game.essential.chunk.ChunkManager;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventHit;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.boss_fight.boss.Boss;

import java.util.EventObject;

public class Rocket extends Projectile
{
    protected SphereCollider explosionCollider;
    protected float explosionRadius = 50;
    protected Flipbook flipbook;
    protected int mainFrameCount = 2;
    protected int explosionFrameCount = 10;
    protected boolean exploded;
    protected float elapsedTime;

    public Rocket(Scene scene, Vector2 start, Vector2 direction, float range, float speed) throws SceneIsNullException
    {
        super("Rocket", scene, start, direction, range, speed, false);
        this.damage = 25;
        AABBCollider collider = this.GetFirstComponentFromClass(AABBCollider.class);
        collider.setHeight(25);
        collider.setWidth(25);

        flipbook = new Flipbook(GameConstant.CHARACTER_PATH("Boss/Shots/Shot6_Animated", "main.png"), mainFrameCount);
        this.AddComponent(flipbook);

        this.exploded = false;
        this.getTransform().setScale(1, 1);

        float angle = (float) Math.toDegrees(Math.atan2(direction.y, direction.x));
        this.getTransform().setRotation(angle, 0);
    }

    @Override
    public void BeginPlay()
    {
       this.setActive(true);

        this.getEventDispatcher().AddListener(EventIndex.HIT, new EventListener() {
            @Override
            public void handleEvent(EventObject event) {
                EventHit e = (EventHit) event;
                if (!GameConstant.GOD_MODE && e.otherGameObject instanceof Player && !generatedByPlayer)
                {
                    e.otherGameObject.GetFirstComponentFromClass(CharacterController.class).takeDamage(damage, cooldown);
                }
                if (!(e.otherGameObject instanceof Boss) && !(e.otherGameObject instanceof Rocket) && !(e.otherGameObject instanceof ChunkManager)) { if (!exploded) { Explode(); } }
            }
        });

        this.getEventDispatcher().AddListener(EventIndex.PROJECTILE_REACHED_DESTINATION, new EventListener() { @Override public void handleEvent(EventObject event) { if (!exploded) { Explode(); } } });
    }

    protected void Explode()
    {
        exploded = true;
        elapsedTime = 0;
        explosionCollider = new SphereCollider(explosionRadius, false, true);
        this.AddComponent(explosionCollider);
        this.flipbook.setFileName(GameConstant.CHARACTER_PATH("Boss/Shots/Shot6_Animated", "exploding.png"), explosionFrameCount);
        this.GetFirstComponentFromClass(AABBCollider.class).SetActive(false);
    }

    @Override
    public void Update(float DeltaTime)
    {
        super.Update(DeltaTime);
        if (exploded)
        {
            elapsedTime += DeltaTime;
            if (elapsedTime >= CoreConstant.ANIMATION_FRAME_DURATION * explosionFrameCount)
            {
                DestroyThisInScene();
            }
        }
    }
}
