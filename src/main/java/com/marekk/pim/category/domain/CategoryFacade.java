package com.marekk.pim.category.domain;

import com.marekk.pim.category.dto.CategoryDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.marekk.pim.infrastructure.Preconditions.checkArgument;
import static com.marekk.pim.infrastructure.exception.Exceptions.conflicted;
import static com.marekk.pim.infrastructure.exception.Exceptions.illegalState;
import static com.marekk.pim.infrastructure.exception.Exceptions.notFound;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Slf4j
public class CategoryFacade {
    CategoryRepository categoryRepository;

    @Transactional
    public CategoryDTO create(CategoryDTO dto) {
        checkArgument(dto != null, illegalState());
        log.info("create category {}", dto);
        checkArgument(categoryRepository.countByExternalId(dto.getExternalId()) == 0
                , conflicted("external id already exists"));
        return categoryRepository.save(new CategoryEntity(dto.getExternalId(), dto.getName())).toDTO();
    }

    @Transactional
    public String update(String id, CategoryDTO dto) {
        log.info("updating the category with id = {}", id);
        CategoryEntity entity = categoryRepository.findByUuid(id)
                .orElseThrow(notFound("product not found id: " + id));
        categoryRepository.save(entity.merge(dto));
        return dto.getId();
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> retrieveAll(Pageable pageable) {
        log.info("get page = {} of all categories", pageable);
        return categoryRepository.findAll(pageable)
                .map(CategoryEntity::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<String> retrieveByExternalId(String id) {
        log.info("find category by external id = {}", id);
        return categoryRepository.findByExternalId(id)
                .map(CategoryEntity::toDTO)
                .map(CategoryDTO::getId);
    }
}
