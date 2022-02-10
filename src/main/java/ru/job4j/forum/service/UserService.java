package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public UserService(UserRepository users, AuthorityRepository authorities) {
        this.users = users;
        this.authorities = authorities;
    }

    public User addUser(User user) {
        users.save(user);
        return user;
    }

    public User findUserById(int id) {
        return users.findById(id).orElse(null);
    }

    public User findUserByName(String name) {
        return users.findByName(name);
    }

    public Authority findAuthorityByName(String name) {
        return authorities.findAuthorityByName(name);
    }

    public List<User> findAllUsers() {
        return (List<User>) users.findAll();
    }
}
