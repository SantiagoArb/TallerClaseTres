package com.training.dos.demo.products.domain;

import com.training.dos.demo.products.Serialization.StringSerializable;
import com.training.dos.demo.products.common.Preconditions;
import lombok.Value;


@Value(staticConstructor = "of")
public class Description implements StringSerializable {

    private final String value;

    public Description(String value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkNotEmpty(value);
        Preconditions.checkMaxCharacters(value,280);
        this.value = value;
    }

    @Override
    public String valueOf() {
        return value;
    }
}
