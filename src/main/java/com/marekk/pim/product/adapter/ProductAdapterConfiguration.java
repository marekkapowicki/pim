package com.marekk.pim.product.adapter;

import com.marekk.pim.product.domain.command.ProductFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductAdapterConfiguration {

    @Bean
    ProductCsvImporter productCsvImporter(ProductFacade facade) {
        return new ProductCsvImporter(facade);
    }
}
