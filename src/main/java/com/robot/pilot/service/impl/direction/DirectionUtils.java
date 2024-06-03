package com.robot.pilot.service.impl.direction;

import com.robot.pilot.model.Robot;

import static com.robot.pilot.model.Direction.*;

public class DirectionUtils {

    public static void turnRight(Robot robot) {
        switch (robot.getDirection()) {
            case NORTH -> robot.setDirection(EAST);
            case EAST -> robot.setDirection(SOUTH);
            case SOUTH -> robot.setDirection(WEST);
            case WEST -> robot.setDirection(NORTH);
        }
    }

    public static void turnAround(Robot robot) {
        switch (robot.getDirection()) {
            case NORTH -> robot.setDirection(SOUTH);
            case EAST -> robot.setDirection(WEST);
            case SOUTH -> robot.setDirection(NORTH);
            case WEST -> robot.setDirection(EAST);
        }
    }

    public static void wait(Robot robot) {
        // No operation for WAIT
    }
}
