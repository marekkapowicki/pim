package com.marekk.pim.category.dto;

import com.marekk.pim.infrastructure.BaseDTO;
import com.opencsv.bean.CsvBindByName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@ToString
@Getter
@NoArgsConstructor
public class CategoryDTO implements BaseDTO {
    String id;
    @CsvBindByName(column = "CategoryID")
    String externalId;
    @CsvBindByName(column = "Name")
    String name;
}
