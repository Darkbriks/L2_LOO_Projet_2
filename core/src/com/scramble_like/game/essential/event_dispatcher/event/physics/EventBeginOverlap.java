package com.scramble_like.game.essential.event_dispatcher.event.physics;

import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;
import java.util.EventObject;

public class EventBeginOverlap extends EventObject
{
    public GameObject sourceGameObject;
    public Component overlappedComponent;
    public GameObject otherGameObject;
    public Component otherComponent;

    public EventBeginOverlap(GameObject sourceGameObject, Component overlappedComponent, GameObject otherGameObject, Component otherComponent)
    {
        super(sourceGameObject);
        this.sourceGameObject = sourceGameObject;
        this.overlappedComponent = overlappedComponent;
        this.otherGameObject = otherGameObject;
        this.otherComponent = otherComponent;
    }
}
