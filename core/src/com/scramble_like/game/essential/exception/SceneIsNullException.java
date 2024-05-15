package com.scramble_like.game.essential.exception;

import com.scramble_like.game.essential.GameObject;

public class SceneIsNullException extends Exception
{
    public SceneIsNullException(GameObject gameObject)
    {
        super("Scene is null in " + gameObject.getName() + " GameObject of ID " + gameObject.getID() + ", destroying this.");
    }
}
