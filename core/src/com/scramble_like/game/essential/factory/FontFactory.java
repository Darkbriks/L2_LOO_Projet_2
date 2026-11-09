package com.scramble_like.game.essential.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Génère les polices du jeu à partir d'un TTF. Les glyphes sont rasterisés RESOLUTION fois plus grands
 * (avec mipmaps) puis affichés à l'échelle BASE_SCALE, ce qui garde le texte net lorsqu'il est agrandi.
 * Toute échelle de police appliquée à la main doit donc être multipliée par BASE_SCALE.
 */
public class FontFactory
{
    public static final String DEFAULT_FONT_PATH = "UI/NotoSans-Regular.ttf";
    public static final int DEFAULT_FONT_SIZE = 17;
    private static final int RESOLUTION = 4;
    public static final float BASE_SCALE = 1f / RESOLUTION;

    private FontFactory() {}

    /** Crée une nouvelle police (à disposer par l'appelant) de taille apparente DEFAULT_FONT_SIZE. */
    public static BitmapFont newDefaultFont()
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(DEFAULT_FONT_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = DEFAULT_FONT_SIZE * RESOLUTION;
        parameter.genMipMaps = true;
        parameter.minFilter = Texture.TextureFilter.MipMapLinearLinear;
        parameter.magFilter = Texture.TextureFilter.Linear;

        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        font.getData().setScale(BASE_SCALE);
        font.setUseIntegerPositions(false);
        return font;
    }
}
