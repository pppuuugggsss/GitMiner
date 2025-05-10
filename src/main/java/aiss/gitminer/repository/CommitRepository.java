package aiss.gitminer.repository;

import aiss.gitminer.model.Commit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import java.util.Arrays;
import java.util.UUID;

@Repository
public class CommitRepository {
    private final RestTemplate restTemplate;
    @Value("${githubminer.api.comments}")
    private String commitsEndpoint;

    public CommitRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Commit> findAll() {
        Commit[] commits = restTemplate.getForObject(commitsEndpoint, Commit[].class);
        return Arrays.asList(commits);
    }

    public Commit findoneById(String id){
        return restTemplate.getForObject(commitsEndpoint + "/" + id, Commit.class);
    }

    public Commit create(Commit commit) {
        commit.setId(UUID.randomUUID().toString());
        return restTemplate.postForObject(commitsEndpoint, commit, Commit.class);
    }

    public void update(Commit updatedCommit, String id) {
        updatedCommit.setId(id);
        restTemplate.put(commitsEndpoint + "/" + id, updatedCommit);
    }

    public void delete(String id) {
        restTemplate.delete(commitsEndpoint + "/" + id);
    }
}
