package com.satvik.restful_web_services.controller;

import com.satvik.restful_web_services.exception.UserNotFoundException;
import com.satvik.restful_web_services.repository.JpaPostsRepository;
import com.satvik.restful_web_services.repository.JpaUserRepository;
import com.satvik.restful_web_services.user.Posts;
import com.satvik.restful_web_services.user.User;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private JpaUserRepository userRepository;
    private JpaPostsRepository postsRepository;

    public UserJpaResource(JpaUserRepository userRepository, JpaPostsRepository postsRepository) {

        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
    }
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveSpecificUser(@PathVariable int id) {

        Optional<User> retrievedUser = userRepository.findById(id);
        if (retrievedUser.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        EntityModel<User> model = EntityModel.of(retrievedUser.get());
        WebMvcLinkBuilder link= WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(link.withRel("all-users"));
        return model;
    }
    @PostMapping("/jpa/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser) ;

    }
    @DeleteMapping("/jpa/users/{id}/Posts")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
    @GetMapping("/jpa/users/{id}/post")
    public List<Posts> getUserPosts(@PathVariable int id) {
        Optional<User> retrievedUser = userRepository.findById(id);
        if (retrievedUser.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        return retrievedUser.get().getPosts();
    }
    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Posts> createUserPosts(@PathVariable int id, @Valid @RequestBody Posts posts) {
        Optional<User> retrievedUser = userRepository.findById(id);
        if (retrievedUser.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        posts.setUser(retrievedUser.get());
        Posts savedPost= postsRepository.save(posts);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedPost) ;
    }
    @GetMapping("/jpa/users/{id}/post/{p_id}")
    public Posts getUserPosts(@PathVariable int id, @PathVariable int p_id) {
        Optional<User> retrievedUser = userRepository.findById(id);
        if (retrievedUser.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        List<Posts> retrievedPost= retrievedUser.get().getPosts();
        return retrievedPost.stream().filter(x-> x.getId()==p_id).findFirst().orElse(new Posts());
    }


}
