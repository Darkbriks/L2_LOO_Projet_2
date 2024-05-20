package com.scramble_like.game.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ScoreActor extends Actor {
    private BitmapFont font;
    private int score;

    public ScoreActor(BitmapFont font) {
        this.font = font;
        this.score = 0; // Score initial
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, "Score: " + score, getX(), getY());
    }
}
