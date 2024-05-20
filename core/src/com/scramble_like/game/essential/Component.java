package com.scramble_like.game.essential;

import com.badlogic.gdx.utils.Disposable;
import com.scramble_like.game.essential.exception.OwnerIsNullException;

public abstract class Component implements Disposable
{
    private static int globalID = 0;
    private final int ID;
    protected GameObject Owner;
    private boolean isActive = true;
    private boolean isMarkedForDestruction = false;

    public Component() { this.ID = globalID++; this.isActive = false; }

    public void Init(GameObject Owner) throws OwnerIsNullException { this.Owner = Owner; if (this.Owner == null) { this.Destroy(); this.Destroying(); throw new OwnerIsNullException(this); } }

    public int getID() { return this.ID; }

    public GameObject getOwner() { return this.Owner; }

    public boolean IsActive() { return this.isActive; }

    public void SetActive(boolean isActive) { this.isActive = isActive; }

    public boolean IsMarkedForDestruction() { return this.isMarkedForDestruction; }

    public void BeginPlay() {this.isActive = true; }

    public void Update(float DeltaTime) {}

    public void Render() {}

    public void Destroy() { this.isMarkedForDestruction = true; }

    public void Destroying() { this.Owner = null; this.isActive = false; }

    @Override
    public void dispose()
    {
        this.Destroy();
        this.Destroying();
    }
}
