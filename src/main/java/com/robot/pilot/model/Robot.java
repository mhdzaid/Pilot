package com.robot.pilot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Robot {
    private Coordinates coordinates;
    private Direction direction;

    public Robot() {
        this.coordinates = new Coordinates(0, 0);
        this.direction = Direction.SOUTH;
    }

    public Robot copy() {
        return new Robot(new Coordinates(this.coordinates), this.direction);
    }
}
