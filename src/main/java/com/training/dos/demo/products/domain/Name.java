package com.training.dos.demo.products.domain;

import com.google.common.base.Preconditions;
import com.training.dos.demo.products.Serialization.StringSerializable;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

@Value(staticConstructor = "of")
public class Name implements StringSerializable {

    private final String value;

    public Name(String value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(StringUtils.isNotBlank(value), "Product name can't be blank");
        Preconditions.checkArgument(StringUtils.length(value) <= 100, "Product name can not be longer than 100");
        this.value = value;
    }

    @Override
    public String valueOf() {
        return value;
    }
}
