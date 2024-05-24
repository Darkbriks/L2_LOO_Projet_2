package com.scramble_like.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.GameCamera;
import com.scramble_like.game.essential.event_dispatcher.EventDispatcher;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.event.game.TextInfoFinishedEvent;

public class AE_Label extends Label
{
    protected float elapsedTime = 0;
    protected float duration = 0;
    protected boolean isDestroyedAfterDuration = false;
    protected EventDispatcher eventDispatcherToHandle = null;
    protected boolean followCamera = false;

    public AE_Label(CharSequence text, Skin skin) {
        super(text, skin);

        this.setColor(1, 1, 1, 1);
        this.setFontScale(2f);
        this.setAlignment(1);
        this.setWidth(500);
    }

    public AE_Label(CharSequence text, Skin skin, float duration, EventDispatcher eventDispatcherToHandle, boolean followCamera)
    {
        super(text, skin);

        this.setColor(1, 1, 1, 1);
        this.setFontScale(2f);
        this.setAlignment(1);
        this.setWidth(500);

        this.duration = duration;
        this.elapsedTime = 0;
        this.isDestroyedAfterDuration = true;
        this.eventDispatcherToHandle = eventDispatcherToHandle;
        this.followCamera = followCamera;
    }

    public AE_Label(CharSequence text, LabelStyle style) {
        super(text, style);

        this.setColor(1, 1, 1, 1);
        this.setFontScale(2f);
        this.setAlignment(1);
        this.setWidth(500);
    }

    public AE_Label(CharSequence text, Skin skin, String styleName) {
        super(text, skin, styleName);

        this.setColor(1, 1, 1, 1);
        this.setFontScale(2f);
        this.setAlignment(1);
        this.setWidth(500);
    }

    public AE_Label(CharSequence text, Skin skin, String fontName, Color color) {
        super(text, skin, fontName, color);

        this.setColor(1, 1, 1, 1);
        this.setFontScale(2f);
        this.setAlignment(1);
        this.setWidth(500);
    }

    public AE_Label(CharSequence text, Skin skin, String fontName, String colorName) {
        super(text, skin, fontName, colorName);

        this.setColor(1, 1, 1, 1);
        this.setFontScale(2f);
        this.setAlignment(1);
        this.setWidth(500);
    }
    public AE_Label(CharSequence text, Skin skin, Color color) {
        super(text, skin);
        this.setColor(color);
        this.setFontScale(2f);
        this.setAlignment(1);
        this.setWidth(500);
    }

    public void act(float delta)
    {
        super.act(delta);

        if (this.isDestroyedAfterDuration)
        {
            this.elapsedTime += delta;

            if (this.elapsedTime >= this.duration)
            {
                if (this.eventDispatcherToHandle != null)
                {
                    this.eventDispatcherToHandle.DispatchEvent(EventIndex.TEXT_INFO_FINISHED, new TextInfoFinishedEvent(this));
                }
                this.remove();
            }

            if (this.followCamera)
            {
                GameCamera camera = ScrambleLikeApplication.getInstance().getCamera();
                this.setPosition(camera.getPosition().x - this.getWidth() / 2, camera.getPosition().y - this.getHeight() / 2);
            }
        }
    }
}
