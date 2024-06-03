package com.robot.pilot.service.impl;

import com.robot.pilot.exception.InvalidScriptException;
import com.robot.pilot.service.Action;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionFactory {

    private final List<Action> actions;

    public ActionFactory(List<Action> actions) {
        this.actions = actions;
    }

    public Action getScriptAction(String script) {

        Optional<Action> action = actions.stream()
                .filter(a -> a.getActionTypes().stream()
                        .anyMatch(actionType -> script.startsWith(actionType.name())))
                .findFirst();
        if(action.isEmpty()){
            throw new InvalidScriptException("Invalid script command: " + script);
        }
        return action.get();
    }
}
