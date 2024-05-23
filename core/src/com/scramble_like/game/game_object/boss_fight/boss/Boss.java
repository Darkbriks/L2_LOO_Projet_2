package com.scramble_like.game.game_object.boss_fight.boss;

import com.badlogic.gdx.Gdx;
import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.boss_fight.pattern.Pattern;
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
        this.collider = collider;
        this.bossToSpawnOnDeath = bossToSpawnOnDeath;
        this.AddComponent(sprite);
        this.AddComponent(collider);

        this.getTransform().setScale(3, 3);
    }

    public void TakeDamage(int damage)
    {
        health -= damage;
        if (health <= 0)
        {
            if (bossToSpawnOnDeath != null)
            {
                try
                {
                    Boss newBoss = bossToSpawnOnDeath.getConstructor(Scene.class).newInstance(getScene());
                    getScene().AddGameObject(newBoss);
                }
                catch (Exception e) {  Gdx.app.error("Boss", "Failed to spawn new boss on death"); }
            }
            DestroyThisInScene();
        }
    }

    @Override
    public void Update(float DeltaTime)
    {
        super.Update(DeltaTime);
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
}
