package com.marekk.pim.product;

import com.marekk.pim.product.adapter.ProductRow;
import com.marekk.pim.product.dto.ProductDTO;
import com.marekk.pim.product.dto.ProductProjection;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import sun.misc.IOUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;

import static java.nio.file.Files.newBufferedWriter;
import static java.nio.file.Files.readAllBytes;

/*
@author Volodymyr Rudyi
 */
@Component
public class SuperCSVMessageConverter extends AbstractHttpMessageConverter<Page<ProductProjection>> {

    private static final String FILE_CSV = "text/csv";

    public SuperCSVMessageConverter() {
        super(MediaType.valueOf(FILE_CSV));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return Page.class.isAssignableFrom(aClass) || ProductDTO.class.isAssignableFrom(aClass);
    }

    @Override
    protected Page<ProductProjection> readInternal(Class<? extends Page<ProductProjection>> aClass, HttpInputMessage httpInputMessage) throws IOException {
        throw new UnsupportedOperationException("readInternal() is not supported yet");
    }


    @Override
    protected void writeInternal(Page<ProductProjection> projections, HttpOutputMessage message) throws IOException {
        Path csvResource = create(projections);
        message.getHeaders().add(HttpHeaders.CONTENT_TYPE, FILE_CSV);
        message.getHeaders().add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"product.csv\"");
        OutputStream body = message.getBody();
        Files.copy(csvResource, body);
    }


    @SneakyThrows
    private Path create(Page<ProductProjection> productDTOS) throws IOException {
        Path tempPath = Files.createTempFile("file", ".csv");
        try (BufferedWriter writer = newBufferedWriter(tempPath)) {
            List<ProductRow> content = productDTOS.map(ProductRow::from).getContent();
            new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(';')
                    .build()
                    .write(content);

        }
        return tempPath;
    }

}