package com.example.Parcial_Ibarra.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class dnaRespuesta {
    private boolean isMutant;

    public boolean isMutant(){
        return isMutant;
    }
}
