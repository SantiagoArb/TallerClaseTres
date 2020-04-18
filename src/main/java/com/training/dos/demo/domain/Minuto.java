package com.training.dos.demo.domain;

import com.google.common.base.Preconditions;
import com.training.dos.demo.serialization.IntegerSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class Minuto implements IntegerSerializable {

    private final Integer value;

    public Minuto(Integer val){
        Preconditions.checkArgument(val >= 0 && val <= 59);
        this.value = val;
    }

    @Override
    public Integer valueOf() {
        return value;
    }
}
