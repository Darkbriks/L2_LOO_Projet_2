package com.scramble_like.game.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;



public class Sprite extends Component{

    Texture img;
    SpriteBatch batch;

    public Sprite(){
        super();
        img = new Texture("badlogic.jpg");
        batch = new SpriteBatch();
    }
    
    @Override
    public void Render() {
        
        this.Owner.getScene().batch.draw(img, this.Owner.getTransform().getLocation().x, this.Owner.getTransform().getLocation().y);
        
    }
    @Override
    public void Destroy() {
        batch.dispose();
        img.dispose();
    }
}
