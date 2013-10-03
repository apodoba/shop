package com.apodoba.dto;

import com.apodoba.domain.CategoryEntity;

public class CategoryDTOBuilder implements DTOBuilderInterface<CategoryEntity, CategoryDTO>{

    public CategoryDTO buildDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setParentCategoryId(entity.getParentCategoryId());
        return dto;
    }

    public CategoryEntity buildEntity(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setParentCategoryId(dto.getParentCategoryId());
        return entity;
    }

}
