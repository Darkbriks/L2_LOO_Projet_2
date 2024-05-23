package com.scramble_like.game.essential;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.scramble_like.game.GameConstant;

public class ControllersListener implements ControllerListener
{
    @Override
    public void connected(Controller controller)
    {
        System.out.println("Controller connected: " + controller.getName());
        CoreConstant.UPDATE_MULTIPLIER = 1;
    }

    @Override
    public void disconnected(Controller controller)
    {
        System.out.println("Controller disconnected: " + controller.getName());
        CoreConstant.UPDATE_MULTIPLIER = 0;
    }

    @Override
    public boolean buttonDown(Controller controller, int i)
    {
        if (i == controller.getMapping().buttonX) { GameConstant.SHOOT_BUTTON = true; }
        else if (i == controller.getMapping().buttonR1) { GameConstant.SECONDARY_SHOOT_BUTTON = true; }
        else if (i == controller.getMapping().buttonStart) { GameConstant.PAUSE_BUTTON = true; }
        else if (i == controller.getMapping().buttonL1) { GameConstant.CAMERA_SPEED *= 5; }
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int i)
    {
        if (i == controller.getMapping().buttonX) { GameConstant.SHOOT_BUTTON = false; }
        else if (i == controller.getMapping().buttonR1) { GameConstant.SECONDARY_SHOOT_BUTTON = false; }
        else if (i == controller.getMapping().buttonStart) { GameConstant.PAUSE_BUTTON = false; }
        else if (i == controller.getMapping().buttonL1) { GameConstant.CAMERA_SPEED /= 5; }
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int i, float v) {
        if (i == controller.getMapping().axisLeftX) { GameConstant.X_AXIS_VALUE = v; }
        else if (i == controller.getMapping().axisLeftY) { GameConstant.Y_AXIS_VALUE = v; }
        return false;
    }
}
