package com.marekk.pim.category;

import com.marekk.pim.category.dto.CategoryDTO;
import com.marekk.pim.infrastructure.api.CreateResourceRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(makeFinal = true, level = PRIVATE)
@AllArgsConstructor
@ToString
class CreateCategoryRequest implements CreateResourceRequest {
    String externalId;
    @NonNull
    String name;

    CategoryDTO toDto() {
        return CategoryDTO.of(null, externalId, name);
    }

}
