package com.marekk.pim.product.domain.query;

import com.marekk.pim.product.dto.ProductProjection;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface ProductFinderRepository extends CrudRepository<ProductProjectionEntity, Long> {
    Optional<ProductProjection> findByProductId(String id);
}
