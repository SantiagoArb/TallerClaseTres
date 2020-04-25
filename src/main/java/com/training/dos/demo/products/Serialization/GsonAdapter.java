package com.training.dos.demo.products.Serialization;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public interface GsonAdapter<T>  extends JsonDeserializer<T>, JsonSerializer<T> {

}
