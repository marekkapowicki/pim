package com.marekk.pim.product;

import com.marekk.pim.infrastructure.api.IdResponse;
import com.marekk.pim.infrastructure.api.UploadResult;
import com.marekk.pim.infrastructure.transform.SourceFile;
import com.marekk.pim.product.adapter.ProductCsvImporter;
import com.marekk.pim.product.domain.command.ProductFacade;
import com.marekk.pim.product.domain.query.ProductFinderFacade;
import com.marekk.pim.product.dto.ProductProjection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.marekk.pim.infrastructure.api.Specification.API_CONTENT_TYPE;
import static com.marekk.pim.infrastructure.api.Specification.FORM_DATA;
import static com.marekk.pim.infrastructure.api.Specification.ROOT;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@FieldDefaults(makeFinal = true, level = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@RequestMapping(value = ROOT + "/products")
@Api(value = "Products")
public class ProductController {

    ProductFacade productFacade;
    ProductFinderFacade productFinderFacade;
    ProductCsvImporter productCsvImporter;

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

    @PutMapping(value = "/{productId}", consumes = API_CONTENT_TYPE, produces = API_CONTENT_TYPE)
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

    @GetMapping(value = "/{id}", produces = API_CONTENT_TYPE)
    @ApiOperation(value = "Lists product by id", produces = API_CONTENT_TYPE)
    public ProductProjection retrieveById(@PathVariable("id") String id) {
        log.info("retrieve product by id={}", id);
        return productFinderFacade.findById(id);
    }

    @GetMapping(produces = {API_CONTENT_TYPE, "text/csv"}, consumes = {API_CONTENT_TYPE, "text/csv"})
    @ApiOperation(value = "Lists all products by example", produces = API_CONTENT_TYPE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "externalId", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "categoryName", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. "
                            + "Multiple sort criteria are supported.")
    })
    public Page<ProductProjection> retrieveByExample(ProductProjection example, Pageable pageable) {
        log.info("retrieve products by example: {}", example);
        return productFinderFacade.findByExample(example, pageable);


    }

    @ApiOperation(value = "upload csv file", consumes = FORM_DATA, produces = APPLICATION_JSON_VALUE)
    @PostMapping(value = "/actions/import", consumes = FORM_DATA, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = IdResponse.class),
            @ApiResponse(code = 415, message = "only csv is supported")
    })
    @ResponseBody
    public UploadResult uploadCsv(@RequestParam("uploadFile") MultipartFile uploadFile) {
        return productCsvImporter.importFile(SourceFile.csv(uploadFile));
    }
}
