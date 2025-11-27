package org.example.easymart.mapper;

import org.example.easymart.dto.request.UserDTO;
import org.example.easymart.dto.response.UserDtoResponse;
import org.example.easymart.entity.User;
import org.mapstruct.Mapper;

@Mapper(   componentModel = "spring")
public interface UserMapper {

    public User toEntity(UserDTO userDTO);
    public UserDtoResponse toDtoResponse(User user);
}
