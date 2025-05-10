package aiss.gitminer.repository;

import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class ProjectRepository {
    private final RestTemplate restTemplate;
    @Value("${githubminer.api.projects}")
    private String projectsEndpoint;

    public ProjectRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Project> findAll(){
        Project[] projects = restTemplate.getForObject(projectsEndpoint + "/projects", Project[].class);
        return Arrays.asList(projects);
    }

    public Project findOneById(String id){
        return restTemplate.getForObject(projectsEndpoint + "/" + id, Project.class);
    }

    public Project create(Project project){
        project.setId(UUID.randomUUID().toString());
        return restTemplate.postForObject(projectsEndpoint , project, Project.class);
    }

    public void update(Project updatedProject, String id){
        updatedProject.setId(id);
        restTemplate.put(projectsEndpoint + "/" + id, updatedProject);
    }

    public void delete(String id){
        restTemplate.delete(projectsEndpoint + "/" + id);
    }

}
