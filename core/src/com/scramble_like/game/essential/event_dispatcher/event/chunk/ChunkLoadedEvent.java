package com.scramble_like.game.essential.event_dispatcher.event.chunk;

import com.scramble_like.game.utils.Chunk;
import java.util.EventObject;

public class ChunkLoadedEvent extends EventObject
{
    public Chunk chunk;
    public ChunkLoadedEvent(Chunk chunk) { super(chunk); this.chunk = chunk; }
}
