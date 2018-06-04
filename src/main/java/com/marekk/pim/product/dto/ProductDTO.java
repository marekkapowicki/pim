package com.marekk.pim.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marekk.pim.infrastructure.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductDTO implements BaseDTO {
    long id;
    String externalId;
    String name;
    String description;
    int minOrderQuantity;
    int unitOfMeasure;
    String externalCategoryId;
    BigDecimal purchasePrice;
    int availableQuantity;

}
