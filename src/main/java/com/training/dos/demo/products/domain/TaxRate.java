package com.training.dos.demo.products.domain;

import com.google.common.base.Preconditions;
import com.training.dos.demo.products.Serialization.BigDecimalSerializable;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")

public class TaxRate implements BigDecimalSerializable {
    private final BigDecimal value;

    public TaxRate(BigDecimal value) {
        Preconditions.checkNotNull(value, "Tax rate  can not be null");
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0, "Tax rate can not be negative");
        Preconditions.checkArgument(value.compareTo(BigDecimal.ONE) <= 0, "Tax rate can not be more than 1");
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
