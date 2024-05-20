package com.scramble_like.game.component.controller;

import com.scramble_like.game.component.paper2d.Flipbook;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.CoreConstant;

public class AnimationController extends Component
{
    public static enum AnimationState { IDLE, WALK, HURT, ATTACK, DIE }

    protected final String ANIMATION_IDLE; protected final int ANIMATION_IDLE_FRAME;
    protected final String ANIMATION_WALK; protected final int ANIMATION_WALK_FRAME;
    protected final String ANIMATION_HURT; protected final int ANIMATION_HURT_FRAME;
    protected final String ANIMATION_ATTACK; protected final int ANIMATION_ATTACK_FRAME;
    protected final String ANIMATION_DIE; protected final int ANIMATION_DIE_FRAME;

    protected String animationFolder;
    protected AnimationState state;
    protected int stateFrame;
    protected boolean stateChanged;
    protected int numberOfLoop; // -1, on joue en boucle, sinon on joue numberOfLoop fois
    protected float elapsedTime;
    protected Flipbook flipbook;

    public AnimationController(String animationFolder, Flipbook flipbook)
    {
        super();
        this.animationFolder = animationFolder;
        this.state = AnimationState.IDLE;
        this.stateFrame = 0;
        this.stateChanged = true;
        this.numberOfLoop = -1;
        this.elapsedTime = 0;
        this.flipbook = flipbook;

        this.ANIMATION_IDLE = "Idle.png"; this.ANIMATION_IDLE_FRAME = 4;
        this.ANIMATION_WALK = "Walk.png"; this.ANIMATION_WALK_FRAME = 4;
        this.ANIMATION_HURT = "Hurt.png"; this.ANIMATION_HURT_FRAME = 2;
        this.ANIMATION_ATTACK = "Attack.png"; this.ANIMATION_ATTACK_FRAME = 6;
        this.ANIMATION_DIE = "Death.png"; this.ANIMATION_DIE_FRAME = 6;
    }

    public AnimationController(String animationFolder, Flipbook flipbook, int[] animationFrames)
    {
        super();
        this.animationFolder = animationFolder;
        this.state = AnimationState.IDLE;
        this.stateFrame = 0;
        this.stateChanged = true;
        this.numberOfLoop = -1;
        this.elapsedTime = 0;
        this.flipbook = flipbook;

        this.ANIMATION_IDLE = "Idle.png"; this.ANIMATION_IDLE_FRAME = animationFrames[0];
        this.ANIMATION_WALK = "Walk.png"; this.ANIMATION_WALK_FRAME = animationFrames[1];
        this.ANIMATION_HURT = "Hurt.png"; this.ANIMATION_HURT_FRAME = animationFrames[2];
        this.ANIMATION_ATTACK = "Attack.png"; this.ANIMATION_ATTACK_FRAME = animationFrames[3];
        this.ANIMATION_DIE = "Death.png"; this.ANIMATION_DIE_FRAME = animationFrames[4];
    }

    public AnimationController(String animationFolder, Flipbook flipbook, String[] animationNames, int[] animationFrames)
    {
        super();
        if (animationNames.length != 5 || animationFrames.length != 5) { throw new IllegalArgumentException("Animation names and frames must have 5 elements"); }

        this.animationFolder = animationFolder;
        this.state = AnimationState.IDLE;
        this.stateFrame = 0;
        this.stateChanged = true;
        this.numberOfLoop = -1;
        this.elapsedTime = 0;
        this.flipbook = flipbook;

        this.ANIMATION_IDLE = animationNames[0]; this.ANIMATION_IDLE_FRAME = animationFrames[0];
        this.ANIMATION_WALK = animationNames[1]; this.ANIMATION_WALK_FRAME = animationFrames[1];
        this.ANIMATION_HURT = animationNames[2]; this.ANIMATION_HURT_FRAME = animationFrames[2];
        this.ANIMATION_ATTACK = animationNames[3]; this.ANIMATION_ATTACK_FRAME = animationFrames[3];
        this.ANIMATION_DIE = animationNames[4]; this.ANIMATION_DIE_FRAME = animationFrames[4];
    }

    public String getAnimationFolder() { return this.animationFolder; }
    public AnimationState getState() { return this.state; }
    public void setState(AnimationState state, int numberOfLoop)
    {
        if (this.numberOfLoop > 0) { return; }
        if (this.state == state) { return; }
        this.state = state;
        this.stateChanged = true;
        this.numberOfLoop = numberOfLoop;
        this.elapsedTime = 0;
    }

    @Override
    public void Update(float dt)
    {
        if (!this.IsActive()) { return; }

        if (this.numberOfLoop == 0) { setState(AnimationState.IDLE, -1); return; }

        this.elapsedTime += dt;
        if (this.elapsedTime >= CoreConstant.ANIMATION_FRAME_DURATION * this.stateFrame)
        {
            numberOfLoop = (numberOfLoop == -1) ? -1 : numberOfLoop - 1;
        }

        if (this.stateChanged)
        {
            this.stateChanged = false;
            switch (this.state)
            {
                case IDLE: this.flipbook.setFileName(this.animationFolder + ANIMATION_IDLE, ANIMATION_IDLE_FRAME); this.stateFrame = ANIMATION_IDLE_FRAME; break;
                case WALK: this.flipbook.setFileName(this.animationFolder + ANIMATION_WALK, ANIMATION_WALK_FRAME); this.stateFrame = ANIMATION_WALK_FRAME; break;
                case HURT: this.flipbook.setFileName(this.animationFolder + ANIMATION_HURT, ANIMATION_HURT_FRAME); this.stateFrame = ANIMATION_HURT_FRAME; break;
                case ATTACK: this.flipbook.setFileName(this.animationFolder + ANIMATION_ATTACK, ANIMATION_ATTACK_FRAME); this.stateFrame = ANIMATION_ATTACK_FRAME; break;
                case DIE: this.flipbook.setFileName(this.animationFolder + ANIMATION_DIE, ANIMATION_DIE_FRAME); this.stateFrame = ANIMATION_DIE_FRAME; break;
            }
        }
    }
}
