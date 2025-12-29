package org.example.easymart.mapper;

import org.example.easymart.dto.request.UserDTO;
import org.example.easymart.dto.response.UserDtoResponse;
import org.example.easymart.entity.Users;
import org.mapstruct.Mapper;

@Mapper(   componentModel = "spring")
public interface UserMapper {

    public Users toEntity(UserDTO userDTO);
    public UserDtoResponse toDtoResponse(Users user);
}
