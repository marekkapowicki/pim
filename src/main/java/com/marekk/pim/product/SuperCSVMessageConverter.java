package com.marekk.pim.product;

import com.marekk.pim.product.dto.ProductDTO;
import com.opencsv.CSVWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedHashMap;

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

        message.getHeaders().add(HttpHeaders.CONTENT_TYPE, TEXT_CSV);

        try (final Writer writer = new OutputStreamWriter(message.getBody())){
            final CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            csvWriter.writeNext(headers);

        }

    }

}