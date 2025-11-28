package org.example.easymart.service;


import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.example.easymart.dto.request.UserDTO;
import org.example.easymart.dto.response.UserDtoResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public UserDtoResponse login(String email, String password);
    public String logout(HttpSession httpSession);
    public UserDtoResponse register(UserDTO userDTO);


}
