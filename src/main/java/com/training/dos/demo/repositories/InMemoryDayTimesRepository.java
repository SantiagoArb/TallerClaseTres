package com.training.dos.demo.repositories;

import com.training.dos.demo.domain.DayTime;
import com.training.dos.demo.domain.Hora;
import com.training.dos.demo.domain.Minuto;
import com.training.dos.demo.domain.Segundo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Qualifier("in-memory")
public class InMemoryDayTimesRepository implements DayTimesRepository {
    private final Map<Long,DayTime> state = new HashMap<>();
    @Override
    public DayTime create(Hora hora, Minuto minuto, Segundo segundo) {
        Long id = state.size() +1L;
        DayTime daytimecreated = DayTime.from(hora,minuto,segundo);
        state.put(id,daytimecreated);
        return daytimecreated;
    }

    @Override
    public List<DayTime> findAll() {

        List<DayTime> list = state.values().stream()
                .collect(Collectors.toList());
        return list;
    }
}
