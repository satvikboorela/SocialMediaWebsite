package com.satvik.restful_web_services.repository;

import com.satvik.restful_web_services.user.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostsRepository extends JpaRepository<Posts, Integer> {
}
