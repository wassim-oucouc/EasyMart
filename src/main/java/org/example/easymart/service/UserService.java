package org.example.easymart.service;

import org.example.easymart.dto.response.UserDtoResponse;
import org.example.easymart.entity.Users;

public interface UserService {

    public Users findUserById(Long id);
}
