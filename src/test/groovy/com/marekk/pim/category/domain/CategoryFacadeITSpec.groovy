package com.marekk.pim.category.domain

import com.marekk.pim.category.dto.CategoryDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Subject

import static com.marekk.pim.category.domain.Categories.SAMPLE_CATEGORY_WITH_EXT_ID

@DataJpaTest
@ContextConfiguration(classes = CategoryConfiguration)
class CategoryFacadeITSpec extends Specification {
    private static final CategoryDTO SAVED_CATEGORY = SAMPLE_CATEGORY_WITH_EXT_ID
    private static String CATEGORY_ID
    @Autowired
    @Subject
    CategoryFacade categoryFacade

    void setup() {
        CATEGORY_ID = categoryFacade.create(SAVED_CATEGORY)
    }

    def "should find all categories"() {
        when:
            Page page = categoryFacade.getAll(new PageRequest(0, 5))
        then:
            with(page) {
                totalElements == 1
                content.get(0).name == SAVED_CATEGORY.name
            }
    }
}
