package com.scramble_like.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.scramble_like.game.essential.exception.GameIsNullException;
import com.scramble_like.game.map.MainMenu;

public class ScrambleLikeApplication extends Game
{
	@Override
	public void create ()
	{
		try { setScreen(new MainMenu(this)); }
		catch (GameIsNullException e) { System.err.println(e.getMessage()); Gdx.app.exit(); }
		dispose(); return;
	}
}