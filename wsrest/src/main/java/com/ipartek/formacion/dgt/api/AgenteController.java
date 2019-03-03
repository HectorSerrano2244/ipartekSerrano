package com.ipartek.formacion.dgt.api;

import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.modelo.daos.MultaDAO;
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Multa;
import com.ipartek.formacion.service.AgenteService;
import com.ipartek.formacion.service.impl.AgenteServiceImpl;

import io.swagger.annotations.Api;

@CrossOrigin
@RestController
@Api(tags = { "AGENTE" }, produces = "application/json", description = "Gestión de multas puestas por un agente")
public class AgenteController {
	private final static Logger LOG = Logger.getLogger(VehiculoController.class);

	MultaDAO multaDAO;
	private ValidatorFactory factory;
	private Validator validator;
	private AgenteService agenteService;

	public AgenteController() {
		super();
		multaDAO = MultaDAO.getInstance();
		agenteService = AgenteServiceImpl.getInstance();
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@RequestMapping(value = { "/api/agente/{idagente}/multas/{estado}" }, method = RequestMethod.GET)
	public ResponseEntity<List<Multa>> obtenerMultas(@PathVariable int idagente, @PathVariable String estado) {

		ResponseEntity<List<Multa>> response = new ResponseEntity<List<Multa>>(HttpStatus.NOT_FOUND);
		try {
			List<Multa> multas = agenteService.obtenerMultas(idagente, estado);
			if (multas != null) {
				response = new ResponseEntity<List<Multa>>(multas, HttpStatus.OK);
			}
		} catch (NumberFormatException e) {
			response = new ResponseEntity<List<Multa>>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error(e);
			response = new ResponseEntity<List<Multa>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(value = { "/api/agente/login/{placa}/{password}" }, method = RequestMethod.GET)
	public ResponseEntity<Agente> login(@PathVariable String placa, @PathVariable String password) {

		ResponseEntity<Agente> response = new ResponseEntity<Agente>(HttpStatus.FORBIDDEN);
		try {
			Agente agente = agenteService.existe(placa, password);
			if (agente != null) {
				response = new ResponseEntity<Agente>(agente, HttpStatus.OK);
			}
		} catch (Exception e) {
			LOG.error(e);
			response = new ResponseEntity<Agente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	@RequestMapping(value = { "/api/agente/multa" }, method = RequestMethod.POST)
	public ResponseEntity<Multa> multar(@RequestBody Multa multa) {

		ResponseEntity<Multa> response = new ResponseEntity<Multa>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			multa = agenteService.multar(multa.getCoche().getId(), multa.getAgente().getId(), multa.getConcepto(), (float)multa.getImporte());
			if (multa != null) {
				response = new ResponseEntity<Multa>(multa, HttpStatus.CREATED);
			}
		} catch (NumberFormatException e) {
			response = new ResponseEntity<Multa>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error(e);
		}
		return response;
	}
	
	@RequestMapping(value = { "/api/agente/multa/{idmulta}/{accion}" }, method = RequestMethod.PATCH)
	public ResponseEntity<Multa> update(@PathVariable int idmulta, @PathVariable String accion, @RequestBody Multa multa) {

		multa.setId(idmulta);
		ResponseEntity<Multa> response = new ResponseEntity<Multa>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			multa = agenteService.update(idmulta, accion);
			if (multa != null) {
				response = new ResponseEntity<Multa>(multa, HttpStatus.OK);
			}
		} catch (NumberFormatException e) {
			response = new ResponseEntity<Multa>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error(e);
		}
		return response;
	}
}