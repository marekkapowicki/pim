package com.marekk.pim.product;

import com.marekk.pim.product.dto.ProductProjection;

import java.math.BigDecimal;

public class FakeProductProjection implements ProductProjection {
    @Override
    public String getProductId() {
        return "productId";
    }

    @Override
    public String getName() {
        return "name";
    }

    @Override
    public String getDescription() {
        return "description";
    }

    @Override
    public Integer getMinOrderQuantity() {
        return 10;
    }

    @Override
    public Integer getUnitOfMeasure() {
        return 5;
    }

    @Override
    public String getCategoryName() {
        return "categoryName";
    }

    @Override
    public BigDecimal getPurchasePrice() {
        return BigDecimal.TEN;
    }

    @Override
    public Integer getAvailableQuantity() {
        return 500;
    }
}
