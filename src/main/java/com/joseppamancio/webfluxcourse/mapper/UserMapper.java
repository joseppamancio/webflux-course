package com.joseppamancio.webfluxcourse.mapper;

import com.joseppamancio.webfluxcourse.entity.User;
import com.joseppamancio.webfluxcourse.model.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE, //ignora valores nulos
        nullValueCheckStrategy = ALWAYS // sempre checas valores nulos
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(final UserRequest request);
}
