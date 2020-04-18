package com.training.dos.demo.configuration;

import com.training.dos.demo.domain.UnidadTiempo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import com.google.gson.Gson;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    private final Gson gson;

    @Autowired
    public AppConfig(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        GsonHttpMessageConverter messageConverter = new GsonHttpMessageConverter(gson);
        converters.add(messageConverter);
    }

}
