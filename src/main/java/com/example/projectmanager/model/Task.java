package com.example.projectmanager.model;

import com.example.projectmanager.model.enums.TaskStatus;
import com.example.projectmanager.model.enums.TaskType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "task_name")
    private String name;

    @Column(name = "task_type")
    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "type")
    private TaskType type;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;


    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "status_changed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusChangedAt;

    @Column(name = "information")
    private String information;

    @ManyToOne
    @JoinColumn(name = "usr_id", referencedColumnName = "id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project projectId;

    public Task() {
    }

    public Task(String name, TaskType type, TaskStatus status, String information, Project projectId) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.information = information;
        this.projectId = projectId;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getStatusChangedAt() {
        return statusChangedAt;
    }

    public void setStatusChangedAt(Date statusChangedAt) {
        this.statusChangedAt = statusChangedAt;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

}
