package aiss.gitminer.controller;

import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/commits")
public class CommitController {
    private final CommitRepository commitRepository;

    @Autowired
    public CommitController(CommitRepository commitRepository) {
        this.commitRepository = commitRepository;
    }

    @GetMapping
    public List<Commit> findAll(){
        return commitRepository.findAll();
    }

    @GetMapping("/{id}")
    public Commit findById(@PathVariable String id){ return commitRepository.findoneById(id);}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Commit create(@Valid @RequestBody Commit commit){ return commitRepository.create(commit); }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Commit updatedCommit, @PathVariable String id){
        commitRepository.update(updatedCommit, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        commitRepository.delete(id);
    }



}
