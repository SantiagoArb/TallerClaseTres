package com.training.dos.demo.products.domain;

import com.training.dos.demo.products.Serialization.BigDecimalSerializable;
import com.training.dos.demo.products.common.Preconditions;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class BasePrice implements BigDecimalSerializable {
    private final BigDecimal value;

    public BasePrice(BigDecimal value) {
        Preconditions.checkNotNull(value);
        Preconditions.highestTypeBigDecimal(value);
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
