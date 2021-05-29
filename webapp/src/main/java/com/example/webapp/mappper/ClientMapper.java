package com.example.webapp.mappper;

import com.example.webapp.domain.Client;
import com.example.webapp.openapi.model.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "email", target = "email")
    ClientDto toDto(Client client);
}
