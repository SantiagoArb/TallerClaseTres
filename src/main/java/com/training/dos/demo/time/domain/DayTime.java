package com.training.dos.demo.time.domain;

import lombok.Value;

@Value(staticConstructor = "from")
public class DayTime {
    Hora hora;
    Minuto minuto;
    Segundo segundo;
}