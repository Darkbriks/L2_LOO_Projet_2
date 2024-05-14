package com.scramble_like.game.essential.event_dispatcher.listener;

import com.scramble_like.game.essential.event_dispatcher.event.EventBeginOverlapp;

import java.util.EventListener;

public class ListenerBeginOverlapp
{
    void handleEvent(EventBeginOverlapp event) { System.out.println("Begin overlapp"); }
}