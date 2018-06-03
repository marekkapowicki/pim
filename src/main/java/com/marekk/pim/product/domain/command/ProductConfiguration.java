package com.marekk.pim.product.domain.command;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.marekk.pim.product.domain")
class ProductConfiguration {

    @Bean
    ProductFacade productFacade(ProductRepository productRepository) {
        return new ProductFacade(productRepository, new ProductFactory());
    }
}
