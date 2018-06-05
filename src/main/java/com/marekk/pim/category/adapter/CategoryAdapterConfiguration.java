package com.marekk.pim.category.adapter;

import com.marekk.pim.category.domain.CategoryFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CategoryAdapterConfiguration {

    @Bean
    CategoryCsvImporter categoryCsvImporter(CategoryFacade facade) {
        return new CategoryCsvImporter(facade);
    }
}
