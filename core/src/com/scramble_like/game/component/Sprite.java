package com.scramble_like.game.component;

import com.badlogic.gdx.graphics.Texture;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.utils.Utils;

public class Sprite extends Component{

    private Texture img;

    private boolean flipX = false;
    private boolean flipY = false;

    public Sprite() { super(); img = new Texture("badlogic.jpg"); }
    public Sprite(String path) { super(); img = new Texture(path); }
    public Sprite(String path, boolean flipX, boolean flipY) { super(); img = new Texture(path); this.flipX = flipX; this.flipY = flipY; }

    public Texture getImg() { return img; }
    public void setImg(Texture img) { this.img = img; }

    public boolean isFlipX() { return flipX; }
    public void setFlipX(boolean flipX) { this.flipX = flipX; }

    public boolean isFlipY() { return flipY; }
    public void setFlipY(boolean flipY) { this.flipY = flipY; }

    @Override
    public void Render() {
        
        //batch.draw(img, this.Owner.getTransform().getLocation().x, this.Owner.getTransform().getLocation().y);
        this.Owner.getScene().batch.draw(img,
                this.Owner.getTransform().getLocation().x - this.img.getWidth() / 2.0f, this.Owner.getTransform().getLocation().y - this.img.getHeight() / 2.0f,
                Utils.lerp(this.Owner.getTransform().getLocation().x, this.img.getWidth(), this.Owner.getTransform().getAlignment().x), Utils.lerp(this.Owner.getTransform().getLocation().y, this.img.getHeight(), this.Owner.getTransform().getAlignment().y),
                img.getWidth(), img.getHeight(),
                this.Owner.getTransform().getScale().x, this.Owner.getTransform().getScale().y,
                this.Owner.getTransform().getRotation().x,
                0, 0,
                img.getWidth(), img.getHeight(),
                false, false);
    }
    @Override
    public void Destroy() { img.dispose(); }
}