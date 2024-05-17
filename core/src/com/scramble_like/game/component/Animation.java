package com.scramble_like.game.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.scramble_like.game.essential.utils.Utils;

public class Animation extends Sprite {

    protected  Texture animation = this.img;
    protected TextureRegion[] frames;
    protected int currentFrameIndex = 0;
    protected float time;
    protected final int nbdecoupage;
    protected final float dureeAnimation;
    public Animation(String path, int nbdecoupage,float dureeAnimation){
        super(path);
        this.nbdecoupage = nbdecoupage;
        this.dureeAnimation = dureeAnimation;
        decoupage();
    }

    private void decoupage(){
        TextureRegion[][] tmp = TextureRegion.split(animation,  animation.getWidth() / this.nbdecoupage, animation.getHeight());
        this.frames = new TextureRegion[5];
        for (int i = 0; i < 1; i++) { // Une seule ligne
            for (int j = 0; j < this.nbdecoupage; j++) { // nombre d'images
                this.frames[j] = tmp[i][j];
            }
        }
    }

    @Override
    public void Update(double DeltaTime){
        if (!this.IsActive()) { return; }
        time += (float) DeltaTime;
        if(time>dureeAnimation){
            currentFrameIndex = (currentFrameIndex + 1) % this.nbdecoupage;
            time = 0;
        }
    }

    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }
        this.getOwner().getBatch().draw(frames[currentFrameIndex],
                this.getOwner().getTransform().getLocation().x - Utils.lerp(0, img.getWidth() * this.getOwner().getTransform().getScale().x, this.getOwner().getTransform().getAlignment().x),
                this.getOwner().getTransform().getLocation().y - Utils.lerp(0, img.getHeight() * this.getOwner().getTransform().getScale().y, this.getOwner().getTransform().getAlignment().y),
                0,0,
                img.getWidth(), img.getHeight(),
                this.getOwner().getTransform().getScale().x, this.getOwner().getTransform().getScale().y,
                this.getOwner().getTransform().getRotation().x);
    }
}
