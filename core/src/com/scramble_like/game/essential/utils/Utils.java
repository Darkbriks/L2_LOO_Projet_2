package com.scramble_like.game.essential.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Utils
{
    public static int lerp(int a, int b, float t) { return (int)lerp((float)a, (float)b, t); }
    public static float lerp(float a, float b, float t) { return a + (b - a) * t; }
    public static Vector2 lerp(Vector2 a, Vector2 b, float t) { return new Vector2(lerp(a.x, b.x, t), lerp(a.y, b.y, t)); }
    public static Vector3 lerp(Vector3 a, Vector3 b, float t) { return new Vector3(lerp(a.x, b.x, t), lerp(a.y, b.y, t), lerp(a.z, b.z, t)); }

    public static int quadraticBezier(int a, int b, int c, float t) { return (int)quadraticBezier((float)a, (float)b, (float)c, t); }
    public static float quadraticBezier(float a, float b, float c, float t) { return (1 - t) * (1 - t) * a + 2 * (1 - t) * t * b + t * t * c; }
    public static Vector2 quadraticBezier(Vector2 a, Vector2 b, Vector2 c, float t) { return new Vector2(quadraticBezier(a.x, b.x, c.x, t), quadraticBezier(a.y, b.y, c.y, t)); }
    public static Vector3 quadraticBezier(Vector3 a, Vector3 b, Vector3 c, float t) { return new Vector3(quadraticBezier(a.x, b.x, c.x, t), quadraticBezier(a.y, b.y, c.y, t), quadraticBezier(a.z, b.z, c.z, t)); }

    public static int coserp(int a, int b, float t) { return (int)coserp((float)a, (float)b, t); }
    public static float coserp(float a, float b, float t) { return lerp(a, b, (1 - (float)Math.cos(t * Math.PI)) / 2); }
    public static Vector2 coserp(Vector2 a, Vector2 b, float t) { return new Vector2(coserp(a.x, b.x, t), coserp(a.y, b.y, t)); }
    public static Vector3 coserp(Vector3 a, Vector3 b, float t) { return new Vector3(coserp(a.x, b.x, t), coserp(a.y, b.y, t), coserp(a.z, b.z, t)); }

    public static int sinerp(int a, int b, float t) { return (int)sinerp((float)a, (float)b, t); }
    public static float sinerp(float a, float b, float t) { return lerp(a, b, (float)Math.sin(t * Math.PI / 2)); }
    public static Vector2 sinerp(Vector2 a, Vector2 b, float t) { return new Vector2(sinerp(a.x, b.x, t), sinerp(a.y, b.y, t)); }
    public static Vector3 sinerp(Vector3 a, Vector3 b, float t) { return new Vector3(sinerp(a.x, b.x, t), sinerp(a.y, b.y, t), sinerp(a.z, b.z, t)); }

    public static boolean ProjectionSurSegment(float Cx, float Cy, float Ax, float Ay, float Bx, float By)
    {
        float ACx = Cx - Ax;
        float ACy = Cy - Ay;
        float ABx = Bx - Ax;
        float ABy = By - Ay;
        float BCx = Cx - Bx;
        float BCy = Cy - By;
        float s1 = (ACx * ABx) + (ACy * ABy);
        float s2 = (BCx * ABx) + (BCy * ABy);
        return s1 * s2 > 0;
    }

    public static int clamp(int value, int min, int max)
    {
        if (value < min) { return min; }
        return Math.min(value, max);
    }

    public static double clamp(double value, double min, double max)
    {
        if (value < min) { return min; }
        return Math.min(value, max);
    }
}
