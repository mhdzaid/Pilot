package com.robot.pilot.service.impl.direction;

import com.robot.pilot.model.Coordinates;
import com.robot.pilot.model.Direction;
import com.robot.pilot.model.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionActionTest {

    @InjectMocks
    private DirectionAction directionAction;

    private Robot robot;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        robot = new Robot();
        robot.setCoordinates(new Coordinates(0, 0));
    }

    @Test
    public void testExecuteRight() {
        robot.setDirection(Direction.NORTH);
        directionAction.execute(robot, "RIGHT");

        assertEquals(Direction.EAST, robot.getDirection());
    }

    @Test
    public void testExecuteTurnaround() {
        robot.setDirection(Direction.NORTH);
        directionAction.execute(robot, "TURNAROUND");

        assertEquals(Direction.SOUTH, robot.getDirection());
    }

    @Test
    public void testExecuteWait() {
        Direction initialDirection = robot.getDirection();
        directionAction.execute(robot, "WAIT");

        assertEquals(initialDirection, robot.getDirection());
    }
}
