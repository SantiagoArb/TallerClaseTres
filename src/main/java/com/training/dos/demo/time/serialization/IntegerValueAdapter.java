package com.training.dos.demo.time.serialization;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.function.Function;

public class IntegerValueAdapter<T extends IntegerSerializable> implements GsonAdapter<T>{

    private final Function<Integer, T> factory;

    public IntegerValueAdapter(Function<Integer, T> factory) {
        this.factory = factory;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Integer value = json.getAsInt();
        return factory.apply(value);
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        Integer value = src.valueOf();
        return new JsonPrimitive(value);
    }
}
