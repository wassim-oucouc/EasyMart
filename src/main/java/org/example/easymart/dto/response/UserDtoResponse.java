package org.example.easymart.dto.response;

import lombok.Data;
import org.example.easymart.enumeration.Role;

@Data
public class UserDtoResponse {
    private long id;
    private String username;
    private String password;
    private Role role;

}
