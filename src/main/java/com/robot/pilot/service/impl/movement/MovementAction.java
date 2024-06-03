package com.robot.pilot.service.impl.movement;

import com.robot.pilot.config.GridConfig;
import com.robot.pilot.model.ActionType;
import com.robot.pilot.model.Coordinates;
import com.robot.pilot.model.Robot;
import com.robot.pilot.service.Action;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.robot.pilot.model.ActionType.FORWARD;

@Service
public class MovementAction implements Action {

    private final GridConfig gridConfig;

    public MovementAction(GridConfig gridConfig) {
        this.gridConfig = gridConfig;
    }

    @Override
    public void execute(Robot robot, String script) {
        Integer steps = Integer.parseInt(script.split(" ")[1]);
        Coordinates coordinates = robot.getCoordinates();
        switch (robot.getDirection()) {
            case NORTH -> coordinates.setY(Math.max(0, coordinates.getY() - steps));
            case EAST -> coordinates.setX(Math.min(gridConfig.getWidth() - 1, coordinates.getX() + steps));
            case SOUTH -> coordinates.setY(Math.min(gridConfig.getHeight() - 1, coordinates.getY() + steps));
            case WEST -> coordinates.setX(Math.max(0, coordinates.getX() - steps));
        }
    }

    @Override
    public List<ActionType> getActionTypes() {
        return List.of(FORWARD);
    }
}
