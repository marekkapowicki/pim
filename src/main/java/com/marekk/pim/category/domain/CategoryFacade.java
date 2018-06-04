package com.marekk.pim.category.domain;

import com.marekk.pim.category.dto.CategoryDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static com.marekk.pim.infrastructure.Preconditions.checkArgument;
import static com.marekk.pim.infrastructure.exception.Exceptions.conflicted;
import static com.marekk.pim.infrastructure.exception.Exceptions.illegalState;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Slf4j
public class CategoryFacade {
    CategoryRepository categoryRepository;

    @Transactional
    public CategoryDTO create(CategoryDTO dto) {
        checkArgument(dto != null, illegalState());
        log.info("create category {}", dto);
        checkArgument( categoryRepository.countByExternalId(dto.getExternalId()) == 0
                , conflicted("external id already exists"));
        return categoryRepository.save(new CategoryEntity(dto.getExternalId(), dto.getName())).toDTO();
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> retrieveAll(Pageable pageable) {
        log.info("get page = {} of all categories");
        return categoryRepository.findAll(pageable)
                .map(CategoryEntity::toDTO);
    }
}
