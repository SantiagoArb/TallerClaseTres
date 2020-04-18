package com.training.dos.demo.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public enum TimeUnitEnum {
    MILISEGUNDOS,
    SEGUNDOS,
    MINUTOS,
    HORAS;

    public static Long fromHour(TimeUnitEnum unit, LocalTime currentDate){
        LocalTime midnight  = LocalTime.of(00,00,00,00);
        switch (unit){
            case HORAS:

                return ChronoUnit.HOURS.between(midnight,currentDate);
            case MINUTOS:
                return ChronoUnit.MINUTES.between(midnight,currentDate);
            case SEGUNDOS:
                return ChronoUnit.SECONDS.between(midnight,currentDate);
            case MILISEGUNDOS:
                return ChronoUnit.MILLIS.between(midnight,currentDate);
            default:
                throw new UnsupportedOperationException();
        }

    }
}
