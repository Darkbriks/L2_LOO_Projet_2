package com.scramble_like.game.component.paper2d;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.factory.ImageFactory;
import com.scramble_like.game.essential.utils.Utils;

public class Sprite extends Component
{
    protected String fileName;
    protected Vector2 imageSize;

    protected boolean flipX = false;
    protected boolean flipY = false;

    public Sprite() { super(); fileName = "badlogic.jpg"; }
    public Sprite(String path) { super(); fileName = path; }
    public Sprite(String path, boolean flipX, boolean flipY) { super(); this.flipX = flipX; this.flipY = flipY; fileName = path; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName)
    {
        ImageFactory.disposeTexture(this.fileName);
        this.fileName = fileName;
        ImageFactory.loadTexture(fileName);
        imageSize = ImageFactory.getTextureSize(fileName);
    }

    public boolean isFlipX() { return flipX; }
    public void setFlipX(boolean flipX) { this.flipX = flipX; }

    public boolean isFlipY() { return flipY; }
    public void setFlipY(boolean flipY) { this.flipY = flipY; }

    @Override
    public void BeginPlay()
    {
        ImageFactory.loadTexture(fileName);
        imageSize = ImageFactory.getTextureSize(fileName);
    }

    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }
        this.getOwner().getBatch().draw(ImageFactory.getTexture(fileName),
                this.getOwner().getTransform().getLocation().x - Utils.lerp(0, (int) imageSize.x * this.getOwner().getTransform().getScale().x, this.getOwner().getTransform().getAlignment().x),
                this.getOwner().getTransform().getLocation().y - Utils.lerp(0, (int) imageSize.y * this.getOwner().getTransform().getScale().y, this.getOwner().getTransform().getAlignment().y),
                0, 0,
                imageSize.x, imageSize.y,
                this.getOwner().getTransform().getScale().x, this.getOwner().getTransform().getScale().y,
                this.getOwner().getTransform().getRotation().x,
                0, 0,
                (int) imageSize.x, (int) imageSize.y,
                flipX, flipY);
    }
    @Override
    public void Destroy() { ImageFactory.disposeTexture(fileName); }
}
