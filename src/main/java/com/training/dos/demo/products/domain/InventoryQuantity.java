package com.training.dos.demo.products.domain;

import com.google.common.base.Preconditions;
import com.training.dos.demo.time.serialization.IntegerSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class InventoryQuantity implements IntegerSerializable {
    private final Integer value;

    public InventoryQuantity(Integer value) {
        Preconditions.checkNotNull(value, "quantity can not be null");
        Preconditions.checkArgument(value > 0, "quantity should be greater than 0");
        this.value = value;
    }


    @Override
    public Integer valueOf() {
        return value;
    }
}
