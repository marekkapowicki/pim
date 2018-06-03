package com.marekk.pim.category.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByUuid(String id);
    long countByExternalId(String externalId);

}
