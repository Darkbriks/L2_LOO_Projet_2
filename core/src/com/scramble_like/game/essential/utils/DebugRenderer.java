package com.scramble_like.game.essential.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.ScrambleLikeApplication;

public class DebugRenderer
{
    public static void DrawDebugLine(float x1, float y1, float x2, float y2, Color color, Matrix4 projectionMatrix)
    {
        ScrambleLikeApplication.getInstance().getBatch().end();

        Gdx.gl.glLineWidth(2);
        ScrambleLikeApplication.getInstance().getShapeRenderer().setProjectionMatrix(projectionMatrix);
        ScrambleLikeApplication.getInstance().getShapeRenderer().begin(ShapeRenderer.ShapeType.Line);
        ScrambleLikeApplication.getInstance().getShapeRenderer().setColor(color);
        ScrambleLikeApplication.getInstance().getShapeRenderer().line(x1, y1, x2, y2);
        ScrambleLikeApplication.getInstance().getShapeRenderer().end();
        Gdx.gl.glLineWidth(1);

        ScrambleLikeApplication.getInstance().getBatch().begin();
    }

    public static void DrawDebugCircle(Vector2 point, int size, Color color, Matrix4 projectionMatrix)
    {
        ScrambleLikeApplication.getInstance().getBatch().end();

        ScrambleLikeApplication.getInstance().getShapeRenderer().setProjectionMatrix(projectionMatrix);
        ScrambleLikeApplication.getInstance().getShapeRenderer().begin(ShapeRenderer.ShapeType.Line);
        ScrambleLikeApplication.getInstance().getShapeRenderer().setColor(color);
        ScrambleLikeApplication.getInstance().getShapeRenderer().circle(point.x, point.y, size);
        ScrambleLikeApplication.getInstance().getShapeRenderer().end();

        ScrambleLikeApplication.getInstance().getBatch().begin();
    }

    public static void DrawDebugCircle(float x, float y, float radius, Color color, Matrix4 projectionMatrix)
    {
        ScrambleLikeApplication.getInstance().getBatch().end();

        ScrambleLikeApplication.getInstance().getShapeRenderer().setProjectionMatrix(projectionMatrix);
        ScrambleLikeApplication.getInstance().getShapeRenderer().begin(ShapeRenderer.ShapeType.Line);
        ScrambleLikeApplication.getInstance().getShapeRenderer().setColor(color);
        ScrambleLikeApplication.getInstance().getShapeRenderer().circle(x, y, radius);
        ScrambleLikeApplication.getInstance().getShapeRenderer().end();

        ScrambleLikeApplication.getInstance().getBatch().begin();
    }
}
