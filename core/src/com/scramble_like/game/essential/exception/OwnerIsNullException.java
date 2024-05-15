package com.scramble_like.game.essential.exception;

import com.scramble_like.game.essential.Component;

public class OwnerIsNullException extends Exception
{
    public OwnerIsNullException(Component component)
    {
        super("Owner is null in " + component.getClass().getSimpleName() + " Component of ID " + component.getID() + ", destroying this.");
    }
}
