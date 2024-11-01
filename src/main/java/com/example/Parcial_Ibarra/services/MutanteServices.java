package com.example.Parcial_Ibarra.services;

import com.example.Parcial_Ibarra.dto.statsRespuesta;
import com.example.Parcial_Ibarra.entities.Mutante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Parcial_Ibarra.repositories.MutanteRepository;

import java.util.Optional;

@Service
public class MutanteServices {

    private final MutanteRepository mutanteRepository;
    private final EstadisticasServices estadisticasServices;

    // Constructor con inyección de dependencias
    @Autowired
    public MutanteServices(MutanteRepository mutanteRepository, EstadisticasServices estadisticasServices) {
        this.mutanteRepository = mutanteRepository;
        this.estadisticasServices = estadisticasServices;
    }

    public boolean secuenciaHorizontal(String[] adn) {

        for (int i = 0; i < adn.length; i++) {
            int contador = 0;
            for (int j = 0; j < adn[i].length()-1; j++) {
                if (adn[i].charAt(j) == adn[i].charAt(j+1)) {
                    contador = contador+1;
                    if (contador==3){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean secuenciaVertical(String[] adn) {

        for (int i = 0; i < adn.length; i++) {
            int contador = 0;

            for (int j=0; j < adn.length-1;j++) {

                if (adn[j].charAt(i) == adn[j+1].charAt(i)) {
                    contador = contador + 1;
                } else break;
                if (contador==3){
                    return true;
                }
            }

        }
        return false;
    }

    public boolean secuenciaOblicua(String[] adn) {
        int contador = 0;
        for (int i = 0; i < adn.length-1; i++) {

            if (adn[i].charAt(i)==adn[i+1].charAt(i+1)){
                contador=contador+1;
            } else contador = 0;
            if (contador==3){
                return true;
            }
        }

        return false;
    }

    public boolean tieneSecuencia(String[]adn){
        //secuencia horizontal
        for (int i = 0; i < adn.length; i++) {
            int contador = 0;
            for (int j = 0; j < adn[i].length()-1; j++) {
                if (adn[i].charAt(j) == adn[i].charAt(j+1)) {
                    contador = contador+1;
                    if (contador==3){
                        return true;
                    }
                }
            }
        }
        //secuencia vertical
        for (int i = 0; i < adn.length; i++) {
            int contador2 = 0;

            for (int j=0; j < adn.length-1;j++) {

                if (adn[j].charAt(i) == adn[j+1].charAt(i)) {
                    contador2 = contador2 + 1;
                } else break;
                if (contador2==3){
                    return true;
                }
            }

        }
        //secuencia oblicua
        int contador3 = 0;
        for (int i = 0; i < adn.length-1; i++) {

            if (adn[i].charAt(i)==adn[i+1].charAt(i+1)){
                contador3=contador3+1;
            } else contador3 = 0;
            if (contador3==3){
                return true;
            }
        }
        return false;
    }


    public boolean isMutant(String[] adn) {

        if (secuenciaVertical(adn)||secuenciaHorizontal(adn)||secuenciaOblicua(adn)) {
            return true;
        }
        return false;

    }

    public boolean analizarAdn(String[] adn){
        String dnaSequence = String.join(",", adn);

        Optional<Mutante> existingDna = mutanteRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()){
            return existingDna.get().isMutant();
        }

        boolean isMutant= isMutant(adn);
        Mutante mutante = Mutante.builder()
                .dna(dnaSequence)
                .isMutant(isMutant)
                .build();
        mutanteRepository.save(mutante);

        return isMutant(adn);
    }

    public statsRespuesta getStats() {

        return estadisticasServices.getStats();
    }
}
