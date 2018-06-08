package com.marekk.pim.product.dto;
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

}
