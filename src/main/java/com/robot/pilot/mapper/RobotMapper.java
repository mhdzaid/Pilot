package com.robot.pilot.mapper;

import com.robot.pilot.dto.RobotDTO;
import com.robot.pilot.model.Robot;

public class RobotMapper {

    public static RobotDTO toRobotDTO(Robot robot) {
        if (robot == null) {
            return null;
        }

        return new RobotDTO(robot.getCoordinates().getX(), robot.getCoordinates().getY(), robot.getDirection().name());
    }
}
