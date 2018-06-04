package com.marekk.pim.product

import com.marekk.pim.infrastructure.exception.Exceptions
import com.marekk.pim.product.domain.command.ProductFacade
import com.marekk.pim.product.domain.command.Products
import com.marekk.pim.product.domain.query.ProductFinderFacade
import org.apache.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired

import static com.jayway.restassured.RestAssured.given
import static com.marekk.pim.infrastructure.api.Specification.API_CONTENT_TYPE
import static com.marekk.pim.infrastructure.api.Specification.ROOT
import static com.marekk.pim.product.Requests.SAMPLE
import static com.marekk.pim.product.Requests.toJson
import static org.hamcrest.Matchers.notNullValue
import static org.hamcrest.Matchers.notNullValue
import static org.hamcrest.Matchers.notNullValue
import static org.hamcrest.core.IsEqual.equalTo

class ProductControllerSpec extends BaseSpringBootITSpec {
    @Autowired
    private ProductFacade productFacade

    @Autowired
    private ProductFinderFacade productFinderFacade

    def 'should return id during product creation'() {
        given:
            String createdId = "createdId"
            productFacade.create(SAMPLE.toDto()) >> createdId
        expect:
            given()
                .contentType(API_CONTENT_TYPE)
                .body(toJson(SAMPLE))
            .when()
                .post(ROOT + "/products")
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", equalTo(createdId))
    }

    def 'should return 200 during updating existing product'() {
        given:
            String existingId = "existingId"
            productFacade.update(existingId,SAMPLE.toDto())
        expect:
            given()
                .pathParam("productId", existingId)
                .contentType(API_CONTENT_TYPE)
                .body(toJson(SAMPLE))
            .when()
                .put(ROOT + "/products/{productId}")
            .then()
                .statusCode(HttpStatus.SC_OK)
    }

    def 'should return 404 during updating not existing product'() {
        given:
            productFacade.update(_,SAMPLE.toDto()) >> {throw Exceptions.notFound().get()}

        expect:
            given()
                .pathParam("productId", 'wrongId')
                .contentType(API_CONTENT_TYPE)
                .body(toJson(SAMPLE))
            .when()
                .put(ROOT + "/products/{productId}")
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
    }

    def 'should return 200 during deleting existing product'() {
        given:
            String existingId = "existingId"
            productFacade.delete(existingId)
        expect:
            given()
                .pathParam("productId", existingId)
            .when()
                .delete(ROOT + "/products/{productId}")
            .then()
                .statusCode(HttpStatus.SC_OK)
    }

    def 'should return product by id'() {
        given:
            String existingId = "existingId"
            productFinderFacade.findById(existingId) >> new FakeProductProjection()
        expect:
            given()
                .pathParam("productId", existingId)
            .when()
                .get(ROOT + "/products/{productId}")
            .then()
                .statusCode(HttpStatus.SC_OK)
                    .contentType(API_CONTENT_TYPE)
                    .body("productId", notNullValue()).and()
                    .body("name", notNullValue()).and()
                    .body("description", notNullValue())
                    .body("minOrderQuantity", notNullValue())
                    .body("unitOfMeasure", notNullValue())
                    .body("categoryName", notNullValue())
                    .body("purchasePrice", notNullValue())
                    .body("availableQuantity", notNullValue())
    }

}
