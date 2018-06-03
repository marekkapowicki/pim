package com.marekk.pim.category.dto;

import com.marekk.pim.infrastructure.BaseDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor(staticName = "of")
@ToString
@Getter
public class CategoryDTO implements BaseDTO {
    String id;
    String externalId;
    String name;
}
