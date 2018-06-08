package com.marekk.pim.product.adapter;

import com.marekk.pim.product.dto.ProductDTO;
import com.marekk.pim.product.dto.ProductProjection;
import com.opencsv.bean.CsvBindByName;
import javaslang.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static com.google.common.primitives.Ints.tryParse;
import static org.apache.commons.lang3.math.NumberUtils.createBigDecimal;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    String productId;

    public static ProductRow from(ProductProjection dto) {
        return new ProductRow(dto.getExternalId(), dto.getName(), dto.getDescription(), dto.getMinOrderQuantity().toString()
                , dto.getUnitOfMeasure().toString(), dto.getCategoryName(), dto.getPurchasePrice().toString()
                , dto.getAvailableQuantity().toString(), dto.getProductId());
    }
    ProductDTO toDto() {
        return new ProductDTO(productId, zamroId, name, description, toInt(minOrderQuantity), toInt(unitOfMeasure), categoryID, toBigDecimal(purchasePrice), toInt(available));
    }

    private Integer toInt(String value) {
        return value == null ? null: tryParse(value);
    }

    private BigDecimal toBigDecimal(String value) {
        return (value == null) ? null : Try.of(() -> createBigDecimal(value)).getOrElse(() -> null);
    }

}
