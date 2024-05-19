package com.scramble_like.game.essential.event_dispatcher.event.game;

import com.scramble_like.game.component.controller.ProjectileController;

import java.util.EventObject;

public class ProjectileReachDestinationEvent extends EventObject
{
    public ProjectileController controller;

    public ProjectileReachDestinationEvent(ProjectileController controller)
    {
        super(controller.getOwner());
        this.controller = controller;
    }
}