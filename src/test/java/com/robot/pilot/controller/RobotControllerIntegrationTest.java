package com.robot.pilot.controller;


import com.robot.pilot.model.Coordinates;
import com.robot.pilot.model.Direction;
import com.robot.pilot.model.Robot;
import com.robot.pilot.service.impl.RobotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RobotController.class)
public class RobotControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RobotService robotService;

    private Robot robot;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        robot = new Robot(new Coordinates(1, 3), Direction.EAST);
    }

    @Test
    void moveRobot_validScript_returnsUpdatedRobot() throws Exception {
        String script = "POSITION 1 3 EAST";

        // Mock the service call
        when(robotService.processScript(anyString())).thenReturn(robot);

        mockMvc.perform(post("/api/robot/move")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"script\":\"" + script + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.x", is(1)))
                .andExpect(jsonPath("$.y", is(3)))
                .andExpect(jsonPath("$.direction", is("EAST")));
    }

    @Test
    void moveRobot_forwardScript_returnsUpdatedRobot() throws Exception {
        String script = "FORWARD 2";
        robot = new Robot(new Coordinates(1, 3), Direction.EAST);
        Robot updatedRobot = new Robot(new Coordinates(3, 3), Direction.EAST);

        // Mock the service call
        when(robotService.processScript(anyString())).thenReturn(updatedRobot);

        mockMvc.perform(post("/api/robot/move")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"script\":\"" + script + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.x", is(3)))
                .andExpect(jsonPath("$.y", is(3)))
                .andExpect(jsonPath("$.direction", is("EAST")));
    }
}

