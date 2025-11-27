package org.example.easymart.dto.request;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String username;
    private String password;
}
