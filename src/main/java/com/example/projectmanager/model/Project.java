package com.example.projectmanager.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "main_project_id")
    public Integer mainProjectId;

    @OneToMany(mappedBy = "projectId", fetch = FetchType.EAGER)
    private List<Task> taskList;

    public Project() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
