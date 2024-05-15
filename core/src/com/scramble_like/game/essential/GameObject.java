package com.scramble_like.game.essential;

import com.scramble_like.game.essential.event_dispatcher.EventDispatcher;
import com.scramble_like.game.essential.exception.GameIsNullException;
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

    public GameObject(String name, Scene scene)
    {
        this.ID = globalID++;
        this.name = name;
        this.scene = scene;
        this.components = new ArrayList<>();
        this.transform = new Transform();
        this.eventDispatcher = new EventDispatcher();
    }

    public int getID() { return this.ID; }

    public String getName() { return this.name; }

    public Transform getTransform() { return this.transform; }

    public Scene getScene() { return this.scene; }

    public boolean IsActive() { return this.isActive; }

    public void setActive(boolean isActive) { this.isActive = isActive; }

    public boolean IsMarkedForDestruction() { return this.isMarkedForDestruction; }

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
        component.Init(this);
        this.components.add(component);
    }

    public void RemoveComponent(Component component) { this.components.remove(component); }

    public void RemoveAllComponents() { this.components.clear(); }

    public EventDispatcher getEventDispatcher() { return this.eventDispatcher; }

    public void BeginPlay()
    {
        for (Component c : this.components) { c.BeginPlay(); }
    }

    public void Update(double dt) throws GameIsNullException {
        if (!this.isActive) { return; }
        for (Component c : this.components) { c.Update(dt); }
    }

    public void Render()
    {
        if (!this.isActive) { return; }
        for (Component c : this.components) { c.Render(); }

        // Debug - Draw the GameObject's name
        //this.scene.font.draw(this.scene.batch, this.name, this.transform.getLocation().x, this.transform.getLocation().y);
    }

    public void Destroy()
    {
        for (Component c : this.components) { c.Destroy(); }
        this.components.clear();
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
