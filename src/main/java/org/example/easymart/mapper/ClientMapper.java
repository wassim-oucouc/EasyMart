package org.example.easymart.mapper;

import org.example.easymart.dto.request.ClientDTO;
import org.example.easymart.dto.response.ClientDtoResponse;
import org.example.easymart.entity.Client;
import org.example.easymart.entity.Users;
import org.example.easymart.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class}
)
public abstract class ClientMapper {

    @Autowired
     public UserService userService;



    @Mapping(target = "user", source = "userId")
   public abstract Client toEntity(ClientDTO clientDTO);

    @Mapping(target = "userDtoResponse", source = "user")
   public abstract ClientDtoResponse toDtoResponse(Client client);

    protected Users map(Long userId) {
        if (userId == null) return null;
      return this.userService.findUserById(userId);
    }
}
