package com.scramble_like.game.essential.event_dispatcher;

import com.scramble_like.game.essential.event_dispatcher.event.EventBeginOverlapp;
import com.scramble_like.game.essential.event_dispatcher.listener.ListenerBeginOverlapp;

import java.util.ArrayList;
import java.util.List;

public class EventDispatcher {
    private List<ListenerBeginOverlapp> listeners = new ArrayList<>();

    public void addListener(ListenerBeginOverlapp listener) {
        listeners.add(listener);
    }

    public void removeListener(ListenerBeginOverlapp listener) {
        listeners.remove(listener);
    }

    public void dispatchEvent(EventBeginOverlapp event) {
        for (ListenerBeginOverlapp listener : listeners) {
            //listener.handleEvent(event);
        }
    }
}