package com.training.dos.demo.products.domain;

import com.google.common.base.Preconditions;
import com.training.dos.demo.products.Serialization.LongSerializable;
import lombok.Value;


@Value(staticConstructor = "of")
public class ProductId implements LongSerializable {

 private final Long value;

    public ProductId(Long value)  {
        Preconditions.checkNotNull(value, "value can not be null");
        Preconditions.checkArgument(value >= 1, "value should be greater than 0");
        this.value = value;
    }

    @Override
    public Long valueOf() {
        return value;
    }
}
