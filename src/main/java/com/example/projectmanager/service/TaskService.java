package com.example.projectmanager.service;

import com.example.projectmanager.dto.TaskDTO;
import com.example.projectmanager.model.Task;
import com.example.projectmanager.repo.ProjectRepository;
import com.example.projectmanager.repo.TaskRepository;
import com.example.projectmanager.repo.UserRepository;
import com.example.projectmanager.util.NoAccessException;
import com.example.projectmanager.util.TaskNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<TaskDTO> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(this::convertToTaskDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(int taskId) {
        return convertToTaskDTO(taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new));
    }

    public void createTask(int projectId, int userId, TaskDTO taskDTO) {
        Task task = convertToTask(taskDTO);
        task.setCreatedAt(new Date());
        task.setCreator(userRepository.findById(userId).orElseThrow(NoAccessException::new));
        task.setProjectId(projectRepository.findById(projectId).orElseThrow(TaskNotFoundException::new));
        taskRepository.save(task);
    }

    public void updateTask(int taskId, TaskDTO taskDTO) {
        Task newTask = convertToTask(taskDTO);
        Task taskToUpdate = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if (newTask.getCreator() != null) taskToUpdate.setCreator(newTask.getCreator());
        if (newTask.getStatus() != null && !(newTask.getStatus().equals(taskToUpdate.getStatus()))) {
            taskToUpdate.setStatus(newTask.getStatus());
            taskToUpdate.setStatusChangedAt(new Date());
        }
        if (newTask.getInformation() != null) taskToUpdate.setInformation(newTask.getInformation());
        if (newTask.getName() != null) taskToUpdate.setName(newTask.getName());
        if (newTask.getType() != null) taskToUpdate.setType(newTask.getType());
        taskRepository.save(taskToUpdate);
    }

    public void updateTaskStatus(int taskId, TaskDTO taskDTO) {
        Task newTask = convertToTask(taskDTO);
        Task taskToUpdate = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if (!(newTask.getStatus().equals(taskToUpdate.getStatus()))) {
            taskToUpdate.setStatus(newTask.getStatus());
            taskToUpdate.setStatusChangedAt(new Date());
        }
        taskRepository.save(taskToUpdate);
    }

    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }

    public TaskDTO convertToTaskDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    public Task convertToTask(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }
}
