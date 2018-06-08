package com.marekk.pim.product.adapter.converter;

import com.marekk.pim.product.adapter.ProductRow;
import com.marekk.pim.product.dto.ProductProjection;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.marekk.pim.infrastructure.api.Specification.FILE_CSV;
import static java.nio.file.Files.newBufferedWriter;

@Component
class ProductCSVMessageConverter extends AbstractProductMessageConverter {

    public ProductCSVMessageConverter() {
        super(MediaType.valueOf(FILE_CSV));
    }

    @Override
    String fileName() {
        return "attachment;filename=\"product.csv\"";
    }

    @SneakyThrows
    @Override
    Path fileContent(Page<ProductProjection> products) {
        Path tempPath = Files.createTempFile("file", ".csv");
        try (BufferedWriter writer = newBufferedWriter(tempPath)) {
            List<ProductRow> content = products.map(ProductRow::from).getContent();
            new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(';')
                    .build()
                    .write(content);

        }
        return tempPath;
    }

}