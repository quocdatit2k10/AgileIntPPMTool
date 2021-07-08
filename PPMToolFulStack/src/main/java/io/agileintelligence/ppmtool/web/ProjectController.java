package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> mapError = mapValidationErrorService.MapValidationService(result);
        if ((mapError != null)) return mapError;

        Project createProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    /*
        Find project by Identifier
        @Param projectIdentifier
        @Return project object
     */
    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> findByIdentifier(@PathVariable String projectIdentifier) {

        Project getProject = projectService.findByProjectIdentifier(projectIdentifier);

        return new ResponseEntity<Project>(getProject, HttpStatus.OK);
    }

    /*
        Find all project
        @Param projectIdentifier
        @Return project object
     */
    @GetMapping("/all")
    public Iterable<Project> findAll() {

        return projectService.findAll();
    }
}
