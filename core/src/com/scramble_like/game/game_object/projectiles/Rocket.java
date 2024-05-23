package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.CharacterController;
import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.CoreConstant;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.chaos.SphereCollider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventHit;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.boss_fight.boss.Boss;

import java.util.EventObject;

public class Rocket extends Projectile
{
    protected Sprite sprite;
    protected String[] explosionAnimationFrames;
    protected float[] explosionRadius;
    protected SphereCollider explosionCollider;
    protected boolean exploded;
    protected float elapsedTime;
    protected int currentFrame;

    public Rocket(Scene scene, Vector2 start, Vector2 direction, float range, float speed) throws SceneIsNullException
    {
        super("Rocket", scene, start, direction, range, speed, false);
        this.damage = 25;
        AABBCollider collider = this.GetFirstComponentFromClass(AABBCollider.class);
        collider.setHeight(25);
        collider.setWidth(25);
        this.sprite = new Sprite(GameConstant.CHARACTER_PATH("Boss/Shots/Shot6", "shot6_1.png"));
        this.sprite.setFlipX(true);
        this.AddComponent(sprite);

        this.explosionAnimationFrames = new String[]{
                "shot6_exp1.png",
                "shot6_exp2.png",
                "shot6_exp3.png",
                "shot6_exp4.png",
                "shot6_exp5.png",
                "shot6_exp6.png",
                "shot6_exp7.png",
                "shot6_exp8.png",
                "shot6_exp9.png",
                "shot6_exp10.png",};

        this.explosionRadius = new float[]{ 25, 30, 35, 40, 45, 50, 50, 50, 50, 50 };

        this.exploded = false;
        this.getTransform().setScale(2, 2);
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
                if (!(e.otherGameObject instanceof Boss) && !(e.otherGameObject instanceof Rocket)) { if (!exploded) { Explode(); } }
            }
        });

        this.getEventDispatcher().AddListener(EventIndex.PROJECTILE_REACHED_DESTINATION, new EventListener() { @Override public void handleEvent(EventObject event) { if (!exploded) { Explode(); } } });
    }

    protected void Explode()
    {
        exploded = true;
        elapsedTime = 0;
        currentFrame = 0;
        explosionCollider = new SphereCollider(explosionRadius[0], false, true);
        this.AddComponent(explosionCollider);
        this.GetFirstComponentFromClass(AABBCollider.class).SetActive(false);
    }

    @Override
    public void Update(float DeltaTime)
    {
        super.Update(DeltaTime);
        if (exploded)
        {
            elapsedTime += DeltaTime;
            if (elapsedTime >= CoreConstant.ANIMATION_FRAME_DURATION)
            {
                if (currentFrame < explosionAnimationFrames.length)
                {
                    sprite.setFileName(GameConstant.CHARACTER_PATH("Boss/Shots/Shot6", explosionAnimationFrames[currentFrame]));
                    explosionCollider.setRadius(explosionRadius[currentFrame]);
                    currentFrame++;
                }
                else { DestroyThisInScene(); }
            }
        }
    }
}
