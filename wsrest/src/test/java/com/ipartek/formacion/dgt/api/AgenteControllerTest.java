package com.ipartek.formacion.dgt.api;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.ipartek.formacion.modelo.pojo.Agente;

public class AgenteControllerTest {

	@Test
	public void login() {
		
		
		AgenteController aController = new AgenteController();
		
		ResponseEntity<Agente> response = aController.login("987987", "987987");		
		assertEquals(200, response.getStatusCode().value());
		assertNotNull("El Agente tiene que venir en el body",response.getBody());
		
		
		response = aController.login("9", "a");
		assertEquals(403, response.getStatusCode().value());
		assertNull(response.getBody());
		
		
		response = aController.login("987987", "9");
		assertEquals(403, response.getStatusCode().value());
		assertNull(response.getBody());
		
		
		
	}

}