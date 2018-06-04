package com.marekk.pim.product.domain.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "product_view")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = PRIVATE)
class ProductProjectionEntity {

    @Id
    String productId;
    String name;
    String description;
    int minOrderQuantity;
    int unitOfMeasure;
    String categoryName;
    BigDecimal purchasePrice;
    int availableQuantity;
}
