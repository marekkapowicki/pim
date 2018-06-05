package com.marekk.pim.infrastructure.transform;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.MODULE)
class CsvSourceFile implements SourceFile {
    MultipartFile file;

    @SneakyThrows
    @Override
    /**
     * there is a base parser that just skips the wrong lines
     * my goal was to deliver feature not to handle bad files
     */
    public <T> Iterator<T> iterator(Class<T> className) {
        Reader reader = new InputStreamReader(file.getInputStream());
        return new CsvToBeanBuilder(reader)
                .withThrowExceptions(false)
                .withType(className)
                .withIgnoreQuotations(false)
                .withIgnoreLeadingWhiteSpace(true)
                .build().iterator();

    }


}
