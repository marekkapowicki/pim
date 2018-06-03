package com.marekk.pim.product.domain.command

import com.marekk.pim.infrastructure.exception.Exceptions
import com.marekk.pim.product.dto.ProductDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Subject

import static com.marekk.pim.infrastructure.exception.Exceptions.notFound

@DataJpaTest
@ContextConfiguration(classes = ProductConfiguration)
class ProductFacadeITSpec extends Specification {

    private static final ProductDTO SAVED_PRODUCT = Products.SAMPLE_DTO
    private static String PRODUCT_ID
    @Autowired
    @Subject
    ProductFacade productFacade

    @Autowired
    ProductRepository repository

    void setup() {
        PRODUCT_ID = productFacade.create(SAVED_PRODUCT).id
    }

    def 'update existing product'() {
        given:
            ProductDTO productWithNewName = ProductDTO.builder().name('new name').build()
        when:
            productFacade.update(PRODUCT_ID, productWithNewName);
        then:
            productFacade.findNameById(PRODUCT_ID) == 'new name'
    }

    def 'throw exception during updating not existing product'() {
        given:
            ProductDTO productWithNewName = ProductDTO.builder().name('new name').build()
        when:
            productFacade.update('not existing', productWithNewName);
        then:
            RuntimeException ex = thrown()
            ex.class == notFound().get().class
    }

    def 'delete existing product'() {

        given:
            productFacade.delete(PRODUCT_ID);
        when:
            productFacade.findNameById(PRODUCT_ID)
        then:
            RuntimeException ex = thrown()
            ex.class == notFound().get().class

    }

}
