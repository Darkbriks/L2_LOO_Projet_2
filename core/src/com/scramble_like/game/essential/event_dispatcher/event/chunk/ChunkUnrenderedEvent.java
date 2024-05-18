package com.scramble_like.game.essential.event_dispatcher.event.chunk;

import com.scramble_like.game.chunk.Chunk;

import java.util.EventObject;

public class ChunkUnrenderedEvent extends EventObject
{
    public Chunk chunk;
    public ChunkUnrenderedEvent(Chunk chunk) { super(chunk); this.chunk = chunk; }
}
