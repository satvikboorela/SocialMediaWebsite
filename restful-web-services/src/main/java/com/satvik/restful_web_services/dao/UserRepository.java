//package com.satvik.restful_web_services.dao;
//
//import com.satvik.restful_web_services.user.User;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserRepository {
//
//    private static List<User> users = new ArrayList<>();
//    private static int userCounter = 0;
//
//    static {
//        users.add(new User(++userCounter, "Aman", LocalDate.now().minusYears(31)));
//        users.add(new User(++userCounter, "Rajat", LocalDate.now().minusYears(29)));
//        users.add(new User(++userCounter, "Satvik", LocalDate.now().minusYears(25)));
//    }
//    public List<User> findAll() {
//        return users;
//    }
//    public User findById(int id) {
//        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
//    }
//    public User save(User user) {
//        user.setId(++userCounter);
//        users.add(user);
//        return user;
//    }
//    public void deleteById(int id) {
//        users.removeIf(user -> user.getId()== id);
//    }
//}
