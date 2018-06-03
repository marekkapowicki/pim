package com.marekk.pim.category.domain

import com.marekk.pim.category.dto.CategoryDTO
import spock.lang.Specification

class CategoryEntitySpec extends Specification {
    def "convert to dto"() {
        given:
            CategoryEntity category = new CategoryEntity('externalId', 'someName')
        when:
            CategoryDTO dto = category.toDTO()
        then:
            with(dto) {
                name == 'someName'
                externalId == 'externalId'
                id == category.getUuid()
            }
    }
}
