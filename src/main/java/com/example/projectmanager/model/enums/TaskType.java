package com.example.projectmanager.model.enums;


import com.fasterxml.jackson.annotation.JsonProperty;

public enum TaskType {

    @JsonProperty("MANAGER")
    MANAGER,
    @JsonProperty("TECH_SPEC")
    TECH_SPEC;

    TaskType() {
    }


}
