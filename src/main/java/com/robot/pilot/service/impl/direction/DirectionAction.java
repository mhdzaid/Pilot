package com.robot.pilot.service.impl.direction;

import com.robot.pilot.model.ActionType;
import com.robot.pilot.model.Robot;
import com.robot.pilot.service.Action;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.robot.pilot.model.ActionType.TURNAROUND;
import static com.robot.pilot.model.ActionType.WAIT;

@Service
public class DirectionAction implements Action {
    @Override
    public void execute(Robot robot, String script) {
        ActionType actionType = ActionType.valueOf(script.split(" ")[0]);
        switch (actionType) {
            case RIGHT:
                DirectionUtils.turnRight(robot);
                break;
            case TURNAROUND:
                DirectionUtils.turnAround(robot);
                break;
            case WAIT:
                break;
            default:
                throw new UnsupportedOperationException("Unsupported action type: " + actionType);
        }
    }

    @Override
    public List<ActionType> getActionTypes() {
        return List.of(ActionType.RIGHT, TURNAROUND, WAIT);
    }
}
