package com.scramble_like.game.game_object;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.paper2d.Flipbook;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class Particule extends GameObject
{
    private float elapsedTime;
    private final float animationDuration;

    public Particule(String name, Scene scene, String path, int frameCount) throws SceneIsNullException
    {
        super(name, scene);
        this.AddComponent(new Flipbook(path, frameCount));
        this.animationDuration = GameConstant.ANIMATION_FRAME_DURATION * frameCount;
        this.elapsedTime = 0;
    }

    public Particule(String name, Scene scene, String path, int frameCount, Vector2 location) throws SceneIsNullException
    {
        super(name, scene);
        this.getTransform().setLocation(location);
        this.AddComponent(new Flipbook(path, frameCount));
        this.animationDuration = GameConstant.ANIMATION_FRAME_DURATION * frameCount;
        this.elapsedTime = 0;
    }

    @Override
    public void Update(float DeltaTime)
    {
        if (!this.IsActive()) { return; }
        super.Update(DeltaTime);
        this.elapsedTime += DeltaTime;
        if (this.elapsedTime >= this.animationDuration) { DestroyThisInScene(); }
    }
}
