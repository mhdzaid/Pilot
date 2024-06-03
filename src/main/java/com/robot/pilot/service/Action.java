package com.robot.pilot.service;

import com.robot.pilot.model.ActionType;
import com.robot.pilot.model.Robot;

import java.util.List;

public interface Action {
    void execute(Robot robot, String script);

    List<ActionType> getActionTypes();

}
