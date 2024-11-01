package com.example.Parcial_Ibarra.controllers;

import com.example.Parcial_Ibarra.dto.statsRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.Parcial_Ibarra.services.EstadisticasServices;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class EstadisticasController {
    @Autowired
    private final EstadisticasServices estadisticasServices;

    @Autowired
    public EstadisticasController(EstadisticasServices statsService) {
        this.estadisticasServices = statsService;
    }

    @GetMapping
    public statsRespuesta getStats(){
        return estadisticasServices.getStats();
    }

}
