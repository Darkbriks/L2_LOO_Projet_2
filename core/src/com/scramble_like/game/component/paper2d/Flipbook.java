package com.scramble_like.game.component.paper2d;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.scramble_like.game.essential.CoreConstant;
import com.scramble_like.game.essential.factory.ImageFactory;
import com.scramble_like.game.essential.utils.Utils;

public class Flipbook extends Sprite
{
    private float elapsedTime;
    private int currentFrame;
    private int frameCount;
    TextureRegion frame;

    public Flipbook(String path, int frameCount)
    {
        super(path);
        this.elapsedTime = 0;
        this.currentFrame = 0;
        this.frameCount = frameCount;
    }

    public void setFileName(String fileName, int frameCount)
    {
        super.setFileName(fileName);
        this.frameCount = frameCount;
        this.currentFrame = 0;
        this.elapsedTime = 0;
        ImageFactory.loadTextureRegion(this.fileName, this.frameCount);
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        ImageFactory.loadTextureRegion(this.fileName, this.frameCount);
    }

    @Override
    public void Update(float DeltaTime)
    {
        if (!this.IsActive()) { return; }
        this.elapsedTime += DeltaTime;
        if (this.elapsedTime >= CoreConstant.ANIMATION_FRAME_DURATION)
        {
            this.elapsedTime = 0;
            this.currentFrame = (this.currentFrame + 1) % this.frameCount;
        }
    }

    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }

        frame = ImageFactory.getTextureRegion(this.fileName, this.frameCount, this.currentFrame);

        if (this.getOwner().getTransform().getRotation().x != 0)
        {
            this.getOwner().getBatch().draw(frame,
                    this.getOwner().getTransform().getLocation().x - Utils.lerp(0, frame.getRegionWidth() * this.getOwner().getTransform().getScale().x, this.getOwner().getTransform().getAlignment().x),
                    this.getOwner().getTransform().getLocation().y - Utils.lerp(0, frame.getRegionHeight() * this.getOwner().getTransform().getScale().y, this.getOwner().getTransform().getAlignment().y),
                    frame.getRegionWidth() * this.getOwner().getTransform().getScale().x * this.getOwner().getTransform().getAlignment().x,
                    frame.getRegionHeight() * this.getOwner().getTransform().getScale().y * this.getOwner().getTransform().getAlignment().y,
                    frame.getRegionWidth(), frame.getRegionHeight(),
                    this.getOwner().getTransform().getScale().x, this.getOwner().getTransform().getScale().y,
                    this.getOwner().getTransform().getRotation().x);
            return;
        }

        this.getOwner().getBatch().draw(frame,
                this.getOwner().getTransform().getLocation().x - Utils.lerp(0, frame.getRegionWidth() * this.getOwner().getTransform().getScale().x, this.getOwner().getTransform().getAlignment().x),
                this.getOwner().getTransform().getLocation().y - Utils.lerp(0, frame.getRegionHeight() * this.getOwner().getTransform().getScale().y, this.getOwner().getTransform().getAlignment().y),
                0, 0,
                frame.getRegionWidth(), frame.getRegionHeight(),
                this.getOwner().getTransform().getScale().x, this.getOwner().getTransform().getScale().y,
                this.getOwner().getTransform().getRotation().x);
    }
}
