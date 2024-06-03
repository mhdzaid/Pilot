package com.robot.pilot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScriptRequest {

    @NotBlank(message = "Script name must not be blank")
    private String script;
}
