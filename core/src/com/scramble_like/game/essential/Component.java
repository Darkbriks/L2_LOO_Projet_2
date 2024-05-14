package com.scramble_like.game.essential;

public abstract class Component<T>
{
    private static int globalID = 0;
    private final int ID;
    public GameObject Owner;

    public Component() { this.ID = globalID++; }

    public int getID() { return this.ID; }

    public abstract void BeginPlay();

    public abstract void Update(double DeltaTime);

    public abstract void Render();

    public abstract void Destroy();
}
