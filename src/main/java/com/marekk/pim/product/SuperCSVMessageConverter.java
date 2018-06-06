package com.marekk.pim.product;

import com.marekk.pim.product.dto.ProductDTO;
import com.opencsv.CSVWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;

import static java.nio.file.Files.newBufferedWriter;
import static java.nio.file.Files.readAllBytes;

/*
@author Volodymyr Rudyi
 */
@Component
public class SuperCSVMessageConverter extends AbstractHttpMessageConverter<Object> {

    private static final String [] headers = {"ZamroID","Name","Description","MinOrderQuantity","UnitOfMeasure","CategoryID","PurchasePrice","Available"};
    private static final String TEXT_CSV = "text/csv";

    public SuperCSVMessageConverter() {
        super(MediaType.valueOf(TEXT_CSV));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return ProductDTO.class.isAssignableFrom(aClass) || LinkedHashMap.class.isAssignableFrom(aClass);
    }

    @Override
    protected ProductDTO readInternal(Class<?> aClass,
                                   HttpInputMessage message) throws IOException {
        throw new UnsupportedOperationException("readInternal() is not supported yet");
    }

    @Override
    protected void writeInternal(Object object,
                                 HttpOutputMessage message) throws IOException {

        Path csvResource = create();
//        message.getHeaders().add(HttpHeaders.CONTENT_TYPE, TEXT_CSV);
        message.getHeaders().add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + csvResource.getFileName() + "\"");

        message.getBody().write(readAllBytes(csvResource));
    }

    private Path create() throws IOException {
        Path tempPath = Files.createTempFile("invoiceconvertedfile_", ".csv");
        try (BufferedWriter writer = newBufferedWriter(tempPath)) {
            new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END).writeNext(headers);
        }
        return tempPath;
    }

}