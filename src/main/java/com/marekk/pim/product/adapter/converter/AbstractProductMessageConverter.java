package com.marekk.pim.product.adapter.converter;

import com.marekk.pim.product.dto.ProductDTO;
import com.marekk.pim.product.dto.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

abstract class AbstractProductMessageConverter extends AbstractHttpMessageConverter<Page<ProductProjection>> {
    AbstractProductMessageConverter(MediaType mediaType) {
        super(mediaType);
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
        Path csvResource = fileContent(projections);
        message.getHeaders().add(HttpHeaders.CONTENT_DISPOSITION, fileName());
        OutputStream body = message.getBody();
        Files.copy(csvResource, body);
    }

    abstract String fileName();

    abstract Path fileContent(Page<ProductProjection> products);
}
