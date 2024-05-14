package com.scramble_like.game.essential.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Transform
{
    private Vector3 Location;
    private Vector2 Rotation;
    private Vector2 Scale;
    private Vector2 Alignment;

    public Transform()
    {
        this.Location = new Vector3(0, 0, 0);
        this.Rotation = new Vector2(0, 0);
        this.Scale = new Vector2(1, 1);
        this.Alignment = new Vector2(0.5f, 0.5f);
    }

    public Vector3 getLocation() { return this.Location; }
    public Vector2 getRotation() { return this.Rotation; }
    public Vector2 getScale() { return this.Scale; }
    public Vector2 getAlignment() { return this.Alignment; }

    public void setLocation(Vector3 Location) { this.Location = Location; }
    public void setRotation(Vector2 Rotation) { this.Rotation = Rotation; }
    public void setScale(Vector2 Scale) { this.Scale = Scale; }
    public void setAlignment(Vector2 Alignment) { this.Alignment = Alignment; }

    public void setLocationAndRotation(Vector3 Location, Vector2 Rotation)
    {
        this.Location = Location;
        this.Rotation = Rotation;
    }

    public void Translate(Vector3 Translation) { this.Location.add(Translation); }
    public void Rotate(Vector2 Rotation) { this.Rotation.add(Rotation); }
    public void Scale(Vector2 Scale) { this.Scale.add(Scale); }


    public void Translate(float x, float y, float z) { this.Location.add(x, y, z); }
    public void Rotate(float x, float y) { this.Rotation.add(x, y); }
    public void Scale(float x, float y) { this.Scale.add(x, y); }
}
