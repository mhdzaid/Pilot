package com.robot.pilot.service.impl;

import com.robot.pilot.exception.InvalidScriptException;
import com.robot.pilot.model.ActionType;
import com.robot.pilot.service.Action;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ActionFactoryTest {

    @Mock
    private Action directionAction;

    @Mock
    private Action movementAction;

    @Mock
    private Action positionAction;

    private ActionFactory actionFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(directionAction.getActionTypes()).thenReturn(List.of(ActionType.RIGHT, ActionType.TURNAROUND, ActionType.WAIT));
        when(movementAction.getActionTypes()).thenReturn(List.of(ActionType.FORWARD));
        when(positionAction.getActionTypes()).thenReturn(List.of(ActionType.POSITION));

        actionFactory = new ActionFactory(List.of(directionAction, movementAction, positionAction));
    }

    @Test
    public void testGetScriptActionForDirectionAction() {
        Action action = actionFactory.getScriptAction("RIGHT");

        assertEquals(directionAction, action);
    }

    @Test
    public void testGetScriptActionForMovementAction() {
        Action action = actionFactory.getScriptAction("FORWARD 3");

        assertEquals(movementAction, action);
    }

    @Test
    public void testGetScriptActionForPositionAction() {
        Action action = actionFactory.getScriptAction("POSITION 1 2 EAST");

        assertEquals(positionAction, action);
    }

    @Test
    public void testGetScriptActionThrowsExceptionForInvalidScript() {
        InvalidScriptException exception = assertThrows(InvalidScriptException.class, () -> {
            actionFactory.getScriptAction("INVALID");
        });

        assertEquals("Invalid script command: INVALID", exception.getMessage());
    }
}
