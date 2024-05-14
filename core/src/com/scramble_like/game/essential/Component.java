package com.scramble_like.game.essential;

public abstract class Component
{
    private static int globalID = 0;
    private final int ID;
    protected GameObject Owner;
    private boolean isActive = true;
    private boolean isMarkedForDestruction = false;

    public Component(GameObject Owner) { this.ID = globalID++; this.Owner = Owner; }

    public int getID() { return this.ID; }

    public GameObject getOwner() { return this.Owner; }

    public boolean IsActive() { return this.isActive; }

    public void SetActive(boolean isActive) { this.isActive = isActive; }

    public boolean IsMarkedForDestruction() { return this.isMarkedForDestruction; }

    public void BeginPlay() {}

    public void Update(double DeltaTime) {}

    public void Render() {}

    public void Destroy() { this.isMarkedForDestruction = true; }

    public void Destroying() { this.Owner = null; }
}
