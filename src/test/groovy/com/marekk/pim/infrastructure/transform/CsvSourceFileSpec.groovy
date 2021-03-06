package com.marekk.pim.infrastructure.transform

import com.marekk.pim.product.adapter.ProductRow
import javaslang.control.Try
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import spock.lang.Specification

class CsvSourceFileSpec extends Specification {
    def "upload the product csv file"() {
        given:
            File csvFile = new File('src/test/resources/files/smallProductData.csv')
            MultipartFile file = new MockMultipartFile(csvFile.name, csvFile.text.bytes)

        when:
            Iterator<ProductRow> iter = SourceFile.csv(file).iterator(ProductRow)

        then:
           iter.size() == 1

    }
}
