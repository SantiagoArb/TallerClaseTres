package com.training.dos.demo.products.domain;

import com.training.dos.demo.products.common.Preconditions;
import com.training.dos.demo.time.serialization.IntegerSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class InventoryQuantity implements IntegerSerializable {
    private final Integer value;

    public InventoryQuantity(Integer value) {
        Preconditions.checkNotNull(value);
        Preconditions.smallestTypeInteger(value);
        this.value = value;
    }


    @Override
    public Integer valueOf() {
        return value;
    }
}
