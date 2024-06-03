package com.robot.pilot.service.impl.position;

import com.robot.pilot.config.GridConfig;
import com.robot.pilot.model.Direction;
import com.robot.pilot.model.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PositionActionTest {

    @Mock
    private GridConfig gridConfig;
    @InjectMocks
    private PositionAction positionAction;

    private Robot robot;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(gridConfig.getWidth()).thenReturn(5);
        when(gridConfig.getHeight()).thenReturn(5);
        robot = new Robot();
    }

    @Test
    public void testExecutePosition() {
        positionAction.execute(robot, "POSITION 4 4 NORTH");

        assertEquals(4, robot.getCoordinates().getX());
        assertEquals(4, robot.getCoordinates().getY());
        assertEquals(Direction.NORTH, robot.getDirection());
    }

    @Test
    public void processScript_unsupportedOperation_throwsException() {

        String unsupportedScript = "POSITION 4 4 NOTRH";

        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            positionAction.execute(robot, unsupportedScript);
        });

        assertEquals("Invalid script format: " + unsupportedScript, exception.getMessage());

    }

    @Test
    public void processScript_invalidPosition_throwsException() {

        String unsupportedScript = "POSITION 6 6 NORTH";

        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            positionAction.execute(robot, unsupportedScript);
        });

        assertEquals("Invalid position: (6, 6)", exception.getMessage());

    }
}
