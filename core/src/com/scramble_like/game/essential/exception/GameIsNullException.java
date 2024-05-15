package com.scramble_like.game.essential.exception;

public class GameIsNullException extends Exception
{
    public GameIsNullException(String SceneName)
    {
        super("Game is null in " + SceneName + " scene, quitting...");
    }
}
