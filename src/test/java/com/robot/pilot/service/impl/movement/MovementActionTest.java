package com.robot.pilot.service.impl.movement;

import com.robot.pilot.config.GridConfig;
import com.robot.pilot.model.Coordinates;
import com.robot.pilot.model.Direction;
import com.robot.pilot.model.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MovementActionTest {

    @Mock
    private GridConfig gridConfig;

    @InjectMocks
    private MovementAction movementAction;

    private Robot robot;
    private Coordinates coordinates;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(gridConfig.getWidth()).thenReturn(5);
        when(gridConfig.getHeight()).thenReturn(5);

        coordinates = new Coordinates(2, 2);
        robot = new Robot();
        robot.setCoordinates(coordinates);
    }

    @Test
    public void testExecuteForwardNorth() {
        robot.setDirection(Direction.NORTH);
        movementAction.execute(robot, "FORWARD 3");

        assertEquals(2, coordinates.getX());
        assertEquals(0, coordinates.getY());
    }

    @Test
    public void testExecuteForwardEast() {
        robot.setDirection(Direction.EAST);
        movementAction.execute(robot, "FORWARD 3");

        assertEquals(4, coordinates.getX());
        assertEquals(2, coordinates.getY());
    }

    @Test
    public void testExecuteForwardSouth() {
        robot.setDirection(Direction.SOUTH);
        movementAction.execute(robot, "FORWARD 3");

        assertEquals(2, coordinates.getX());
        assertEquals(4, coordinates.getY());
    }

    @Test
    public void testExecuteForwardWest() {
        robot.setDirection(Direction.WEST);
        movementAction.execute(robot, "FORWARD 3");

        assertEquals(0, coordinates.getX());
        assertEquals(2, coordinates.getY());
    }

    @Test
    public void testExecuteForwardNorthWithinBounds() {
        robot.setDirection(Direction.NORTH);
        movementAction.execute(robot, "FORWARD 1");

        assertEquals(2, coordinates.getX());
        assertEquals(1, coordinates.getY());
    }

    @Test
    public void testExecuteForwardEastWithinBounds() {
        robot.setDirection(Direction.EAST);
        movementAction.execute(robot, "FORWARD 1");

        assertEquals(3, coordinates.getX());
        assertEquals(2, coordinates.getY());
    }

    @Test
    public void testExecuteForwardSouthWithinBounds() {
        robot.setDirection(Direction.SOUTH);
        movementAction.execute(robot, "FORWARD 1");

        assertEquals(2, coordinates.getX());
        assertEquals(3, coordinates.getY());
    }

    @Test
    public void testExecuteForwardWestWithinBounds() {
        robot.setDirection(Direction.WEST);
        movementAction.execute(robot, "FORWARD 1");

        assertEquals(1, coordinates.getX());
        assertEquals(2, coordinates.getY());
    }
}
