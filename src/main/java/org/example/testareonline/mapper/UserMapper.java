package org.example.testareonline.mapper;

import org.example.testareonline.dto.request.UserRequest;
import org.example.testareonline.dto.response.UserDTO;
import org.example.testareonline.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teste", ignore = true)
    User toEntity(UserRequest request);

    UserDTO toDTO(User user);
}