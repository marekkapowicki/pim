package com.marekk.pim.product.domain.command

import spock.lang.Specification
import spock.lang.Subject

import static com.marekk.pim.product.domain.command.Products.SAMPLE_DTO

class ProductFactorySpec extends Specification {

    @Subject
    private final ProductFactory factory = new ProductFactory()

    def "build the product entity"() {
        when:
            ProductEntity product = factory.create(SAMPLE_DTO)
        then:
            with(product) {
                name == SAMPLE_DTO.name
                description == SAMPLE_DTO.description
                minOrderQuantity == SAMPLE_DTO.minOrderQuantity
                unitOfMeasure == SAMPLE_DTO.unitOfMeasure
                externalCategoryId == SAMPLE_DTO.externalCategoryId
                purchasePrice == SAMPLE_DTO.purchasePrice
                availableQuantity == SAMPLE_DTO.availableQuantity
            }
    }
}
