package com.marekk.pim.product.domain.query;

import com.marekk.pim.product.dto.ProductProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

interface ProductFinderRepository extends CrudRepository<ProductProjectionEntity, Long>,
        QueryByExampleExecutor<ProductProjection> {
    Optional<ProductProjection> findByProductId(String id);
}
