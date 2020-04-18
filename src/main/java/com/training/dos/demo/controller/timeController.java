package com.training.dos.demo.controller;

import com.training.dos.demo.domain.UnidadTiempo;
import com.training.dos.demo.domain.*;
import com.training.dos.demo.services.DayTimeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/times")
@AllArgsConstructor
public class timeController {

    private UnidadTiempo unidadTiemp;
    public final DayTimeService service;


    @GetMapping(value = "timecurrent")
    public HoraDia getCurrentTime(){
        System.out.println(this.unidadTiemp.getUnidad());
        LocalTime currentTime = LocalTime.now();
        return HoraDia.from(Hora.of(currentTime.getHour()),
                Minuto.of(currentTime.getMinute()),
                Segundo.of(currentTime.getSecond()),
                TimeUnitEnum.fromHour(TimeUnitEnum.valueOf(unidadTiemp.getUnidad()), currentTime)
        );
    }
    @PostMapping(value = "create")
    public DayTime createTime(@RequestBody DayTime dayTime){

        return service.create(dayTime.getHora(), dayTime.getMinuto(), dayTime.getSegundo());
    }

    @GetMapping(value = "findall")
    public List<DayTime> findAll(){
        return service.findAll();
    }
}
