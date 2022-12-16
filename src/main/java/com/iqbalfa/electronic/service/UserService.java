package com.iqbalfa.electronic.service;

import com.iqbalfa.electronic.exception.EntityExistException;
import com.iqbalfa.electronic.exception.NotFoundException;
import com.iqbalfa.electronic.model.User;
import com.iqbalfa.electronic.repository.IUserRepository;
import com.iqbalfa.electronic.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        try {
            User newUser = userRepository.save(user);
            return newUser;
        } catch (Exception e) {
            throw new EntityExistException("User " + user.getName() + " already exist");
        }
    }

    @Override
    public List<User> list() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User getById(Long id) throws Exception {
        Optional<User> existingUser = userRepository.findByUserById(id);
        if (existingUser.isEmpty()) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        return existingUser.get();
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<User> existingUser = userRepository.findByUserById(id);
        if (existingUser.isEmpty()) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteUserById(id);
    }

    @Override
    public User update(Long id, User user) throws Exception {
        Optional<User> existingUser = userRepository.findByUserById(id);
        if (existingUser.isEmpty()) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        user.setUserId(id);
        User newUser = userRepository.save(user);
        return newUser;
    }

}
