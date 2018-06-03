package com.marekk.pim.category.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.marekk.pim.category.domain")
class CategoryConfiguration {

    @Bean
    CategoryFacade customerFacade(CategoryRepository customerRepository) {
        return new CategoryFacade(customerRepository);
    }

}
