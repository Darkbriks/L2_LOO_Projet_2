package com.scramble_like.game.essential;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.event_dispatcher.EventDispatcher;
import com.scramble_like.game.essential.exception.OwnerIsNullException;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.utils.Transform;
import java.util.ArrayList;

public class GameObject
{
    private static int globalID = 0;
    private final int ID;
    private String name;
    private Transform transform;
    private Scene scene;
    private boolean isActive = true;
    private boolean isMarkedForDestruction = false;
    private ArrayList<Component> components;
    private final EventDispatcher eventDispatcher;

    public GameObject(String name, Scene scene) throws SceneIsNullException
    {
        this.ID = globalID++;
        this.name = name;
        this.scene = scene;
        this.components = new ArrayList<>();
        this.transform = new Transform();
        this.eventDispatcher = new EventDispatcher();
        if (this.scene == null) { this.Destroy(); this.Destroying(); throw new SceneIsNullException(this); }
    }

    public int getID() { return this.ID; }
    public String getName() { return this.name; }
    public Transform getTransform() { return this.transform; }
    public Scene getScene() { return this.scene; }
    public ScrambleLikeApplication getGame() { return this.scene.getGame(); }
    public GameCamera getCamera() { return this.scene.getCamera(); }
    public ShapeRenderer getShapeRenderer() { return this.scene.getGame().getShapeRenderer(); }
    public SpriteBatch getBatch() { return this.scene.getBatch(); }
    public BitmapFont getFont() { return this.scene.getFont(); }

    public boolean IsActive() { return this.isActive; }
    public boolean IsMarkedForDestruction() { return this.isMarkedForDestruction; }

    public void setActive(boolean isActive) { this.isActive = isActive; }

    public ArrayList<Component> GetComponents() { return this.components; }

    public <T extends Component> T GetFirstComponentFromClass(Class<T> componentClass)
    {
        for (Component c : this.components)
        {
            if (componentClass.isAssignableFrom(c.getClass()))
            {
                try { return componentClass.cast(c); }
                catch (ClassCastException e) { System.err.println("Error: " + e.getMessage()); }
            }
        }
        return null;
    }

    public <T extends Component> ArrayList<T> GetAllComponentsFromClass(Class<T> componentClass)
    {
        ArrayList<T> components = new ArrayList<>();
        for (Component c : this.components)
        {
            if (componentClass.isAssignableFrom(c.getClass()))
            {
                try { components.add(componentClass.cast(c)); }
                catch (ClassCastException e) { System.err.println("Error: " + e.getMessage()); }
            }
        }
        return components;
    }

    public void AddComponent(Component component)
    {
        try { component.Init(this); this.components.add(component); }
        catch (OwnerIsNullException e) { System.err.println("Error: " + e.getMessage()); }
    }

    public void RemoveComponent(Component component) { this.getScene().DestroyComponent(component); this.components.remove(component); }

    public void RemoveAllComponents() { for (Component c : this.components) { this.getScene().DestroyComponent(c); } this.components.clear(); }

    public EventDispatcher getEventDispatcher() { return this.eventDispatcher; }

    public void BeginPlay() { for (Component c : this.components) { c.BeginPlay(); } }

    public void Update(double dt)
    {
        if (!this.isActive) { return; }
        for (Component c : this.components) { c.Update(dt); }
    }

    public void Render()
    {
        if (!this.isActive) { return; }
        for (Component c : this.components) { c.Render(); }
    }

    public void Destroy()
    {
        for (Component c : this.components) { this.getScene().DestroyComponent(c); }
        this.isMarkedForDestruction = true;
    }

    public void Destroying()
    {
        this.name = null;
        this.transform = null;
        this.scene = null;
        this.components = null;
    }
}
