package com.training.dos.demo.products.Serialization;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.function.Function;

public class LongValueAdapter <T extends LongSerializable> implements GsonAdapter<T>  {

    private final Function<Long, T> factory;

    public LongValueAdapter(Function<Long, T> factory) {
        this.factory = factory;
    }

    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Long value = jsonElement.getAsLong();
        return factory.apply(value);
    }

    @Override
    public JsonElement serialize(T src, Type type, JsonSerializationContext jsonSerializationContext) {
        Long value = src.valueOf();
        return new JsonPrimitive(value);
    }
}
