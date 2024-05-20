package com.scramble_like.game.essential.event_dispatcher.event.chunk;

import com.scramble_like.game.essential.chunk.Chunk;

import java.util.EventObject;

public class ChunkUnloadedEvent extends EventObject
{
    public Chunk chunk;
    public ChunkUnloadedEvent(Chunk chunk) { super(chunk); this.chunk = chunk; }
}
