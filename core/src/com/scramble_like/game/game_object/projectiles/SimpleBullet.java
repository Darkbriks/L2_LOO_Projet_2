package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class SimpleBullet extends Projectile
{
    public SimpleBullet(Scene scene, Vector2 location) throws SceneIsNullException
    {
        super("Simple Bullet", scene, location, new Vector2(-1, 0), 1000, 300);
        this.getTransform().setScale(new Vector2(0.2f, 0.2f));
        this.damage = 10;
        this.AddComponent(new Sprite(GameConstant.CHARACTER_PATH("UnderwaterCharacterPack", "Projectile.png")));
    }
}
