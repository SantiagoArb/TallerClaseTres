package com.training.dos.demo.time.domain;


import com.google.common.base.Preconditions;
import lombok.Value;

@Value(staticConstructor = "from")
public class HoraDia {

    private final Hora hora;
    private final Minuto minuto;
    private final Segundo segundo;
    private final Long cantidad;

    public HoraDia(Hora horas, Minuto minutos, Segundo segundos, Long cantidades){
        Preconditions.checkNotNull(horas);
        Preconditions.checkNotNull(minutos);
        Preconditions.checkNotNull(segundos);
        Preconditions.checkNotNull(cantidades);
        this.hora = horas;
        this.minuto = minutos;
        this.segundo = segundos;
        this.cantidad = cantidades;
    }
}
