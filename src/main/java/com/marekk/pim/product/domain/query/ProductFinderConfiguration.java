package com.marekk.pim.product.domain.query;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

@Configuration
@EnableJpaRepositories("com.marekk.pim.product.domain.query")
class ProductFinderConfiguration {

    @Bean
    ProductFinderFacade productFinderFacade(ProductFinderRepository repository, ProjectionFactory factory) {
        return new ProductFinderFacade(repository, factory);
    }

    @Bean
    ProjectionFactory projectionFactory() {
        return new SpelAwareProxyProjectionFactory();
    }
}
