package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class SimpleBullet extends Projectile
{
    public SimpleBullet(Scene scene, Vector2 location) throws SceneIsNullException
    {
        super("Simple Bullet", scene, location, new Vector2(1, 0), 1000, 1000, true);
        this.damage = 10;
        this.AddComponent(new Sprite(GameConstant.CHARACTER_PATH("UnderwaterCharacterPack/MermaidGuard_2", "Projectile.png")));
         AABBCollider collider = this.GetFirstComponentFromClass(AABBCollider.class);
         collider.setHeight(10);
         collider.setWidth(10);
    }
}
