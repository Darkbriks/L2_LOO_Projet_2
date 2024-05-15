package com.scramble_like.game.essential.factory;


import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

public class ImageFactory
{
    private static final HashMap<String, Texture> textures = new HashMap<>();
    private static final HashMap<String, Integer> textureCount = new HashMap<>();

    public static Texture getTexture(String fileName)
    {
        Texture images = textures.get(fileName);
        if(images == null) { images = new Texture( fileName); textures.put(fileName, images); textureCount.put(fileName, 0); }
        textureCount.put(fileName, textureCount.get(fileName) + 1);
        return images ;
    }

    public static void disposeTexture(String fileName)
    {
        if(textureCount.get(fileName) == 1)
        {
            textures.get(fileName).dispose();
            textures.remove(fileName);
            textureCount.remove(fileName);
        }
        else
        {
            textureCount.put(fileName, textureCount.get(fileName) - 1);
        }
    }
}
