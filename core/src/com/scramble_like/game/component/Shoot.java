package com.scramble_like.game.component;

import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.exception.OwnerIsNullException;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Projectile;

public class Shoot extends Component
{
    private GameObject target;
    private float speed;

    public Shoot(GameObject owner, float speed, GameObject target) throws OwnerIsNullException {
        super();
        this.Init(owner);
        this.speed = speed;
        this.target = target;
    }

    public void shoot() throws SceneIsNullException {

        Projectile projectile = new Projectile("projectile", this.Owner.getScene(), this.target);
        this.Owner.getScene().AddGameObject(projectile);


        projectile.getTransform().setLocation(this.Owner.getTransform().getLocation());
    }

    @Override
    public void Update(double DeltaTime) {

        try {
            shoot();
        } catch (SceneIsNullException e) {
            e.printStackTrace();
        }
    }
}