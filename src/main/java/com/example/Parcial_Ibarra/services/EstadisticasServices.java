package com.example.Parcial_Ibarra.services;

import com.example.Parcial_Ibarra.dto.statsRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Parcial_Ibarra.repositories.MutanteRepository;

@Service

public class EstadisticasServices {
    @Autowired
    private MutanteRepository mutanteRepository;

    public statsRespuesta getStats(){
        long countMutantDna = mutanteRepository.countByIsMutant(true);
        long countHumanDna = mutanteRepository.countByIsMutant(false);
        double ratio = countMutantDna == 0 ? 0 : (double) countMutantDna/countHumanDna;
        return new statsRespuesta(countMutantDna,countHumanDna,ratio);
    }
}
