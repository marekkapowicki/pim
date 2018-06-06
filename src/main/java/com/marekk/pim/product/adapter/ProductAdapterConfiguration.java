package com.marekk.pim.product.adapter;

import com.marekk.pim.product.domain.command.ProductFacade;
import com.marekk.pim.product.domain.query.ProductFinderFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductAdapterConfiguration {

    @Bean
    ProductCsvImporter productCsvImporter(ProductFacade productFacade, ProductFinderFacade productFinderFacade) {
        return new ProductCsvImporter(productFacade, productFinderFacade);
    }
}
