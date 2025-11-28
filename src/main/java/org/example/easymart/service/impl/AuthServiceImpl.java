package org.example.easymart.service.impl;

import jakarta.servlet.http.HttpSession;
import org.example.easymart.dto.request.UserDTO;
import org.example.easymart.dto.response.UserDtoResponse;
import org.example.easymart.entity.Users;
import org.example.easymart.exception.InvalidCredentialsException;
import org.example.easymart.exception.UserNotFoundException;
import org.example.easymart.mapper.UserMapper;
import org.example.easymart.repository.UserRepository;
import org.example.easymart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserMapper userMapper, PasswordEncoder passwordEncoder, UserRepository userRepository)
    {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public UserDtoResponse login(String username, String password)
    {
        Users users = this.userRepository.findUsersByUsername(username).orElseThrow(() -> new UserNotFoundException("User Not Exists"));

        Boolean passwordDecrypt = this.passwordEncoder.matches(password,users.getPassword());

        if(users.getUsername().equals(username) && passwordDecrypt.equals(true))
        {
            return this.userMapper.toDtoResponse(users);
        }
        else
        {
         throw new InvalidCredentialsException("Credentials Invalide");
        }

    }
    public String logout(HttpSession httpSession)
    {
        httpSession.invalidate();
        return "logout success";
    }

    public UserDtoResponse register(UserDTO userDTO)
    {
        String cryptPassword = this.passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(cryptPassword);
        Users users =  this.userRepository.save(this.userMapper.toEntity(userDTO));
        return this.userMapper.toDtoResponse(users);
    }
}
