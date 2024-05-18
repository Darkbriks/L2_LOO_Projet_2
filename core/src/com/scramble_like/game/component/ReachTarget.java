package com.scramble_like.game.component;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.exception.OwnerIsNullException;
import com.scramble_like.game.essential.utils.Utils;

public class ReachTarget extends Component
{
    private final GameObject target;
    private final float speed;
    private final float damage;
    private Vector2 initialLocation;
    private float t = 0;

    public ReachTarget(GameObject target) { super(); this.target = target; this.speed = 1; this.damage = 1; }
    public ReachTarget(GameObject target, float speed) { super(); this.target = target; this.speed = speed; this.damage = 1; }
    public ReachTarget(GameObject target, float speed, float damage) { super(); this.target = target; this.speed = speed; this.damage = damage; }

    @Override
    public void Init(GameObject Owner) throws OwnerIsNullException
    {
        super.Init(Owner);
        initialLocation = new Vector2(Owner.getTransform().getLocation());
    }

    public GameObject getTarget() { return target; }
    public float getSpeed() { return speed; }
    public float getDamage() { return damage; }

    @Override
    public void Update(double DeltaTime)
    {
        if (!this.IsActive()) { return; }

        t += (float)DeltaTime  * speed;
        this.getOwner().getTransform().setLocation(Utils.lerp(initialLocation, target.getTransform().getLocation(), t));
    }
}
