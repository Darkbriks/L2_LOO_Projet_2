package com.scramble_like.game.component;

import com.scramble_like.game.essential.Component;

public class TestComponent extends Component
{
    public int ScaleDirection = 1;

    public TestComponent() { super(); }

    @Override
    public void Update(double DeltaTime)
    {
        this.getOwner().getTransform().getRotation().x += 10 * DeltaTime;
        this.getOwner().getTransform().getScale().x += 0.5f * DeltaTime * ScaleDirection;
        this.getOwner().getTransform().getScale().y += 0.5f * DeltaTime * ScaleDirection;
        if (this.getOwner().getTransform().getScale().x > 1.5) { ScaleDirection = -1; }
        if (this.getOwner().getTransform().getScale().x < 0.5) { ScaleDirection = 1; }
    }
}
