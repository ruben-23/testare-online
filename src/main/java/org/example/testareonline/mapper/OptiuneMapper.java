package org.example.testareonline.mapper;

import org.example.testareonline.dto.request.OptiuneRequest;
import org.example.testareonline.dto.response.OptiuneDTO;
import org.example.testareonline.entity.Optiune;
import org.example.testareonline.repository.IntrebareRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = IntrebareRepository.class)
public interface OptiuneMapper {

    @Mapping(target = "intrebare", ignore = true)
    @Mapping(target = "id", ignore = true)
    Optiune toEntity(OptiuneRequest request);

    @Mapping(target = "idIntrebare", source = "intrebare.id")
    OptiuneDTO toDTO(Optiune entity);

}