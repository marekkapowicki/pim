package com.marekk.pim.product.domain.command;

import com.google.common.annotations.VisibleForTesting;
import com.marekk.pim.api.config.IdResponse;
import com.marekk.pim.product.dto.ProductDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import static com.marekk.pim.infrastructure.exception.Exceptions.notFound;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Slf4j
public class ProductFacade {
    ProductRepository productRepository;
    ProductFactory productFactory;

    @Transactional
    public IdResponse create(ProductDTO dto) {
        log.info("create new product from {}", dto);
        ProductEntity entity = productFactory.create(dto);
        return IdResponse.of(productRepository.save(entity).getUuid());
    }


    @Transactional
    public void update(String id, ProductDTO dto) {
        log.info("updating the product with id = {}", id);
        ProductEntity entity = productRepository.findByUuid(id)
                .orElseThrow(notFound("product not found id: " + id));
        productRepository.save(entity.merge(dto));


    }

    @VisibleForTesting
    String findNameById(String id) {
        log.info("finding product name by id = {}", id);
        return productRepository.findByUuid(id)
                .map(ProductEntity::getName)
                .orElseThrow(notFound());
    }
}
