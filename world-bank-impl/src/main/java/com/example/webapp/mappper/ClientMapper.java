package com.example.webapp.mappper;

import com.example.webapp.domain.Client;
import com.example.webapp.openapi.model.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "birthday", dateFormat = "yyyy-MM-dd")//TODO not work in swagger (make something with openapi)
    @Mapping(target = "phone", source = "mobilePhone")
    ClientDto toDto(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "birthday", dateFormat = "yyyy-MM-dd")//TODO not work in swagger (make something with openapi)
    @Mapping(target = "mobilePhone", source = "phone")
    Client toModel(ClientDto client);
}
