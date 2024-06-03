package com.robot.pilot.service.impl;

import com.robot.pilot.exception.InvalidScriptException;
import com.robot.pilot.model.Robot;
import com.robot.pilot.service.Action;
import org.springframework.stereotype.Service;

@Service
public class RobotService {
    private final ActionFactory actionFactory;
    private Robot robot;

    public RobotService(ActionFactory actionFactory) {
        this.actionFactory = actionFactory;
        this.robot = new Robot();
    }

    public Robot processScript(String script) {
        Robot previousState = robot.copy();
        String[] commands = script.split("\n");
        try{
            for (String command : commands) {
                Action action = actionFactory.getScriptAction(command);
                action.execute(robot, command);
            }
        } catch (UnsupportedOperationException | InvalidScriptException exception) {
            robot = previousState;
            throw exception;
        }
        return robot;
    }
}
