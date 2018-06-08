package com.marekk.pim.product.dto;
import com.google.common.base.MoreObjects;

import java.math.BigDecimal;

public interface ProductProjection {
    String getProductId();

    String getName();

    String getDescription();

    Integer getMinOrderQuantity();

    Integer getUnitOfMeasure();

    String getCategoryName();

    BigDecimal getPurchasePrice();

    Integer getAvailableQuantity();

    String getExternalId();

    default String print() {

        return MoreObjects.toStringHelper(ProductProjection.class)
                .add("productId", getProductId())
                .add("name", getName())
                .add("description", getDescription())
                .add("minOrderQuantity", getMinOrderQuantity())
                .add("unitOfMeasure", getUnitOfMeasure())
                .add("categoryName", getCategoryName())
                .add("purchasePrice", getPurchasePrice())
                .add("availableQuantity", getAvailableQuantity())
                .add("externalId", getExternalId())
                .toString();

    }

}
