package com.iqbalfa.electronic.controller;

import com.iqbalfa.electronic.model.Category;
import com.iqbalfa.electronic.model.User;
import com.iqbalfa.electronic.model.request.CategoryIdRequest;
import com.iqbalfa.electronic.model.request.CategoryRequest;
import com.iqbalfa.electronic.model.request.UserRequest;
import com.iqbalfa.electronic.model.response.SuccessResponse;
import com.iqbalfa.electronic.service.interfaces.ICategoryService;
import com.iqbalfa.electronic.service.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private IUserService userService;
    private ModelMapper modelMapper;

    public UserController(IUserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsers() throws Exception {
        List<User> result = userService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all data", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable String id) throws Exception {
        Long newId = Long.valueOf(id);
        User result = userService.getById(newId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get data", result));
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserRequest userRequest) throws Exception {
        User newUser = modelMapper.map(userRequest, User.class);
        User result = userService.create(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create category", result));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) throws Exception {
        Long newId = Long.valueOf(id);
        userService.delete(newId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete category", id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) throws Exception {
        User newUser = modelMapper.map(userRequest, User.class);
        Long newId = Long.valueOf(id);
        User result = userService.update(newId, newUser);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update category", result));
    }

}
