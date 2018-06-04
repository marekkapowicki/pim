package com.marekk.pim.product;

import com.marekk.pim.infrastructure.api.IdResponse;
import com.marekk.pim.product.domain.command.ProductFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.marekk.pim.infrastructure.api.Specification.API_CONTENT_TYPE;
import static com.marekk.pim.infrastructure.api.Specification.ROOT;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@FieldDefaults(makeFinal = true, level = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@RequestMapping(value = ROOT + "/products")
@Api(value = "Products")
public class ProductController {

    ProductFacade productFacade;

    @PostMapping(consumes = API_CONTENT_TYPE, produces = API_CONTENT_TYPE)
    @ApiOperation(value = "create a new product", produces = API_CONTENT_TYPE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = IdResponse.class),
            @ApiResponse(code = 409, message = "Category externalId already exists")
    })
    public IdResponse create(@RequestBody @Valid @NotNull CreateProductRequest createRequest) {
        log.info("new request arrived {}", createRequest);
        return IdResponse.of(productFacade.create(createRequest.toDto()));
    }

    @PutMapping(value = "/{productId}",  consumes = API_CONTENT_TYPE, produces = API_CONTENT_TYPE)
    @ApiOperation(value = "update a product", produces = API_CONTENT_TYPE)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = IdResponse.class)
    })
    public ResponseEntity update(
            @PathVariable("productId") @NotNull String productId,
            @RequestBody @Valid @NotNull CreateProductRequest updateReuest) {
        log.info("new request arrived {}", updateReuest);
        productFacade.update(productId, updateReuest.toDto());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{productId}")
    @ApiOperation(value = "delete a product")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = IdResponse.class)
    })
    public ResponseEntity delete(@PathVariable("productId") @NotNull String productId) {
        productFacade.delete(productId);
        return ResponseEntity.ok().build();
    }
}
