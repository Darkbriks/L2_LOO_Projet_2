package com.scramble_like.game.game_object.boss_fight.boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.CoreConstant;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Particule;
import com.scramble_like.game.game_object.boss_fight.pattern.Pattern;
import com.scramble_like.game.game_object.boss_fight.pattern.rockets.BossTransitionRocketPattern;
import com.scramble_like.game.map.AbstractLevel;

public abstract class Boss extends GameObject
{
    protected int health;
    protected Pattern[] patterns;
    protected float timeBetweenPatterns;
    protected float timeSinceLastPattern;
    protected float timeToSleep;
    protected boolean sleeping;
    protected int xOffsetWithCamera;
    protected Sprite sprite;
    protected AABBCollider collider;
    protected Class<? extends Boss> bossToSpawnOnDeath;

    // In/Out animation variables
    protected float inOutDuration;
    protected float elapsedTime;
    protected boolean isEntering;
    protected boolean isExiting;
    protected float startingX;
    protected float endingX;
    protected Interpolation interpolation;
    private float cameraSpeed;
    private int backgroundSpeed;


    public Boss(String name, Scene scene, int health, Pattern[] patterns, float timeBetweenPatterns, Sprite sprite, AABBCollider collider, Class<? extends Boss> bossToSpawnOnDeath) throws SceneIsNullException
    {
        super(name, scene);
        this.health = health;
        this.patterns = patterns;
        this.timeBetweenPatterns = timeBetweenPatterns;
        this.timeSinceLastPattern = 0;
        this.timeToSleep = 0;
        this.sleeping = false;
        this.xOffsetWithCamera = 500;
        this.sprite = sprite; this.sprite.setFlipX(true);
        this.collider = collider; this.collider.SetActive(false);
        this.bossToSpawnOnDeath = bossToSpawnOnDeath;
        this.AddComponent(sprite);
        this.AddComponent(collider);

        this.inOutDuration = 5f;

        this.getTransform().setScale(3, 3);
        this.getTransform().setZIndex(CoreConstant.MIN_Z_INDEX);
        this.Enter();
    }

    public void TakeDamage(int damage)
    {
        if (isEntering || isExiting) { return; }

        health -= damage;
        if (health <= 0)
        {
            if (bossToSpawnOnDeath == null)
            {
                try
                {
                    Particule explosion = new Particule("Explosion", getScene(), "Characters/Boss/Explosions/explosion.png", 11);
                    explosion.getTransform().setLocation(this.getTransform().getLocation());
                    getScene().AddGameObject(explosion);
                }
                catch (SceneIsNullException e) { Gdx.app.error("Boss", "Failed to spawn explosion on death"); }
                this.DestroyThisInScene();
            }
            else
            {
                this.Exit();
            }
        }
    }

    @Override
    public void Update(float DeltaTime)
    {
        super.Update(DeltaTime);

        if (isEntering || isExiting) { InOutAnimation(DeltaTime); return; }

        if (sleeping)
        {
            timeSinceLastPattern += DeltaTime;
            if (timeSinceLastPattern >= timeToSleep)
            {
                sleeping = false;
                timeSinceLastPattern = 0;
            }
        }
        else
        {
            int i = (int)(Math.random() * patterns.length);
            patterns[i].Start((AbstractLevel) getScene(), this);
            timeToSleep = timeBetweenPatterns + patterns[i].GetDuration();
            sleeping = true;
        }

        this.getTransform().setLocation(getScene().getCamera().getPosition().x + xOffsetWithCamera, this.getTransform().getLocation().y);
        this.collider.setPositionInGrid();
    }

    protected void Enter()
    {
        isEntering = true;
        startingX = getScene().getCamera().getPosition().x + 1000;
        endingX = getScene().getCamera().getPosition().x + xOffsetWithCamera;
        elapsedTime = 0;
        interpolation = Interpolation.swingOut;
        cameraSpeed = GameConstant.CAMERA_SPEED;
        GameConstant.CAMERA_SPEED = 0;
        backgroundSpeed = GameConstant.BACKGROUND_SPEED;
        GameConstant.BACKGROUND_SPEED = 0;
    }

    protected void Exit()
    {
        isExiting = true;
        startingX = this.getTransform().getLocation().x;
        endingX = getScene().getCamera().getPosition().x + 1000;
        elapsedTime = 0;
        interpolation = Interpolation.swingIn;
        cameraSpeed = GameConstant.CAMERA_SPEED;
        GameConstant.CAMERA_SPEED = 0;
        backgroundSpeed = GameConstant.BACKGROUND_SPEED;
        GameConstant.BACKGROUND_SPEED = 0;
        collider.SetActive(false);
    }

    protected void InOutAnimation(float DeltaTime)
    {
        elapsedTime += DeltaTime;
        float progress = elapsedTime / inOutDuration;
        if (progress > 1) { progress = 1; }
        this.getTransform().getLocation().x = Interpolation.linear.apply(startingX, endingX, interpolation.apply(progress));
        if (progress == 1)
        {
            GameConstant.CAMERA_SPEED = cameraSpeed;
            GameConstant.BACKGROUND_SPEED = backgroundSpeed;
            if (isEntering) { isEntering = false; collider.SetActive(true); }
            if (isExiting)
            {
                if (bossToSpawnOnDeath != null)
                {
                    try
                    {
                        Boss newBoss = bossToSpawnOnDeath.getConstructor(Scene.class).newInstance(getScene());
                        newBoss.getTransform().setLocation(this.getTransform().getLocation());
                        getScene().AddGameObject(newBoss);
                    }
                    catch (Exception e) {  Gdx.app.error("Boss", "Failed to spawn new boss on death"); }
                    BossTransitionRocketPattern rockets = new BossTransitionRocketPattern();
                    rockets.Start((AbstractLevel) getScene(), this);
                }
                isExiting = false;
                this.DestroyThisInScene();
            }
        }
    }
}
