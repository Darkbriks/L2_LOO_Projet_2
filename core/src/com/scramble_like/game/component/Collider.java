package com.scramble_like.game.component;

import com.badlogic.gdx.math.Rectangle;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;

import java.util.ArrayList;
import java.util.Iterator;

public class Collider extends Component {
    private Rectangle hitbox;
    private boolean generateOverlappedEvent = false;
    private float width;
    private float height;
    private float x;
    private float y;
    private boolean simulatePhysics;

    public Collider(float x, float y, float w, float h) {
        /*this.x = this.getOwner().getTransform().getLocation().x;
        this.y = this.getOwner().getTransform().getLocation().y;*/
        this.simulatePhysics = false;
        this.hitbox = new Rectangle();
        this.hitbox.x = x;
        this.hitbox.y = y;
        this.hitbox.height = h;
        this.hitbox.width = h;
    }

    @Override
    public void Init(GameObject Owner)
    {
        super.Init(Owner);
        this.x = this.getOwner().getTransform().getLocation().x;
        this.y = this.getOwner().getTransform().getLocation().y;
    }

    public void setHeight(int height) {
        this.hitbox.height = (float)height;
        this.height = (float)height;
    }

    public float getHeight() {
        return this.height;
    }

    public void setWidth(int width) {
        this.hitbox.width = (float)width;
        this.width = (float)width;
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setGenerateOverlapedEvent(boolean generateOverlappedEvent) {
        this.generateOverlappedEvent = generateOverlappedEvent;
    }

    public boolean isGenerateOverlappedEvent() {
        return this.generateOverlappedEvent;
    }

    public Rectangle getHitbox() {
        return this.hitbox;
    }

    public void setHitbox(int w, int h) {
        this.setWidth(w);
        this.setHeight(h);
    }

    public void detect(GameObject other) {
        ArrayList<Collider> l = other.GetAllComponentsFromClass(Collider.class);
        Iterator var3 = l.iterator();

        while(true) {
            while(var3.hasNext()) {
                Collider c = (Collider)var3.next();
                float left1 = c.hitbox.x;
                float right1 = c.hitbox.x + c.hitbox.width;
                float top1 = c.hitbox.y;
                float bottom1 = c.hitbox.y + c.hitbox.height;
                float left2 = this.hitbox.x;
                float right2 = this.hitbox.x + this.hitbox.width;
                float top2 = this.hitbox.y;
                float bottom2 = this.hitbox.y + this.hitbox.height;
                if (right1 >= left2 && left1 <= right2 && bottom1 >= top2 && top1 <= bottom2 && this.isGenerateOverlappedEvent()) {
                    this.beginOverlap(c);
                    c.hitbox.setX(0);
                } else {
                    this.hit(c);
                    c.hitbox.setX(100);
                }
            }

            return;
        }
    }

    private void hit(Collider other)
    {
        this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.HIT, new EventBeginOverlap(this.getOwner(), this, other.getOwner(), other));
    }

    private void beginOverlap(Collider other)
    {
        this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.BEGIN_OVERLAP, new EventBeginOverlap(this.getOwner(), this, other.getOwner(), other));
    }

    private void endOverlap(Collider other)
    {
        this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.END_OVERLAP, new EventBeginOverlap(this.getOwner(), this, other.getOwner(), other));
    }

    public void Update(double DeltaTime) {
        ArrayList<GameObject> g = new ArrayList();
        g.add(this.Owner);
        ArrayList<GameObject> l = this.getOwner().getScene().GetGameObjects(g);
        Iterator var5 = l.iterator();

        while(var5.hasNext()) {
            GameObject iter = (GameObject)var5.next();
            this.detect(iter);
        }

    }

    public void Render() {
    }
}
