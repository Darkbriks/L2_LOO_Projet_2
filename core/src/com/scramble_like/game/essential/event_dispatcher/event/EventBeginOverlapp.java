package com.scramble_like.game.essential.event_dispatcher.event;

import com.scramble_like.game.essential.GameObject;

import java.util.EventObject;

public class EventBeginOverlapp extends EventObject
{
    public GameObject other;

    public EventBeginOverlapp(Object source, GameObject other)
    {
        super(source);
        this.other = other;
    }
}
