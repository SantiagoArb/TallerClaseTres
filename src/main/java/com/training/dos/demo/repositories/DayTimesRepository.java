package com.training.dos.demo.repositories;

import com.training.dos.demo.domain.DayTime;
import com.training.dos.demo.domain.Hora;
import com.training.dos.demo.domain.Minuto;
import com.training.dos.demo.domain.Segundo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayTimesRepository {
    DayTime create(Hora hora, Minuto minuto, Segundo segundo);
    List<DayTime> findAll();
}
