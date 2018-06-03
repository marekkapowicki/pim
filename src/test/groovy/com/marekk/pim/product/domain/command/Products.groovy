package com.marekk.pim.product.domain.command

import com.marekk.pim.product.dto.ProductDTO

class Products {
    static ProductDTO SAMPLE_DTO = ProductDTO.builder()
            .externalId('externalId')
            .name('productName')
            .description('description')
            .minOrderQuantity(100)
            .unitOfMeasure(1)
            .externalCategoryId('zamroCategory123')
            .availableQuantity(50).build()

}
