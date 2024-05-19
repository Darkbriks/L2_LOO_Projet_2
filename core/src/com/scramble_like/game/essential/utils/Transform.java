package com.scramble_like.game.essential.utils;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;

public class Transform
{
    private Vector2 Location;
    private Vector2 Rotation;
    private Vector2 Scale;
    private Vector2 Alignment;
    private int ZIndex;

    public Transform()
    {
        this.Location = new Vector2(0, 0);
        this.Rotation = new Vector2(0, 0);
        this.Scale = new Vector2(1, 1);
        this.Alignment = new Vector2(0.5f, 0.5f);
        this.ZIndex = 0;
    }

    public Vector2 getLocation() { return this.Location; }
    public Vector2 getRotation() { return this.Rotation; }
    public Vector2 getScale() { return this.Scale; }
    public Vector2 getAlignment() { return this.Alignment; }
    public int getZIndex() { return this.ZIndex; }

    public void setLocation(Vector2 Location) { this.Location = Location; }
    public void setLocation(float x, float y) { this.Location.set(x, y); }
    public void setRotation(Vector2 Rotation) { this.Rotation = Rotation; }
    public void setRotation(float x, float y) { this.Rotation.set(x, y); }
    public void setScale(Vector2 Scale) { this.Scale = Scale; }
    public void setScale(float x, float y) { this.Scale.set(x, y); }
    public void setAlignment(Vector2 Alignment) { this.Alignment = Alignment; }
    public void setAlignment(float x, float y) { this.Alignment.set(x, y); }
    public void setZIndex(int ZIndex) { this.ZIndex = Utils.clamp(ZIndex, GameConstant.MIN_Z_INDEX, GameConstant.MAX_Z_INDEX); }

    public void setLocationAndRotation(Vector2 Location, Vector2 Rotation)
    {
        this.Location = Location;
        this.Rotation = Rotation;
    }

    public void Translate(Vector2 Translation) { this.Location.add(Translation); }
    public void Rotate(Vector2 Rotation) { this.Rotation.add(Rotation); }
    public void Scale(Vector2 Scale) { this.Scale.add(Scale); }

    public void Translate(float x, float y) { this.Location.add(x, y); }
    public void Rotate(float x, float y) { this.Rotation.add(x, y); }
    public void Scale(float x, float y) { this.Scale.add(x, y); }
}
