package com.robot.pilot.service.impl;

import com.robot.pilot.model.Coordinates;
import com.robot.pilot.model.Direction;
import com.robot.pilot.model.Robot;
import com.robot.pilot.service.Action;
import com.robot.pilot.exception.InvalidScriptException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class RobotServiceTest {

    @Mock
    private ActionFactory actionFactory;

    @Mock
    private Action action;

    @InjectMocks
    private RobotService robotService;

    private Robot robot;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        robot = new Robot(new Coordinates(0, 0), Direction.SOUTH);
        robotService = new RobotService(actionFactory);

        // Use reflection to set the private robot field
        Field robotField = RobotService.class.getDeclaredField("robot");
        robotField.setAccessible(true);
        robotField.set(robotService, robot);
    }

    @Test
    void processScript_invalidScript_throwsExceptionAndResetsRobot() {
        String script = "INVALID SCRIPT";

        when(actionFactory.getScriptAction(anyString())).thenThrow(new InvalidScriptException("Invalid script command"));

        InvalidScriptException exception = assertThrows(InvalidScriptException.class, () -> {
            robotService.processScript(script);
        });

        assertEquals("Invalid script command", exception.getMessage());

        // Ensure the robot is reset to its initial position
        assertEquals(0, robot.getCoordinates().getX());
        assertEquals(0, robot.getCoordinates().getY());
        assertEquals(Direction.SOUTH, robot.getDirection());

        verify(actionFactory, times(1)).getScriptAction(script);
    }

    @Test
    void processScript_unsupportedOperation_throwsExceptionAndResetsRobot() {
        String script = "POSITION 6 6 EAST";

        when(actionFactory.getScriptAction(anyString())).thenThrow(new UnsupportedOperationException("Invalid position"));

        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            robotService.processScript(script);
        });

        assertEquals("Invalid position", exception.getMessage());

        // Ensure the robot is reset to its initial position
        assertEquals(0, robot.getCoordinates().getX());
        assertEquals(0, robot.getCoordinates().getY());
        assertEquals(Direction.SOUTH, robot.getDirection());

        verify(actionFactory, times(1)).getScriptAction(script);
    }

    @Test
    void processScript_validScript_updatesRobotState() {
        String script = "POSITION 1 3 EAST";
        Robot updatedRobot = new Robot(new Coordinates(1, 3), Direction.EAST);

        when(actionFactory.getScriptAction(anyString())).thenReturn(action);
        doAnswer(invocation -> {
            Robot robot = invocation.getArgument(0);
            robot.setCoordinates(updatedRobot.getCoordinates());
            robot.setDirection(updatedRobot.getDirection());
            return null;
        }).when(action).execute(any(Robot.class), anyString());

        Robot result = robotService.processScript(script);

        assertEquals(1, result.getCoordinates().getX());
        assertEquals(3, result.getCoordinates().getY());
        assertEquals(Direction.EAST, result.getDirection());

        verify(actionFactory, times(1)).getScriptAction(script);
        verify(action, times(1)).execute(any(Robot.class), eq(script));
    }
}
