package com.robot.pilot.service.impl.position;

import com.robot.pilot.config.GridConfig;
import com.robot.pilot.model.ActionType;
import com.robot.pilot.model.Coordinates;
import com.robot.pilot.model.Direction;
import com.robot.pilot.model.Robot;
import com.robot.pilot.service.Action;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.robot.pilot.model.ActionType.POSITION;

@Service
public class PositionAction implements Action {

    private final GridConfig gridConfig;

    public PositionAction(GridConfig gridConfig) {
        this.gridConfig = gridConfig;
    }
    @Override
    public void execute(Robot robot, String script) {

        if (!isValidFormat(script)) {
            throw new UnsupportedOperationException("Invalid script format: " + script);
        }

        String[] parts = script.split(" ");
        Integer x = Integer.parseInt(parts[1]);
        Integer y = Integer.parseInt(parts[2]);

        if (x < 0 || x >= gridConfig.getWidth() || y < 0 || y >= gridConfig.getHeight()) {
            throw new UnsupportedOperationException("Invalid position: (" + x + ", " + y + ")");
        }

        Direction direction = Direction.valueOf(parts[3]);
        robot.setCoordinates(new Coordinates(x, y));
        robot.setDirection(direction);
    }

    @Override
    public List<ActionType> getActionTypes() {
        return List.of(POSITION);
    }

    private boolean isValidFormat(String script) {
        String regex = "^POSITION \\d+ \\d+ (NORTH|SOUTH|EAST|WEST)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(script);
        return matcher.matches();
    }
}
