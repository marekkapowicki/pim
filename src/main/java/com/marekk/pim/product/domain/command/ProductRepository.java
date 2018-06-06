package com.marekk.pim.product.domain.command;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByUuid(String id);
    Optional<ProductEntity> findByExternalId(String id);

}
