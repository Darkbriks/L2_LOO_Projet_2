package com.scramble_like.game.game_object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.component.Animation;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class Particule extends GameObject {

    private float timer;
    private float tmpAnimation;
    public Particule(String name, Scene scene,String path,int nbdecoupage, float dureeAnimation) throws SceneIsNullException
    {
        super(name, scene);
        Animation animation = new Animation(path, nbdecoupage, dureeAnimation);
        this.AddComponent(animation);
        this.tmpAnimation = dureeAnimation*nbdecoupage;
        this.timer = 0;
        //this.getTransform().setScale(new Vector2(0.3f, 0.3f));
    }

    @Override
    public void Update(double DeltaTime){
        if (!this.IsActive()) { return; }
        super.Update(DeltaTime);
        this.timer += (float) DeltaTime;
        if(this.tmpAnimation<=this.timer){
            DestroyThisInScene();
        }
    }
}
