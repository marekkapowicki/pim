package com.marekk.pim.product.domain.query

import com.marekk.pim.product.dto.ProductProjection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Example
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
            List<ProductProjection> result = repository.findAll(of(sample) as Example)
        then:
            result
    }
}
