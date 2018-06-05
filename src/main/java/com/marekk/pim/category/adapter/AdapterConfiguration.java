package com.marekk.pim.category.adapter;

import com.marekk.pim.category.domain.CategoryFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AdapterConfiguration {

    @Bean
    CsvImporter csvImporter(CategoryFacade facade) {
        return new CsvImporter(facade);
    }
}
