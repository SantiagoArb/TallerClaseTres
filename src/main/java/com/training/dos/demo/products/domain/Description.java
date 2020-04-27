package com.training.dos.demo.products.domain;

import com.google.common.base.Preconditions;
import com.training.dos.demo.products.Serialization.StringSerializable;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;


@Value(staticConstructor = "of")
public class Description implements StringSerializable {

    private final String value;

    public Description(String value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(StringUtils.isNotBlank(value), "Product description can't be blank");
        Preconditions.checkArgument(StringUtils.length(value) <= 280, "Product description can not be longer than 280");

        this.value = value;
    }

    @Override
    public String valueOf() {
        return value;
    }
}
