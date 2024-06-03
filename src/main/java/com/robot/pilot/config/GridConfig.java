package com.robot.pilot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class GridConfig {

    @Value("${grid.width}")
    private int width;

    @Value("${grid.height}")
    private int height;
}
