package com.marekk.pim.product.adapter.converter;

import com.marekk.pim.product.dto.ProductProjection;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.marekk.pim.infrastructure.api.Specification.FILE_TXT;
import static java.nio.file.Files.newBufferedWriter;

@Component
class ProductTxtMessageConverter extends AbstractProductMessageConverter {

    public ProductTxtMessageConverter() {
        super(MediaType.valueOf(FILE_TXT));
    }

    @Override
    String fileName() {
        return "attachment;filename=\"product.txt\"";
    }


    @Override
    @SneakyThrows
    Path fileContent(Page<ProductProjection> products) {
        Path tempPath = Files.createTempFile("file", ".txt");
        try (BufferedWriter writer = newBufferedWriter(tempPath)) {
            products.forEach(product -> tryWrite(writer, product.print()));

        }
        return tempPath;
    }

    @SneakyThrows
    private void tryWrite(Writer writer, String text) {
        writer.write(text);
    }

}