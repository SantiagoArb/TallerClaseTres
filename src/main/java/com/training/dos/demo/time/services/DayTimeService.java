package com.training.dos.demo.time.services;

import com.training.dos.demo.time.domain.DayTime;
import com.training.dos.demo.time.domain.Hora;
import com.training.dos.demo.time.domain.Minuto;
import com.training.dos.demo.time.domain.Segundo;
import com.training.dos.demo.time.repositories.DayTimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayTimeService {

    private final DayTimesRepository repository;

    @Autowired
    public DayTimeService(@Qualifier("in-memory") DayTimesRepository repo) {
        this.repository = repo;
    }

    public DayTime create(Hora hora, Minuto minuto, Segundo segundo){
        return repository.create(hora, minuto, segundo);
    }

    public List<DayTime> findAll(){
        return repository.findAll();
    }
}
