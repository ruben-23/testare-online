package org.example.testareonline.mapper;

import org.example.testareonline.dto.request.DomeniuRequest;
import org.example.testareonline.dto.response.DomeniuDTO;
import org.example.testareonline.entity.Domeniu;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DomeniuMapper {

    @Mapping(target = "teste", ignore = true)
    @Mapping(target = "id", ignore = true)
    Domeniu toEntity(DomeniuRequest request);

    DomeniuDTO toDTO(Domeniu entity);
}