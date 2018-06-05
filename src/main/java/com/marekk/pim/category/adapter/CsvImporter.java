package com.marekk.pim.category.adapter;

import com.marekk.pim.infrastructure.api.UploadResult;
import org.springframework.web.multipart.MultipartFile;

import static com.google.common.base.Preconditions.checkArgument;
import static com.marekk.pim.infrastructure.exception.Exceptions.unsupportedFile;
import static liquibase.util.file.FilenameUtils.getExtension;

public class CsvImporter {
    public UploadResult importFile(MultipartFile file) {
        checkArgument("csv".equals(getExtension(file.getOriginalFilename())), unsupportedFile());
        return new UploadResult(5, 4, 5);
    }
}
