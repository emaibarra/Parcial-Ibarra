package com.example.Parcial_Ibarra.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder


public class Mutante extends Base {

    private String dna;
    private boolean isMutant;




}