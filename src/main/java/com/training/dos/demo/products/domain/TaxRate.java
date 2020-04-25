package com.training.dos.demo.products.domain;

import com.training.dos.demo.products.Serialization.BigDecimalSerializable;
import com.training.dos.demo.products.common.Preconditions;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")

public class TaxRate implements BigDecimalSerializable {
    private final BigDecimal value;

    public TaxRate(BigDecimal value) {
        Preconditions.checkNotNull(value);
        Preconditions.highestTypeBigDecimal(value);
        Preconditions.smallestTypeBigDecimal(value);
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
