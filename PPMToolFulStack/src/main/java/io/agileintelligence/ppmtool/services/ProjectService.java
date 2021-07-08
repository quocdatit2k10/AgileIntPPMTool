package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase() + " already exist");
        }

    }

    public Project findByProjectIdentifier(String projectIdentifier) {

        Project getProject = projectRepository.findByProjectIdentifier(projectIdentifier);

        if (getProject == null) {
            throw new ProjectIdException("Project Identifier " + projectIdentifier + " does not exist");
        }
        return getProject;
    }

    public Iterable<Project> findAll() {

        return projectRepository.findAll();
    }

    public void deleteProject(String projectIdentifier) {

        Project getProject = projectRepository.findByProjectIdentifier(projectIdentifier);

        if (getProject == null) {
            throw new ProjectIdException("Project Identifier " + projectIdentifier + " does not exist");
        }

        projectRepository.delete(getProject);
    }
}
