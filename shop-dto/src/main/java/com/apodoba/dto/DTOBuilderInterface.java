package com.apodoba.dto;

public interface DTOBuilderInterface<ENTITY, DTO> {

    public DTO buildDTO(ENTITY entity);
    public ENTITY buildEntity(DTO dto);
}
