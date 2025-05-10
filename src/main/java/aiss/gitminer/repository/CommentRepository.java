package aiss.gitminer.repository;

import aiss.gitminer.model.Comment;
import aiss.gitminer.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository

public class CommentRepository {
    private final RestTemplate restTemplate;
    @Value("${githubminer.api.comments}")
    private String commentsEndpoint;

    public CommentRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Comment> findAll() {
        Comment[] comments = restTemplate.getForObject(commentsEndpoint, Comment[].class);
        return Arrays.asList(comments);
    }

    public Comment findOneById(String id){
        return restTemplate.getForObject(commentsEndpoint + "/" + id, Comment.class);
    }

    public Comment create(Comment comment){
        comment.setId(UUID.randomUUID().toString());
        return restTemplate.postForObject(commentsEndpoint, comment, Comment.class);
    }

    public void update(Comment updatedComment, String id){
        updatedComment.setId(id);
        restTemplate.put(commentsEndpoint + "/" + id, updatedComment);
    }

    public void delete(String id){
        restTemplate.delete(commentsEndpoint + "/" + id);
    }

}
