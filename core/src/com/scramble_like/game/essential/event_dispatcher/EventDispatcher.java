package com.scramble_like.game.essential.event_dispatcher;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.Map;

public class EventDispatcher
{
    private final Map<EventIndex, ArrayList<EventListener>> listeners = new java.util.HashMap<>();

    public void AddListener(EventIndex EventID, EventListener listener) { if (!listeners.containsKey(EventID)) listeners.put(EventID, new ArrayList<>()); listeners.get(EventID).add(listener); }

    public void RemoveListener(EventIndex EventID, EventListener listener) { if (!listeners.containsKey(EventID)) return; listeners.get(EventID).remove(listener); }

    public void DispatchEvent(EventIndex EventID, EventObject event)
    {
        if (!listeners.containsKey(EventID)) return;
        for (EventListener listener : listeners.get(EventID)) { listener.handleEvent(event); }
    }
}