package org.example.testareonline.mapper;

import org.example.testareonline.dto.request.CreateTestRequest;
import org.example.testareonline.dto.request.TestRequest;
import org.example.testareonline.dto.response.TestDTO;
import org.example.testareonline.entity.Test;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TestMapper {

    @Mapping(target = "idUser", source = "user.id")
    @Mapping(target = "idDomeniu", source = "domeniu.id")
    TestDTO toDTO(Test test);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCrearii", ignore = true)
    @Mapping(target = "intrebari", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "domeniu", ignore = true)
    Test toEntity(TestRequest dto);

    // For full test creation with nested questions and options
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCrearii", ignore = true)
    @Mapping(target = "intrebari", ignore = true) // mapped manually in service
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "domeniu", ignore = true)
    Test toEntity(CreateTestRequest request);
}