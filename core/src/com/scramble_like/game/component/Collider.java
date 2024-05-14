package com.scramble_like.game.component;

import com.badlogic.gdx.physics.box2d.Shape;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;

public class Collider extends Component {
    private Class<? extends Shape> hitbox;
    public Collider(GameObject Owner) {
        super(Owner);
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
