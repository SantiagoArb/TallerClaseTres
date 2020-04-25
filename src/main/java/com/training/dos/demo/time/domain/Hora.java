package com.training.dos.demo.time.domain;

import com.google.common.base.Preconditions;
import com.training.dos.demo.time.serialization.IntegerSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class Hora implements IntegerSerializable {

    private final Integer value;

    private Hora(Integer val){
        Preconditions.checkArgument(val >= 0 && val <= 23);
        this.value = val;
    }

    @Override
    public Integer valueOf() {
        return value;
    }
}
