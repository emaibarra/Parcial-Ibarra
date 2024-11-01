package com.example.Parcial_Ibarra;
import com.example.Parcial_Ibarra.controllers.EstadisticasController;
import com.example.Parcial_Ibarra.controllers.MutanteController;
import com.example.Parcial_Ibarra.dto.dnaRequest;
import com.example.Parcial_Ibarra.dto.dnaRespuesta;
import com.example.Parcial_Ibarra.dto.statsRespuesta;
import com.example.Parcial_Ibarra.services.EstadisticasServices;
import com.example.Parcial_Ibarra.services.MutanteServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParcialMutanteTest {

	@InjectMocks
	private MutanteController mutanteController;

	@Mock
	private MutanteServices mutanteServices;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void checkMutant_ADNMutante_DevuelveOK() {
		// Arrange
		String[] dna = {"ATGC", "ATGC", "GCTA", "TGCA"};

		dnaRequest request = new dnaRequest(dna);
		when(mutanteServices.analizarAdn(dna)).thenReturn(true);

		// Act
		ResponseEntity<dnaRespuesta> response = mutanteController.checkMutant(request);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().isMutant());
	}

	@Test
	void checkMutant_ADNHumano_DevuelveFORBIDDEN() {

		// Arrange
		String[] dna = {"ATGC", "ATGT", "GCTA", "TGCA"};
		dnaRequest request = new dnaRequest(dna);
		when(mutanteServices.analizarAdn(dna)).thenReturn(false);

		// Act
		ResponseEntity<dnaRespuesta> response = mutanteController.checkMutant(request);

		// Assert
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		assertFalse(response.getBody().isMutant());
	}
}

class EstadisticasControllerTest {

	@InjectMocks
	private EstadisticasController estadisticasController;

	@Mock
	private EstadisticasServices estadisticasServices;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void Test_DevolverDTO() {
		// Arrange
		statsRespuesta expectedStats = new statsRespuesta(3, 5, 0.6);
		when(estadisticasServices.getStats()).thenReturn(expectedStats);

		// Act
		statsRespuesta actualStats = estadisticasController.getStats();

		// Assert
		assertEquals(expectedStats.getCountMutantDna(), actualStats.getCountMutantDna());
		assertEquals(expectedStats.getCountHumanDna(), actualStats.getCountHumanDna());
		assertEquals(expectedStats.getRatio(), actualStats.getRatio());
	}

}
