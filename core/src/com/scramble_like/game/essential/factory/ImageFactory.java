package com.scramble_like.game.essential.factory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;

public class ImageFactory
{
    private static final ObjectMap<String, Texture> textureMap = new ObjectMap<>();
    private static final ObjectMap<String, TextureRegion[]> textureRegionMap = new ObjectMap<>();
    private static final ObjectMap<String, Integer> textureCount = new ObjectMap<>();

    public static void loadTexture(String path)
    {
        if(textureMap.containsKey(path))
        {
            textureCount.put(path, textureCount.get(path) + 1);
        }
        else
        {
            textureMap.put(path, new Texture(path));
            textureCount.put(path, 1);
        }
    }

    public static void loadTextureRegion(String path, int nbSprite)
    {
        if(textureRegionMap.containsKey(path))
        {
            textureCount.put(path, textureCount.get(path) + 1);
        }
        else
        {
            if (!textureMap.containsKey(path)) { loadTexture(path); }

            TextureRegion[][] tmp = TextureRegion.split(textureMap.get(path),
                    textureMap.get(path).getWidth() / nbSprite,
                    textureMap.get(path).getHeight());
            TextureRegion[] frames = new TextureRegion[nbSprite];
            for (int j = 0; j < nbSprite; j++) { frames[j] = tmp[0][j]; }
            textureRegionMap.put(path, frames);
            textureCount.put(path, 1);
        }
    }

    public static Texture getTexture(String path)
    {
        if(!textureMap.containsKey(path)) { loadTexture(path); }
        return textureMap.get(path);
    }

    public static TextureRegion[] getTextureRegion(String path, int nbSprite)
    {
        if(!textureRegionMap.containsKey(path)) { loadTextureRegion(path, nbSprite); }
        return textureRegionMap.get(path);
    }

    public static TextureRegion getTextureRegion(String path, int nbSprite, int index)
    {
        if(!textureRegionMap.containsKey(path)) { loadTextureRegion(path, nbSprite); }
        return textureRegionMap.get(path)[index];
    }

    public static Vector2 getTextureSize(String path)
    {
        if(!textureMap.containsKey(path)) { loadTexture(path); }
        return new Vector2(textureMap.get(path).getWidth(), textureMap.get(path).getHeight());
    }

    public static void disposeTexture(String path)
    {
        if(textureMap.containsKey(path))
        {
            textureCount.put(path, textureCount.get(path) - 1);
            if(textureCount.get(path) == 0)
            {
                textureMap.get(path).dispose();
                textureMap.remove(path);
                textureRegionMap.remove(path);
                textureCount.remove(path);
            }
        }
    }
}
