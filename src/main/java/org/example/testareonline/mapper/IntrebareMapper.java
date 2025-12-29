package org.example.testareonline.mapper;

import org.example.testareonline.dto.request.CreateIntrebareRequest;
import org.example.testareonline.dto.request.IntrebareRequest;
import org.example.testareonline.dto.response.IntrebareDTO;
import org.example.testareonline.entity.Intrebare;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {OptiuneMapper.class})
public interface IntrebareMapper {

    @Mapping(target = "test", ignore = true)
    @Mapping(target = "optiuni", ignore = true)
    @Mapping(target = "id", ignore = true)
    Intrebare toEntity(IntrebareRequest request);

    // For full test creation (nested)
    @Mapping(target = "test", ignore = true)
    @Mapping(target = "optiuni", ignore = true)
    @Mapping(target = "id", ignore = true)
    Intrebare toEntity(CreateIntrebareRequest request);

    @Mapping(target = "idTest", source = "test.id")
    IntrebareDTO toDTO(Intrebare entity);
}