package com.scramble_like.game.component;

import com.badlogic.gdx.graphics.Color;
import com.scramble_like.game.essential.Component;

public class Text extends Component
{
    private String text;
    private int size;
    private Color color;

    public Text(String text) { super(); this.text = text; this.size = 1; this.color = Color.WHITE; }
    public Text(String text, int size, Color color) { super(); this.text = text; this.size = size; this.color = color; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    @Override
    public void Render()
    {
        this.getOwner().getScene().font.setColor(color);
        this.getOwner().getScene().font.getData().setScale(size);
        this.getOwner().getScene().font.draw(this.getOwner().getScene().batch, text, this.getOwner().getTransform().getLocation().x, this.getOwner().getTransform().getLocation().y);
        this.getOwner().getScene().font.getData().setScale(1);
        this.getOwner().getScene().font.setColor(Color.WHITE);
    }
}
