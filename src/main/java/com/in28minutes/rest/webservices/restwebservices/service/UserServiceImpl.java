package com.in28minutes.rest.webservices.restwebservices.service;

import com.in28minutes.rest.webservices.restwebservices.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class UserServiceImpl implements IUserService{


    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Richard", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(30)));
    }
    @Override
    public List<User> findAll() {
        return users;
    }

    public User findById(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);

        return users.stream().filter(predicate).findFirst().orElse(null);


    }

    public User save(User user){
        user.setId(++usersCount);
        users.add(user);

        return user;
    }
}
