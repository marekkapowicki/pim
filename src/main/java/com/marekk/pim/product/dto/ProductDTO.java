package com.marekk.pim.product.dto;

import com.marekk.pim.infrastructure.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@AllArgsConstructor
@Builder
public class ProductDTO implements BaseDTO {
    long id;
    String externalId;
    String name;
    String description;
    int minOrderQuantity;
    int unitOfMeasure;
    String externalCategoryId;
    BigDecimal availableQuantity;

}