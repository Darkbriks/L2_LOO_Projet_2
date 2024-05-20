package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.utils.Timer;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class SplashScreen extends Scene {
    private Texture logo;
    private SpriteBatch batch;
    private BitmapFont font;
    private final float displayTime = 3.0f;
    private final float textDisplayTime = 2.0f;

    private float elapsedTime = 0;
    private boolean showText = false;

    public SplashScreen() {
        super("SplashScreen");
        this.backgroundColor = new Vector4(0, 0, 0, 1);
    }

    @Override
    public void show() {
        logo = new Texture(Gdx.files.internal("Logo/codingGladiators.jpeg"));
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);

        float delayBeforeText = 0.5f;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                showText = true;
                elapsedTime = 0;
            }
        }, displayTime + delayBeforeText);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                ScrambleLikeApplication.getInstance().setScreen(new MainMenu());
            }
        }, displayTime + delayBeforeText + textDisplayTime);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        elapsedTime += delta;

        Gdx.gl.glClearColor(backgroundColor.x, backgroundColor.y, backgroundColor.z, backgroundColor.w);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        batch.begin();
        if (!showText) {
            float progress = Math.min(elapsedTime / displayTime, 1.0f);

            float initialScale = 0.5f;
            float finalScale = 1.0f;
            float scale = initialScale + (finalScale - initialScale) * progress;
            float initialAlpha = 1.0f;
            float finalAlpha = 0.0f;
            float alpha = initialAlpha + (finalAlpha - initialAlpha) * progress;

            batch.setColor(1, 1, 1, alpha);
            batch.draw(logo,(Gdx.graphics.getWidth() - logo.getWidth() * scale) / 2,(Gdx.graphics.getHeight() - logo.getHeight() * scale) / 2,logo.getWidth() * scale,logo.getHeight() * scale);
        } else {
            float progress = Math.min(elapsedTime / textDisplayTime, 1.0f);
            float alpha = 1.0f - progress;
            font.setColor(1, 1, 1, alpha);

            String text = "The Coding Gladiators";
            float textWidth = font.getRegion().getRegionWidth();
            float textHeight = font.getCapHeight();
            font.draw(batch, text,(Gdx.graphics.getWidth() - textWidth) / 2,(Gdx.graphics.getHeight() + textHeight) / 2);
        }
        batch.end();
    }

    @Override
    public void hide() {
        batch.dispose();
        logo.dispose();
        font.dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        if (batch != null) batch.dispose();
        if (logo != null) logo.dispose();
        if (font != null) font.dispose();
    }
}
