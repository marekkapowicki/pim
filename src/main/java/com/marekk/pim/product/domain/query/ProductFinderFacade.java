package com.marekk.pim.product.domain.query;

import com.marekk.pim.infrastructure.exception.Exceptions;
import com.marekk.pim.product.dto.ProductProjection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Slf4j
public class ProductFinderFacade {
    ProductFinderRepository repository;

    @Transactional(readOnly = true)
    public ProductProjection findById(String id) {
        log.info("finding product by id: {}", id);
        return repository.findByProductId(id)
                .orElseThrow(Exceptions.notFound());
    }
}
