package aiss.gitminer.repository;

import aiss.gitminer.model.Issue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class IssueRepository {
    private final RestTemplate restTemplate;
    @Value("${githubminer.api.issues}")
    private String issuesEndpoint;

    public IssueRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Issue> findAll() {
        Issue[] issues = restTemplate.getForObject(issuesEndpoint, Issue[].class);
        return Arrays.asList(issues);
    }

    public Issue findOneById(String id) {
        return restTemplate.getForObject(issuesEndpoint + "/" + id, Issue.class);
    }

    public Issue create(Issue issue) {
        issue.setId(UUID.randomUUID().toString());
        return restTemplate.postForObject(issuesEndpoint, issue, Issue.class);
    }

    public void update(Issue updatedIssue, String id) {
        updatedIssue.setId(id);
        restTemplate.put(issuesEndpoint + "/" + id, updatedIssue);
    }

    public void delete(String id) {
        restTemplate.delete(issuesEndpoint + "/" + id);
    }
}
