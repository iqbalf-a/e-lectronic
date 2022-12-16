package com.iqbalfa.electronic.service.interfaces;

import com.iqbalfa.electronic.model.Category;
import com.iqbalfa.electronic.model.User;

import java.util.List;

public interface IUserService {
    User create(User user) throws Exception;

    List<User> list() throws Exception;

    User getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    User update(Long id, User user) throws Exception;

}
