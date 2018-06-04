package com.marekk.pim.product.domain.query;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.marekk.pim.product.domain.query")
class ProductFinderConfiguration {

    @Bean
    ProductFinderFacade productFinderFacade(ProductFinderRepository repository) {
        return new ProductFinderFacade(repository);
    }
}
