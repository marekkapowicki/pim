package com.marekk.pim.product.domain.query

import com.marekk.pim.product.FakeProductProjection
import org.junit.Ignore
import spock.lang.Specification
import spock.lang.Subject

@Ignore
class ProductFinderFacadeSpec extends Specification {

    private final String id = "id"
    private final ProductFinderRepository repository = Mock(ProductFinderRepository) {
        findByProductId(id) >>  Optional.of(new FakeProductProjection())
    }

    @Subject
    private ProductFinderFacade facade = new ProductFinderFacade(repository)


    def "invokes the repository"() {
        when:
            facade.findById(id)
        then:
            1* repository.findByProductId(id)
    }
}
