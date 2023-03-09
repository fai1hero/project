package com.example.projectmanager.dto;

import com.example.projectmanager.model.User;
import com.example.projectmanager.model.enums.TaskStatus;
import com.example.projectmanager.model.enums.TaskType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TaskDTO {

    private String name;

    @JsonProperty(value = "type")
    private TaskType type;

    private TaskStatus status;

    private Date createdAt;

    private Date statusChangedAt;

    private String information;

    private User creator;

//    private Project projectId;

    public TaskDTO() {
    }

//    public Project getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(Project projectId) {
//        this.projectId = projectId;
//    }

    public String getName() {
        return name;
    }

    public TaskType getType() {
        return type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getStatusChangedAt() {
        return statusChangedAt;
    }

    public String getInformation() {
        return information;
    }

    public User getCreator() {
        return creator;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatusChangedAt(Date statusChangedAt) {
        this.statusChangedAt = statusChangedAt;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }


}
