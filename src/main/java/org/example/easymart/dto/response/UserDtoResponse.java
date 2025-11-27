package org.example.easymart.dto.response;

import lombok.Data;

@Data
public class UserDtoResponse {
    private long id;
    private String username;
    private String password;
}
