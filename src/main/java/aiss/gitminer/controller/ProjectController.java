package aiss.gitminer.controller;

import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<Project> findAll(){return projectRepository.findAll();}

    @GetMapping("/{id}")
    public Project findbyId(@PathVariable String id){return projectRepository.findOneById(id);}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Project create(@Valid @RequestBody Project project){return projectRepository.create(project);}

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Project project, @PathVariable String id){
        projectRepository.update(project, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        projectRepository.delete(id);
    }
}
