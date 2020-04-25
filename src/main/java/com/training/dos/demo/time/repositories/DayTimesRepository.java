package com.training.dos.demo.time.repositories;

import com.training.dos.demo.time.domain.DayTime;
import com.training.dos.demo.time.domain.Hora;
import com.training.dos.demo.time.domain.Minuto;
import com.training.dos.demo.time.domain.Segundo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayTimesRepository {
    DayTime create(Hora hora, Minuto minuto, Segundo segundo);
    List<DayTime> findAll();
}
