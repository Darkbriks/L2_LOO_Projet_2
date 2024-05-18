package com.scramble_like.game.component;

import com.scramble_like.game.essential.Component;
import com.scramble_like.game.chunk.Chunk;

public class TestComponent extends Component
{
    public int ScaleDirection = 1;
    Chunk chunk;

    public TestComponent(String fileName)
    {
        super();

        /*chunk = new Chunk(fileName);
        chunk.getEventDispatcher().AddListener(EventIndex.CHUNK_LOADED, new EventListener() {
            @Override
            public void handleEvent(EventObject event)
            {
                ChunkLoadedEvent chunkLoadedEvent = (ChunkLoadedEvent) event;
                chunkLoadedEvent.chunk.renderAsynchronously();
            }
        });
        chunk.loadAsynchronously();*/
    }

    @Override
    public void Update(double DeltaTime)
    {
        /*if (!this.IsActive()) { return; }
        this.getOwner().getTransform().getRotation().x += (float) (10 * DeltaTime);
        this.getOwner().getTransform().getScale().x += (float) (0.5f * DeltaTime * ScaleDirection);
        this.getOwner().getTransform().getScale().y += (float) (0.5f * DeltaTime * ScaleDirection);
        if (this.getOwner().getTransform().getScale().x > 1.5) { ScaleDirection = -1; }
        if (this.getOwner().getTransform().getScale().x < 0.5) { ScaleDirection = 1; }*/
    }

    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }
        //if (chunk.isRendered()) { chunk.draw(this.getOwner().getShapeRenderer(), this.getOwner().getBatch()); }
    }
}
