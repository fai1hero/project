package com.example.projectmanager.dto;

import java.util.List;

public class ProjectDTO {

    private String projectName;

    public Integer mainProjectId;

    private List<TaskDTO> taskList;


    public ProjectDTO() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getMainProjectId() {
        return mainProjectId;
    }

    public void setMainProjectId(Integer mainProjectId) {
        this.mainProjectId = mainProjectId;
    }

    public List<TaskDTO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskDTO> taskList) {
        this.taskList = taskList;
    }


}


