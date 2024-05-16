package com.scramble_like.game.essential.event_dispatcher;

public enum EventIndex
{
    // Physics events
    BEGIN_OVERLAP,
    END_OVERLAP,
    HIT,

    // Game Events
    DIE,

    // Chunk Events
    CHUNK_LOADED,
    CHUNK_UNLOADED,
    CHUNK_RENDERED,
    CHUNK_UNRENDERED,
}
