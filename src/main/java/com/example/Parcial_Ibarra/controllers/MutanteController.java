package com.example.Parcial_Ibarra.controllers;

import com.example.Parcial_Ibarra.dto.dnaRequest;
import com.example.Parcial_Ibarra.dto.dnaRespuesta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.Parcial_Ibarra.services.MutanteServices;

@RestController
@RequestMapping(path = "/mutant")
public class MutanteController {

    @Autowired
    private MutanteServices servicio;

    public MutanteController(MutanteServices mutanteServices) {
        this.servicio = mutanteServices;
    }

    @PostMapping
    public ResponseEntity<dnaRespuesta> checkMutant(@Valid @RequestBody dnaRequest dnaRequest) {

        boolean isMutant = servicio.analizarAdn(dnaRequest.getDna());
        dnaRespuesta dnaRespuesta = new dnaRespuesta(isMutant);
        if( isMutant ) {
            return ResponseEntity.ok(dnaRespuesta);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dnaRespuesta);
        }
    }

}
