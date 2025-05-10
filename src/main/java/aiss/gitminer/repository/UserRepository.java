package aiss.gitminer.repository;

import aiss.gitminer.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {
    private final RestTemplate restTemplate;
    @Value("${githubminer.api.users}")
    private String usersEndpoint;

    public UserRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> findAll(){
        User[] users = restTemplate.getForObject(usersEndpoint, User[].class);
        return Arrays.asList(users);
    }

    public User findOneById(String id) {
        return restTemplate.getForObject(usersEndpoint + "/" + id, User.class);
    }

    public User create(User user){
        user.setId(UUID.randomUUID().toString());
        return restTemplate.postForObject(usersEndpoint, user, User.class);
    }

    public void update(User updatedUser, String id){
        updatedUser.setId(id);
        restTemplate.put(usersEndpoint + "/" + id, updatedUser);
    }

    public void delete(String id){
        restTemplate.delete(usersEndpoint + "/" + id);
    }
}
