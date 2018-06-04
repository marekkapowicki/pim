package com.marekk.pim.category.domain

import com.marekk.pim.category.dto.CategoryDTO

class Categories {
    static CategoryDTO SAMPLE_CATEGORY_WITH_EXT_ID = new CategoryDTO(null, "someId", "backpack")
    static CategoryDTO SAMPLE_CATEGORY_WITHOUT_EXT_ID = new CategoryDTO(null, null, "backpack")
}
