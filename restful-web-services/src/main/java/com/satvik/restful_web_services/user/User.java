package com.satvik.restful_web_services.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


import java.time.LocalDate;
import java.util.List;

@Entity(name= "user_details")
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Size(min = 2, message = "Name should have atleast 2 characters")
    @NotNull(message= "Name should not be null")
    private String name;
    @Past(message= "birthdate should be in the past" )
    private LocalDate birthday;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Posts> posts;

    public User(int id, String name, LocalDate birthday, List<Posts> posts) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.posts = posts;
    }

    public User() {

    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", posts=" + posts +
                '}';
    }
}
