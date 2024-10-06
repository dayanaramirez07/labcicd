package com.udea.labcicd;

import com.fasterxml.jackson.databind.JsonNode;
import com.udea.labcicd.controller.DataController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LabcicdApplicationTests {

	@Autowired
	DataController dataController;

	@Test
	void health() {
		assertEquals("Aplicación funcionando correctamente", dataController.healthCheck());
	}

	@Test
	void version() {
		assertEquals("La versión actual es 1.0.0", dataController.version());
	}

	@Test
	void nationsLength() {
		Integer nationsLength = dataController.getRandomNations().size();
		assertEquals(10, nationsLength);
	}

	@Test
	void currenciesLength() {
		Integer currenciesLength = dataController.getRandomCurrencies().size();
		assertEquals(20, currenciesLength);
	}

	@Test
	public void testRandomCurrenciesCodeFormat() {
		DataController controller = new DataController();
		JsonNode response = controller.getRandomCurrencies();

		for (int i = 0; i < response.size(); i++) {
			JsonNode currency = response.get(i);
			String code = currency.get("code").asText();
			assertTrue(code.matches("[A-Z]{3}"));
		}
	}

	@Test
	public void testRandomNationsPerformance() {
		DataController controller = new DataController();
		long startTime = System.currentTimeMillis();
		controller.getRandomNations();
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		assertTrue(executionTime < 2000);
	}

	@Test
	void aviationLength() {
		Integer aviationLength = dataController.getRandomAviation().size();
		assertEquals(20, aviationLength);
	}

}