package aiss.gitminer.controller;

import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueRepository issueRepository;

    @Autowired
    public IssueController(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @GetMapping
    public List<Issue> getIssues() {
        return issueRepository.findAll();
    }

    @GetMapping("/{id}")
    public Issue findById(@PathVariable String id){return issueRepository.findOneById(id);}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Issue create(@Valid @RequestBody Issue issue){ return issueRepository.create(issue); }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void update(@Valid @RequestBody Issue updatedIssue, @PathVariable String id){
        issueRepository.update(updatedIssue, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void delete(@PathVariable String id){issueRepository.delete(id);}
}
