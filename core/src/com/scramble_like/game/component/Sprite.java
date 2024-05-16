package com.scramble_like.game.component;

import com.badlogic.gdx.graphics.Texture;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.factory.ImageFactory;
import com.scramble_like.game.essential.utils.Utils;

public class Sprite extends Component{

    private Texture img;
    private final String fileName;

    private boolean flipX = false;
    private boolean flipY = false;

    public Sprite() { super(); img = ImageFactory.getTexture("badlogic.jpg"); fileName = "badlogic.jpg"; }
    public Sprite(String path) { super(); img = ImageFactory.getTexture(path); fileName = path; }
    public Sprite(String path, boolean flipX, boolean flipY) { super(); img = ImageFactory.getTexture(path); this.flipX = flipX; this.flipY = flipY; fileName = path; }

    public Texture getImg() { return img; }
    public void setImg(Texture img) { this.img = img; }

    public boolean isFlipX() { return flipX; }
    public void setFlipX(boolean flipX) { this.flipX = flipX; }

    public boolean isFlipY() { return flipY; }
    public void setFlipY(boolean flipY) { this.flipY = flipY; }

    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }
        this.getOwner().getBatch().draw(img,
                this.getOwner().getTransform().getLocation().x - Utils.lerp(0, img.getWidth() * this.getOwner().getTransform().getScale().x, this.getOwner().getTransform().getAlignment().x),
                this.getOwner().getTransform().getLocation().y - Utils.lerp(0, img.getHeight() * this.getOwner().getTransform().getScale().y, this.getOwner().getTransform().getAlignment().y),
                0, 0,
                img.getWidth(), img.getHeight(),
                this.getOwner().getTransform().getScale().x, this.getOwner().getTransform().getScale().y,
                this.getOwner().getTransform().getRotation().x,
                0, 0,
                img.getWidth(), img.getHeight(),
                flipX, flipY);
    }
    @Override
    public void Destroy() { ImageFactory.disposeTexture(fileName); }
}
