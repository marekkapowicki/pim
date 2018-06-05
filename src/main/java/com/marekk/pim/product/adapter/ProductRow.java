package com.marekk.pim.product.adapter;

import com.marekk.pim.product.dto.ProductDTO;
import com.opencsv.bean.CsvBindByName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@ToString
@Getter
@NoArgsConstructor
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRow {

    @CsvBindByName(column = "ZamroID")
    String zamroId;
    @CsvBindByName(column = "Name")
    String name;
    @CsvBindByName(column = "Description")
    String description;
    @CsvBindByName(column = "MinOrderQuantity")
    String minOrderQuantity;
    @CsvBindByName(column = "UnitOfMeasure")
    String unitOfMeasure;
    @CsvBindByName(column = "CategoryID")
    String categoryID;
    @CsvBindByName(column = "PurchasePrice")
    String purchasePrice;
    @CsvBindByName(column = "Available")
    String available;

    ProductDTO toDto() {
        return new ProductDTO(null, zamroId, name, description, toInt(minOrderQuantity), toInt(unitOfMeasure), categoryID,toBigDecimal(purchasePrice), toInt(available));
    }

    private Integer toInt(String value) {
        return value == null ? null : Integer.getInteger(value, null);
    }

    private BigDecimal toBigDecimal(String value) {
        return value == null ? null : BigDecimal.valueOf(3L);
    }

}
