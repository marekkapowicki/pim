package com.marekk.pim.category.adapter;

import com.marekk.pim.category.dto.CategoryDTO;
import com.opencsv.bean.CsvBindByName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@NoArgsConstructor
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRow {
    @CsvBindByName(column = "CategoryID")
    String externalId;
    @CsvBindByName(column = "Name")
    String name;

    CategoryDTO toDto() {
        return CategoryDTO.of(null, externalId, name);
    }
}
