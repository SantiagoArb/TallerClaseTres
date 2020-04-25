package com.training.dos.demo.time.serialization;

import com.google.gson.*;
import com.training.dos.demo.time.domain.HoraDia;

import java.lang.reflect.Type;

public class HoraDiaAdapter implements JsonSerializer<HoraDia>, JsonDeserializer<HoraDia> {
    @Override
    public HoraDia deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(HoraDia src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("hora", src.getHora().getValue());
        object.addProperty("minuto", src.getMinuto().getValue());
        object.addProperty("segundo", src.getSegundo().getValue());
        object.addProperty("cantidad", src.getCantidad());
        return object;
    }
}
