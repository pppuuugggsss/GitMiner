package aiss.gitminer.controller;

import aiss.gitminer.model.User;
import aiss.gitminer.repository.CommentRepository;
import aiss.gitminer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> findAll(){return userRepository.findAll();}

    @GetMapping("/{id}")
    public User findbyId(@PathVariable String id){return userRepository.findOneById(id);}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@Valid @RequestBody User user){return userRepository.create(user);}

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody User updatedUser, @PathVariable String id ){
        userRepository.update(updatedUser, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){userRepository.delete(id);}

}
