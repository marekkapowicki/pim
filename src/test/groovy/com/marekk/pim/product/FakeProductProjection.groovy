package com.marekk.pim.product

import com.marekk.pim.product.dto.ProductProjection

class FakeProductProjection implements ProductProjection {
    @Override
    String getProductId() {
        return "productId"
    }

    @Override
    String getName() {
        return "name"
    }

    @Override
    String getDescription() {
        return "description"
    }

    @Override
    Integer getMinOrderQuantity() {
        return 10
    }

    @Override
    Integer getUnitOfMeasure() {
        return 5
    }

    @Override
    String getCategoryName() {
        return "categoryName"
    }

    @Override
    BigDecimal getPurchasePrice() {
        return BigDecimal.TEN
    }

    @Override
    Integer getAvailableQuantity() {
        return 500
    }
}
