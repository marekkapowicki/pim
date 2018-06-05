package com.marekk.pim.category.adapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AdapterConfiguration {

    @Bean
    CsvImporter csvImporter() {
        return new CsvImporter();
    }
}
