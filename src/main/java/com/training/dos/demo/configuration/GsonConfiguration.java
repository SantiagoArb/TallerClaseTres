package com.training.dos.demo.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.training.dos.demo.products.Serialization.BigDecimalValueAdapter;
import com.training.dos.demo.products.Serialization.LongValueAdapter;
import com.training.dos.demo.products.Serialization.StringValueAdapter;
import com.training.dos.demo.products.domain.*;
import com.training.dos.demo.time.domain.Hora;
import com.training.dos.demo.time.domain.HoraDia;
import com.training.dos.demo.time.domain.Minuto;
import com.training.dos.demo.time.domain.Segundo;
import com.training.dos.demo.time.serialization.HoraDiaAdapter;
import com.training.dos.demo.time.serialization.IntegerValueAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfiguration {

@Bean
    public Gson gson(){
        return new GsonBuilder()
                .registerTypeAdapter(HoraDia.class, new HoraDiaAdapter())
                .registerTypeAdapter(Hora.class, new IntegerValueAdapter<Hora>(Hora::of))
                .registerTypeAdapter(Minuto.class, new IntegerValueAdapter<Minuto>(Minuto::of))
                .registerTypeAdapter(Segundo.class, new IntegerValueAdapter<Segundo>(Segundo::of))
                .registerTypeAdapter(ProductId.class, new LongValueAdapter<>(ProductId::of))
                .registerTypeAdapter(Name.class, new StringValueAdapter<>(Name::of))
                .registerTypeAdapter(Description.class, new StringValueAdapter<>(Description::of))
                .registerTypeAdapter(BasePrice.class, new BigDecimalValueAdapter<>(BasePrice::of))
                .registerTypeAdapter(TaxRate.class, new BigDecimalValueAdapter<>(TaxRate::of))
                .registerTypeAdapter(InventoryQuantity.class, new IntegerValueAdapter<>(InventoryQuantity::of))
                .create();
    }
}
