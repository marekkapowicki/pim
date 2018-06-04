package com.marekk.pim.product.dto;

import java.math.BigDecimal;

public interface ProductProjection {
    String getProductId();
    String getName();
    String getDescription();
    int getMinOrderQuantity();
    int getUnitOfMeasure();
    String getCategoryName();
    BigDecimal getPurchasePrice();
    int getAvailableQuantity();

}
