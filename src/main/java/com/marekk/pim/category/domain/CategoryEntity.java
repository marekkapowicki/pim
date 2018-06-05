package com.marekk.pim.category.domain;


import com.marekk.pim.infrastructure.BaseEntity;
import com.marekk.pim.category.dto.CategoryDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "category")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
class CategoryEntity extends BaseEntity {
    String externalId;
    String name;

    CategoryDTO toDTO() {
        return CategoryDTO.of(getUuid(), externalId, name);
    }

    public CategoryEntity merge(CategoryDTO dto) {
        externalId = dto.getExternalId();
        name = dto.getName();
        return this;
    }
}
