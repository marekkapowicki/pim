package com.marekk.pim.product

import com.jayway.restassured.RestAssured
import com.marekk.pim.category.adapter.CategoryCsvImporter
import com.marekk.pim.category.domain.CategoryFacade
import com.marekk.pim.product.adapter.ProductCsvImporter
import com.marekk.pim.product.domain.command.ProductFacade
import com.marekk.pim.product.domain.query.ProductFinderFacade
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(classes = Config, webEnvironment = RANDOM_PORT)
abstract class BaseSpringBootITSpec extends Specification {

    @Value('${local.server.port}')
    int serverPort

    void setup() {
        RestAssured.port = serverPort
    }

    @TestConfiguration
    static class Config {
        private DetachedMockFactory factory = new DetachedMockFactory()

        @Bean
        CategoryFacade categoryFacade() {
            return factory.Stub(CategoryFacade)
        }

        @Bean
        ProductFacade productFacade() {
            return factory.Stub(ProductFacade)
        }
        @Bean
        ProductFinderFacade productFinderFacade() {
            return factory.Stub(ProductFinderFacade)
        }
        @Bean
        CategoryCsvImporter categoryCsvImporter() {
            return factory.Stub(CategoryCsvImporter)
        }
        @Bean
        ProductCsvImporter productCsvImporter() {
            return factory.Stub(ProductCsvImporter)
        }

    }
}
