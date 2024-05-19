package com.scramble_like.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AE_TitleLabel extends AE_Label
{
    public AE_TitleLabel(CharSequence text, Skin skin) {
        super(text, skin);
        this.setColor(Color.CYAN);
        this.setFontScale(3f);
    }

    public AE_TitleLabel(CharSequence text, LabelStyle style) {
        super(text, style);
        this.setColor(Color.CYAN);
        this.setFontScale(3f);
    }

    public AE_TitleLabel(CharSequence text, Skin skin, String styleName) {
        super(text, skin, styleName);
        this.setColor(Color.CYAN);
        this.setFontScale(3f);
    }

    public AE_TitleLabel(CharSequence text, Skin skin, String fontName, Color color) {
        super(text, skin, fontName, color);
        this.setColor(Color.CYAN);
        this.setFontScale(3f);
    }

    public AE_TitleLabel(CharSequence text, Skin skin, String fontName, String colorName) {
        super(text, skin, fontName, colorName);
        this.setColor(Color.CYAN);
        this.setFontScale(3f);
    }
}
