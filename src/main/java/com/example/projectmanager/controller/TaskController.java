package com.example.projectmanager.controller;

import com.example.projectmanager.dto.TaskDTO;
import com.example.projectmanager.security.MyUserDetails;
import com.example.projectmanager.service.TaskService;
import com.example.projectmanager.util.NoAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<TaskDTO> getAllTasks() {
        return taskService.getAll();
    }

    @GetMapping("/tasks/{id}")
    public TaskDTO getOneTask(@PathVariable("id") int id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<HttpStatus> createTask(@PathVariable("projectId") int projectId, @RequestBody TaskDTO taskDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        int creatorId = userDetails.getUser().getId();
        taskService.createTask(projectId, creatorId, taskDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/tasks/{taskId}/update")
    public ResponseEntity<HttpStatus> updateTask(@PathVariable("taskId") int taskId, @RequestBody TaskDTO taskDTO) {
        taskService.updateTask(taskId, taskDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/tasks/{taskId}/status")
    public ResponseEntity<HttpStatus> updateTaskStatus(@PathVariable("taskId") int taskId, @RequestBody TaskDTO taskDTO) {
        taskService.updateTaskStatus(taskId, taskDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("taskId") int taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        int creatorId = taskService.getTaskById(taskId).getCreator().getId();
        if (!(creatorId == userDetails.getUser().getId() || userDetails.getUser().getRole().equalsIgnoreCase("ADMIN"))) {
            throw new NoAccessException();
        }
        taskService.deleteTask(taskId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
