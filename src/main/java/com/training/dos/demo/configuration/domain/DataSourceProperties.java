package com.training.dos.demo.configuration.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "datasource")
@RequiredArgsConstructor
@Data
public class DataSourceProperties {
    String host;
    Integer port;
    String username;
    String password;
    String db;
}