package com.marekk.pim.category.adapter;

import com.marekk.pim.category.domain.CategoryFacade;
import com.marekk.pim.category.dto.CategoryDTO;
import com.marekk.pim.infrastructure.api.UploadResult;
import com.marekk.pim.infrastructure.api.UploadResultBuilder;
import com.marekk.pim.infrastructure.transform.SourceFile;
import javaslang.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class CategoryCsvImporter {
    CategoryFacade categoryFacade;
    public UploadResult importFile(SourceFile file) {
        Iterator<CategoryRow> iterator = file.iterator(CategoryRow.class);
        UploadResultBuilder uploadResultBuilder = UploadResultBuilder.builder();
        while (iterator.hasNext()) {
            Try.of(iterator::next)
                .onSuccess(row -> importRow(row.toDto(), uploadResultBuilder))
                .onFailure(e -> uploadResultBuilder.incrementErrors());
        }
        return uploadResultBuilder.build();
    }

    private void importRow(final CategoryDTO dto,
                           UploadResultBuilder uploadResultBuilder) {
        categoryFacade.retrieveByExternalId(dto.getExternalId())
                .map(id -> updateDto(id, dto, uploadResultBuilder))
                .orElseGet(() -> insertDto(dto, uploadResultBuilder));
    }

    private int insertDto( CategoryDTO dto,
                           UploadResultBuilder uploadResultBuilder) {
        log.info("insert category dto {}", dto);
        Try.of(() -> categoryFacade.create( dto))
                .onSuccess(ct -> uploadResultBuilder.incrementCreated())
                .onFailure(e -> uploadResultBuilder.incrementErrors());
        return 0;
    }
    private int updateDto(String id,
                          CategoryDTO dto,
                          UploadResultBuilder uploadResultBuilder) {
        log.info("update category row {}", dto);
        Try.of(() -> categoryFacade.update(id, dto))
                .onSuccess(ct -> uploadResultBuilder.incrementUpdated())
                .onFailure(e -> uploadResultBuilder.incrementErrors());
        return 0;
    }

}
