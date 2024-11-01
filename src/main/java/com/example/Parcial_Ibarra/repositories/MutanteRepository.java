package com.example.Parcial_Ibarra.repositories;

import com.example.Parcial_Ibarra.entities.Mutante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MutanteRepository extends JpaRepository<Mutante, Long> {
    Optional<Mutante> findByDna(String secuenciaAdn);

    long countByIsMutant(boolean isMutant);
    long count();
}