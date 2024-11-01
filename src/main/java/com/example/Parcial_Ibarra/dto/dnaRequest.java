package com.example.Parcial_Ibarra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.Parcial_Ibarra.validators.AdnValido;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class dnaRequest {
    @AdnValido
    private String[] dna;
}
