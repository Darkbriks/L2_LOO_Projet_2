package com.scramble_like.game.essential.utils;

public class Utils
{
    public static float lerp(float a, float b, float t) { return a + (b - a) * t; }

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
}
