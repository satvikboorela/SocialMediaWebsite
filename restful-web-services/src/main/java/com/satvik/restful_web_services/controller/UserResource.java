//package com.satvik.restful_web_services.controller;
//
//import com.satvik.restful_web_services.dao.UserRepository;
//import com.satvik.restful_web_services.exception.UserNotFoundException;
//import com.satvik.restful_web_services.user.User;
//
//import jakarta.validation.Valid;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//import java.util.List;
//
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//
//@RestController
//public class UserResource {
//
//    private UserRepository service;
//    public UserResource(UserRepository service) {
//        this.service = service;
//    }
//    @GetMapping("/users")
//    public List<User> retrieveAllUsers() {
//        return service.findAll();
//    }
//
//    @GetMapping("/users/{id}")
//    public EntityModel<User> retrieveSpecificUser(@PathVariable int id) {
//
//        User retrievedUser = service.findById(id);
//        if (retrievedUser == null) {
//            throw new UserNotFoundException("id: " + id);
//        }
//        EntityModel<User> model = EntityModel.of(retrievedUser);
//        WebMvcLinkBuilder link= WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
//        model.add(link.withRel("all-users"));
//        return model;
//    }
//    @PostMapping("/users")
//    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
//        User savedUser = service.save(user);
//        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
//                                                 .path("/{id}")
//                                                 .buildAndExpand(savedUser.getId())
//                                                 .toUri();
//        return ResponseEntity.created(location).body(savedUser) ;
//
//    }
//    @DeleteMapping("/users/{id}")
//    public void deleteUser(@PathVariable int id) {
//        service.deleteById(id);
//    }
//
//}
