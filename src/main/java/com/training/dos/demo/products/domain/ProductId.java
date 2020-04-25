package com.training.dos.demo.products.domain;

import com.training.dos.demo.products.Serialization.LongSerializable;
import com.training.dos.demo.products.common.Preconditions;
import lombok.Value;


@Value(staticConstructor = "of")
public class ProductId implements LongSerializable {

 private final Long value;

    public ProductId(Long value)  {
        Preconditions.checkNotNull(value);
        Preconditions.smallestTypeLong(value);
        Preconditions.checkMaxCharacters(value,100);
        this.value = value;
    }

    @Override
    public Long valueOf() {
        return value;
    }
}
