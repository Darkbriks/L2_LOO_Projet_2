package com.scramble_like.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LifeActor extends Actor {
    private final TextureRegion[] regions;
    private TextureRegion currentRegion;

    public LifeActor(TextureRegion[] regions) {
        this.regions = regions;
        this.currentRegion = regions[0]; // Initialiser avec la première région
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Dessiner le TextureRegion actuel à la position et taille de l'acteur
        batch.draw(currentRegion, getX(), getY(), getWidth(), getHeight());
    }

    public void setCurrentRegion(int index) {
        if (index >= 0 && index < regions.length) {
            this.currentRegion = regions[index];
        }
    }
}