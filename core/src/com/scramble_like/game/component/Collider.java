package com.scramble_like.game.component;

import com.badlogic.gdx.math.Rectangle;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;

public class Collider extends Component {
    private Rectangle hitbox;
    public Collider() {
        super();
        hitbox = new Rectangle();
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
    //Renvoi Owner si une collision a eu lieu entre 2 Owners (celui de l'appel et celui en paramètre)
    private GameObject hit(Collider collision){
        if(getOwner().getTransform().getLocation().x==collision.getOwner().getTransform().getLocation().x){
            if(getOwner().getTransform().getLocation().y==collision.getOwner().getTransform().getLocation().y){
                if(getOwner().getTransform().getLocation().z==collision.getOwner().getTransform().getLocation().z){
                    return getOwner();
                }
                else{return null;}
            }
            else{return null;}
        }
        else{return null;}
    }

    @Override
    public void BeginPlay() {

    }

    @Override
    public void Update(double DeltaTime) {

    }

    @Override
    public void Render() {

    }



}
