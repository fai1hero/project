package com.example.projectmanager.controller;

import com.example.projectmanager.dto.ProjectDTO;
import com.example.projectmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectDTO getProjectById(@PathVariable("id") int id) {
       return projectService.findProjectById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addProject(@RequestBody ProjectDTO newProject) {
        projectService.createProject(newProject);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ProjectDTO updateProject(@RequestBody ProjectDTO updatedProject, @PathVariable("id") int id) {
        projectService.updateProject(id, updatedProject);
        return projectService.findProjectById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
