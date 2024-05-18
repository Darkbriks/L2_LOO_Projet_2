package com.scramble_like.game.essential;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.scramble_like.game.GameConstant;

public class ControllersListener implements ControllerListener
{
    @Override
    public void connected(Controller controller) {
        System.out.println("Controller connected: " + controller.getName());
        GameConstant.UPDATE_MULTIPLIER = 1;
    }

    @Override
    public void disconnected(Controller controller) {
        System.out.println("Controller disconnected: " + controller.getName());
        GameConstant.UPDATE_MULTIPLIER = 0;
    }

    @Override
    public boolean buttonDown(Controller controller, int i) {
        if (i == controller.getMapping().buttonA) { System.out.println("Button A pressed"); }
        if (!controller.isVibrating()) { controller.startVibration(200, 1); }
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int i) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int i, float v) {
        //if (i == controller.getMapping().axisLeftX) { System.out.println("Axis Left X moved: " + v); }
        return false;
    }
}
