package com.example.projectmanager.service;

import com.example.projectmanager.dto.ProjectDTO;
import com.example.projectmanager.model.Project;
import com.example.projectmanager.repo.ProjectRepository;
import com.example.projectmanager.util.ProjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProjectDTO> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(this::convertToProjectDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO findProjectById(int id) {
        return convertToProjectDTO(projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new));
    }

    public void createProject(ProjectDTO projectDTO) {

        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setMainProjectId(projectDTO.getMainProjectId());
        if (project.getMainProjectId() != null) findProjectById(project.mainProjectId);
        projectRepository.save(project);
    }

    public void updateProject(int id, ProjectDTO updatedProjectDTO) {
        Project projectToUpdate = projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
        Project updatedProject = convertToProject(updatedProjectDTO);

        if (updatedProject.getMainProjectId() != null)
            projectToUpdate.setMainProjectId(updatedProject.getMainProjectId());
        if (updatedProject.getProjectName() != null) projectToUpdate.setProjectName(updatedProject.getProjectName());
        if (updatedProject.getTaskList() != null) projectToUpdate.setTaskList(updatedProject.getTaskList());

        projectToUpdate.setId(id);
        projectRepository.save(projectToUpdate);
    }

    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }

    public ProjectDTO convertToProjectDTO(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    public Project convertToProject(ProjectDTO projectDTO) {
        return modelMapper.map(projectDTO, Project.class);
    }
}
