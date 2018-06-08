package com.marekk.pim.product.domain.query;


import com.marekk.pim.infrastructure.exception.Exceptions;
import com.marekk.pim.product.dto.ProductProjection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Slf4j
public class ProductFinderFacade {
    ProductFinderRepository repository;
    ProjectionFactory projectionFactory;

    @Transactional(readOnly = true)
    public ProductProjection findById(String id) {
        log.info("finding product by id: {}", id);
        return repository.findByProductId(id)
                .orElseThrow(Exceptions.notFound());
    }

    @Transactional(readOnly = true)
    public Page<ProductProjection> findByExample(ProductProjection example, Pageable pageable) {
        log.info("finding the page = {} of products by example = {} ", pageable, example);
        return repository.findAll(Example.of(ProductProjectionEntity.from(example)), pageable)
                .map(it -> projectionFactory.createProjection(ProductProjection.class, it));
    }

    @Transactional(readOnly = true)
    public Optional<String> findByExternalId(String id) {
        log.info("find product by external id = {}", id);
        return repository.findByExternalId(id)
                .map(ProductProjection::getProductId);
    }
}
