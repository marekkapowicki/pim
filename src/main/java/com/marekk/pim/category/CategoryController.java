package com.marekk.pim.category;

import com.marekk.pim.category.domain.CategoryFacade;
import com.marekk.pim.category.dto.CategoryDTO;
import com.marekk.pim.infrastructure.api.IdResponse;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = ROOT + "/categories")
@Api(value = "Categories")
public class CategoryController {

    CategoryFacade categoryFacade;

    @PostMapping(consumes = API_CONTENT_TYPE, produces = API_CONTENT_TYPE)
    @ApiOperation(value = "register a new category", produces = API_CONTENT_TYPE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = IdResponse.class),
            @ApiResponse(code = 409, message = "Category externalId already exists")
    })
    public IdResponse create(@RequestBody @Valid @NotNull CreateCategoryRequest createRequest) {
        log.info("new request arrived {}", createRequest);
        return IdResponse.of(categoryFacade.create(createRequest.toDto()).getId());
    }

    @GetMapping(produces = API_CONTENT_TYPE)
    @ApiOperation(value = "Lists all categories", produces = API_CONTENT_TYPE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. "
                            + "Multiple sort criteria are supported.")
    })
    public Page<CategoryDTO> retrieveAll(@NotNull Pageable pageable) {
        log.info("retrieving all customers");
        return categoryFacade.retrieveAll(pageable);
    }

}
