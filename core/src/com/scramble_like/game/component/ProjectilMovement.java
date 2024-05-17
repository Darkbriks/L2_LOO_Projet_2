package com.scramble_like.game.component;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;

public class ProjectilMovement  extends Component {
    private Vector2 direction;

    public ProjectilMovement(GameObject target){
        super();
        Vector3 targetPosition = target.getTransform().getLocation();
        Vector3 thisPosition = this.getOwner().getTransform().getLocation();
        this.direction = new Vector2(targetPosition.x - thisPosition.x,targetPosition.y - thisPosition.y).nor();
    }
    @Override
    public void Update(double time){
        float speed = 10f;
        Vector3 vitesse = new Vector3(direction.x,direction.y,0).scl(speed);
        this.getOwner().getTransform().Translate(vitesse.scl((float)time));
    }
}
