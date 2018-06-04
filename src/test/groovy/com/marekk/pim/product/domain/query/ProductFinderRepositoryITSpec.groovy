package com.marekk.pim.product.domain.query

import com.marekk.pim.product.dto.ProductProjection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification

import static org.springframework.data.domain.Example.of

@DataJpaTest
@ContextConfiguration(classes = ProductFinderConfiguration)
@Sql(scripts = ['/insert_categories.sql', '/insert_products.sql'], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProductFinderRepositoryITSpec extends Specification {

    @Autowired
    private ProductFinderRepository repository

    def "find product by id"() {
        when:
            Optional<ProductProjection> found = repository.findByProductId('product1')
        then:
            found.isPresent()
        and:
            with(found.get()) {
                name == 'iphone'
                categoryName == 'phones'
            }
    }

    def "find by example"() {
        given:
            ProductProjectionEntity sample = new ProductProjectionEntity('name': 'iphone')

        when:
            Page<ProductProjectionEntity> result = repository.findAll(of(sample), new PageRequest(0, 5))
        then:
            result.content.size() == 1
    }

    def "find 0 products by example"() {
        given:
            ProductProjectionEntity sample = new ProductProjectionEntity('name': 'iphone_666')

        when:
            Page<ProductProjectionEntity> result = repository.findAll(of(sample), new PageRequest(0, 5))
        then:
            result.content.size() == 0
    }
}
