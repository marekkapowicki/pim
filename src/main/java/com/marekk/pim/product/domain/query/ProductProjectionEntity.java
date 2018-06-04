package com.marekk.pim.product.domain.query;

import com.marekk.pim.product.dto.ProductProjection;
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

    static ProductProjectionEntity from(ProductProjection projection) {
        return new ProductProjectionEntity(projection.getProductId(), projection.getName(), projection.getDescription(),
                projection.getMinOrderQuantity(), projection.getUnitOfMeasure(), projection.getCategoryName(), projection.getPurchasePrice(), projection.getAvailableQuantity());
    }
    @Id
    String productId;
    String name;
    String description;
    Integer minOrderQuantity;
    Integer unitOfMeasure;
    String categoryName;
    BigDecimal purchasePrice;
    Integer availableQuantity;
}
