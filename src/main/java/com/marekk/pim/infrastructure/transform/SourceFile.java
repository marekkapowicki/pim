package com.marekk.pim.infrastructure.transform;

import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;

import static com.marekk.pim.infrastructure.Preconditions.checkArgument;
import static com.marekk.pim.infrastructure.exception.Exceptions.illegalState;
import static com.marekk.pim.infrastructure.exception.Exceptions.unsupportedFile;
import static liquibase.util.file.FilenameUtils.getExtension;

public interface SourceFile {

    String CSV_FILE = "csv";

    static SourceFile csv(MultipartFile uploadFile) {
        checkArgument(uploadFile != null, illegalState());
        checkArgument(CSV_FILE.equals(getExtension(uploadFile.getName())) || CSV_FILE.equals(getExtension(uploadFile.getOriginalFilename())), unsupportedFile());
        return new CsvSourceFile(uploadFile);
    }

    <T> Iterator<T> iterator(Class<T> className);
}
