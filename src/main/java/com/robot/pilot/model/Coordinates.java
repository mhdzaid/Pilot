package com.robot.pilot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Coordinates {
    private Integer x;
    private Integer y;

    public Coordinates(Coordinates other) {
        this.x = other.x;
        this.y = other.y;
    }
}
