package com.robot.pilot.controller;

import com.robot.pilot.dto.RobotDTO;
import com.robot.pilot.dto.ScriptRequest;
import com.robot.pilot.mapper.RobotMapper;
import com.robot.pilot.model.Robot;
import com.robot.pilot.service.impl.RobotService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/robot")
@CrossOrigin(origins = "http://localhost:4200")
public class RobotController {

    private final RobotService robotService;

    @PostMapping("/move")
    public ResponseEntity<RobotDTO> moveRobot(@RequestBody @Valid ScriptRequest scriptRequest) {
        Robot robot = robotService.processScript(scriptRequest.getScript());
        return ResponseEntity.ok(RobotMapper.toRobotDTO(robot));
    }
}
