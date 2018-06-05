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
    public <T> Iterator<T> iterator(Class<T> className) {
        Reader reader = new InputStreamReader(file.getInputStream());
        return new CsvToBeanBuilder(reader)
                .withType(className)
                .withIgnoreQuotations(true)
                .withIgnoreLeadingWhiteSpace(true)
                .build().iterator();

    }


}
