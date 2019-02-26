package com.ipartek.formacion.dgt.api;

import java.util.ArrayList;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.modelo.daos.MultaDAO;
import com.ipartek.formacion.modelo.pojo.Multa;

import io.swagger.annotations.Api;

@CrossOrigin
@RestController
@Api(tags = { "AGENTE" }, produces = "application/json", description = "Gestión de multas puestas por un agente")
public class AgenteController {
	private final static Logger LOG = Logger.getLogger(VehiculoController.class);
	
	MultaDAO multaDAO;
	private ValidatorFactory factory;
	private Validator validator;
	
	public AgenteController() {
		super();
		multaDAO = MultaDAO.getInstance();
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@RequestMapping(value = { "/api/agente/{idagente}/multas" }, method = RequestMethod.GET)
	public ResponseEntity<Multa> getAll(@PathVariable int idagente) {

		ResponseEntity<Multa> response = new ResponseEntity<Multa>(HttpStatus.NOT_FOUND);
		try {
			ArrayList<Multa> multas = multaDAO.getAllByUser(idagente, "nobaja");
			if (multas != null) {
				response = new ResponseEntity<Multa>(HttpStatus.OK);
			}
		} catch (NumberFormatException e) {
			response = new ResponseEntity<Multa>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error(e);
			response = new ResponseEntity<Multa>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
