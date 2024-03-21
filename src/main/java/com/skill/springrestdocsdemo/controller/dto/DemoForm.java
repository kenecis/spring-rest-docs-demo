package com.skill.springrestdocsdemo.controller.dto;

import javax.validation.constraints.NotBlank;

public class DemoForm {

    @NotBlank
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
