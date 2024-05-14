package com.scramble_like.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;



public class Sprite extends Component{
    final ScrambleLikeApplication scramble;

    Texture img;
    SpriteBatch batch;

    public Sprite(ScrambleLikeApplication scramble, GameObject Owner){
        super(Owner);
        this.scramble = scramble;
        img = new Texture("badlogic.jpg");
        batch = new SpriteBatch();
    }
    
    @Override
    public void Render() {
        
        this.Owner.getScene().batch.draw(img, 0, 0);
        
    }
    @Override
    public void Destroy() {
        batch.dispose();
        img.dispose();
    }
}
