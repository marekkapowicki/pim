package com.marekk.pim.product

import com.marekk.pim.product.CreateProductRequest
import com.marekk.pim.product.dto.ProductDTO
import groovy.json.JsonBuilder

class Requests {

    static CreateProductRequest SAMPLE = new CreateProductRequest('externalId', 'someName', 'description', 3, 6, 'externalCategory', 100.5G as BigDecimal, 100)
    static String toJson(CreateProductRequest request) {
        ProductDTO dto = request.toDto()
        return new JsonBuilder(['externalId': dto.externalId, 'name': dto.name, 'description': dto.description,
                                'minOrderQuantity' : dto.minOrderQuantity, 'unitOfMeasure': dto.unitOfMeasure,
                                'externalCategoryId': dto.externalCategoryId, 'purchasePrice' :dto.purchasePrice,
                                'availableQuantity': dto.availableQuantity]).toPrettyString()
    }
}
