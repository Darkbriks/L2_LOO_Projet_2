package com.scramble_like.game.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.scramble_like.game.essential.factory.ImageFactory;
import com.scramble_like.game.essential.utils.Utils;

public class LifeActor extends Actor
{
    private final Stage stage;
    private final TextureRegion[] regions;
    private  Image image;

    public LifeActor(Stage stage, String path, int frameCount)
    {
        super();
        this.stage = stage;
        ImageFactory.loadTextureRegion(path, frameCount);
        this.regions = ImageFactory.getTextureRegion(path, frameCount);
        this.image = new Image(this.regions[0]);
        this.stage.addActor(this.image);
    }

    public void setCurrentRegion(int index)
    {
        this.image.remove();
        this.image = new Image(this.regions[Utils.clamp(index, 0, regions.length - 1)]);
        this.stage.addActor(this.image);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        this.image.setPosition(this.getX(), this.getY());
    }
}