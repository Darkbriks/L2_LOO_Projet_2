package com.scramble_like.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AE_Label extends Label
{
    public AE_Label(CharSequence text, Skin skin) {
        super(text, skin);

        this.setColor(1, 1, 1, 1);
        this.setFontScale(2f);
        this.setAlignment(1);
        this.setWidth(500);
    }

    public AE_Label(CharSequence text, LabelStyle style) {
        super(text, style);

        this.setColor(1, 1, 1, 1);
        this.setFontScale(2f);
        this.setAlignment(1);
        this.setWidth(500);
    }

    public AE_Label(CharSequence text, Skin skin, String styleName) {
        super(text, skin, styleName);

        this.setColor(1, 1, 1, 1);
        this.setFontScale(2f);
        this.setAlignment(1);
        this.setWidth(500);
    }

    public AE_Label(CharSequence text, Skin skin, String fontName, Color color) {
        super(text, skin, fontName, color);

        this.setColor(1, 1, 1, 1);
        this.setFontScale(2f);
        this.setAlignment(1);
        this.setWidth(500);
    }

    public AE_Label(CharSequence text, Skin skin, String fontName, String colorName) {
        super(text, skin, fontName, colorName);

        this.setColor(1, 1, 1, 1);
        this.setFontScale(2f);
        this.setAlignment(1);
        this.setWidth(500);
    }
}
