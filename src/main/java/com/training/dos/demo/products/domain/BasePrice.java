package com.training.dos.demo.products.domain;

import com.google.common.base.Preconditions;
import com.training.dos.demo.products.Serialization.BigDecimalSerializable;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class BasePrice implements BigDecimalSerializable {
    private final BigDecimal value;

    public BasePrice(BigDecimal value) {
        Preconditions.checkNotNull(value, "Base price can not be null");
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0, "Base price can not be negative");
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
