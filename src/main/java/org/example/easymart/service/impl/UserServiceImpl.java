package org.example.easymart.service.impl;

import org.example.easymart.dto.response.UserDtoResponse;
import org.example.easymart.entity.User;
import org.example.easymart.exception.UserNotFoundException;
import org.example.easymart.mapper.UserMapper;
import org.example.easymart.repository.UserRepository;
import org.example.easymart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private  final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,UserMapper userMapper)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    public  User findUserById(Long id)
    {
        return  this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not exists! id : " + id));

    }
}
