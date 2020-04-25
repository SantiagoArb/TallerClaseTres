package com.training.dos.demo.products.domain;

import com.training.dos.demo.products.common.Preconditions;
import com.training.dos.demo.products.Serialization.StringSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class Name implements StringSerializable {

    private final String value;

    public Name(String value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkNotEmpty(value);
        this.value = value;
    }

    @Override
    public String valueOf() {
        return value;
    }
}
