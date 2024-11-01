package com.satvik.restful_web_services.repository;

import com.satvik.restful_web_services.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Integer> {
}
