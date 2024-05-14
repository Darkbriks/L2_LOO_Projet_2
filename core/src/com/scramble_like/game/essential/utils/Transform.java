package com.scramble_like.game.essential.utils;

import com.badlogic.gdx.math.Vector3;

public class Transform
{
    private Vector3 Location;
    private Vector3 Rotation;
    private Vector3 Scale;

    public Transform()
    {
        this.Location = new Vector3(0, 0, 0);
        this.Rotation = new Vector3(0, 0, 0);
        this.Scale = new Vector3(1, 1, 1);
    }

    public Vector3 getLocation() { return this.Location; }
    public Vector3 getRotation() { return this.Rotation; }
    public Vector3 getScale() { return this.Scale; }

    public void setLocation(Vector3 Location) { this.Location = Location; }
    public void setRotation(Vector3 Rotation) { this.Rotation = Rotation; }
    public void setScale(Vector3 Scale) { this.Scale = Scale; }

    public void setTransform(Vector3 Location, Vector3 Rotation, Vector3 Scale)
    {
        this.Location = Location;
        this.Rotation = Rotation;
        this.Scale = Scale;
    }

    public void setLocationAndRotation(Vector3 Location, Vector3 Rotation)
    {
        this.Location = Location;
        this.Rotation = Rotation;
    }

    public void Translate(Vector3 Translation) { this.Location.add(Translation); }
    public void Rotate(Vector3 Rotation) { this.Rotation.add(Rotation); }
    public void Scale(Vector3 Scale) { this.Scale.add(Scale); }

    public void Translate(float x, float y, float z) { this.Location.add(x, y, z); }
    public void Rotate(float x, float y, float z) { this.Rotation.add(x, y, z); }
    public void Scale(float x, float y, float z) { this.Scale.add(x, y, z); }
}
