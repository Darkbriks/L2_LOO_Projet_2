package com.scramble_like.game.essential.event_dispatcher.event.chunk;

import com.scramble_like.game.utils.Chunk;

import java.util.EventObject;

public class ChunkRenderedEvent extends EventObject
{
    public Chunk chunk;
    public ChunkRenderedEvent(Chunk chunk) { super(chunk); this.chunk = chunk; }
}
