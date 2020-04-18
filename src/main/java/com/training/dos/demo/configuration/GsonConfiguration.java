package com.training.dos.demo.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.training.dos.demo.domain.Hora;
import com.training.dos.demo.domain.HoraDia;
import com.training.dos.demo.domain.Minuto;
import com.training.dos.demo.domain.Segundo;
import com.training.dos.demo.serialization.HoraDiaAdapter;
import com.training.dos.demo.serialization.IntegerValueAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class GsonConfiguration {

@Bean
    public Gson gson(){
        return new GsonBuilder()
                .registerTypeAdapter(HoraDia.class, new HoraDiaAdapter())
                .registerTypeAdapter(Hora.class, new IntegerValueAdapter<Hora>(Hora::of))
                .registerTypeAdapter(Minuto.class, new IntegerValueAdapter<Minuto>(Minuto::of))
                .registerTypeAdapter(Segundo.class, new IntegerValueAdapter<Segundo>(Segundo::of))
                .create();
    }
}
