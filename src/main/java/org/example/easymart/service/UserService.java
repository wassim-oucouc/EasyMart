package org.example.easymart.service;

import org.example.easymart.dto.response.UserDtoResponse;
import org.example.easymart.entity.User;

public interface UserService {

    public  User findUserById(Long id);
}
