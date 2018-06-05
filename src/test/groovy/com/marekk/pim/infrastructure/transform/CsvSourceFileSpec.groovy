package com.marekk.pim.infrastructure.transform

import com.marekk.pim.product.adapter.ProductRow
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import spock.lang.Specification

class CsvSourceFileSpec extends Specification {
    def "upload the product csv file"() {
        given:
            File csvFile = new File('src/test/resources/files/ProductData.csv')
            MultipartFile file = new MockMultipartFile(csvFile.name, csvFile.text.bytes)
            Iterator<ProductRow> iterator = SourceFile.csv(file).iterator(ProductRow)
        when:

            while (iterator.hasNext()) {
                iterator.next()
            }
        then:
            notThrown()

    }
}
