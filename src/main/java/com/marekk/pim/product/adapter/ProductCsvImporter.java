package com.marekk.pim.product.adapter;

import com.marekk.pim.infrastructure.api.UploadResult;
import com.marekk.pim.infrastructure.api.UploadResultBuilder;
import com.marekk.pim.infrastructure.transform.SourceFile;
import com.marekk.pim.product.domain.command.ProductFacade;
import com.marekk.pim.product.domain.query.ProductFinderFacade;
import com.marekk.pim.product.dto.ProductDTO;
import javaslang.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ProductCsvImporter {
    ProductFacade productFacade;
    ProductFinderFacade productFinderFacade;

    public UploadResult importFile(SourceFile file) {
        Iterator<ProductRow> iterator = file.iterator(ProductRow.class);
        UploadResultBuilder uploadResultBuilder = UploadResultBuilder.builder();
        while (iterator.hasNext()) {
            Try.of(iterator::next)
                    .onSuccess(row -> importRow(row.toDto(), uploadResultBuilder))
                    .onFailure(e -> uploadResultBuilder.incrementErrors());
        }
        return uploadResultBuilder.build();
    }

    private void importRow(final ProductDTO dto,
                           UploadResultBuilder uploadResultBuilder) {
        productFinderFacade.findByExternalId(dto.getExternalId())
                .map(id -> updateDto(id, dto, uploadResultBuilder))
                .orElseGet(() -> insertDto(dto, uploadResultBuilder));
    }

    private int insertDto(ProductDTO dto,
                          UploadResultBuilder uploadResultBuilder) {
        log.info("insert product dto {}", dto);
        Try.of(() -> productFacade.create(dto))
                .onSuccess(ct -> uploadResultBuilder.incrementCreated())
                .onFailure(e -> uploadResultBuilder.incrementErrors());
        return 0;
    }

    private int updateDto(String id,
                          ProductDTO dto,
                          UploadResultBuilder uploadResultBuilder) {
        log.info("update product row {}", dto);
        Try.of(() -> productFacade.update(id, dto))
                .onSuccess(ct -> uploadResultBuilder.incrementUpdated())
                .onFailure(e -> uploadResultBuilder.incrementErrors());
        return 0;
    }

}
