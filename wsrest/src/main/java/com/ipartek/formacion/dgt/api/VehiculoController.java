package com.ipartek.formacion.dgt.api;

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

import com.ipartek.formacion.modelo.daos.VehiculoDAO;
import com.ipartek.formacion.modelo.pojo.Vehiculo;

import io.swagger.annotations.Api;

@CrossOrigin
@RestController
@Api(tags = { "VEHICULO" }, produces = "application/json", description = "Gestión de vehiculos")
public class VehiculoController {

	private final static Logger LOG = Logger.getLogger(VehiculoController.class);
	
	VehiculoDAO vehiculoDAO;
	private ValidatorFactory factory;
	private Validator validator;

	public VehiculoController() {
		super();
		vehiculoDAO = VehiculoDAO.getInstance();
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	/*
	 * @RequestMapping( value= {"/api/vehiculo"}, method = RequestMethod.GET) public
	 * ArrayList<Coche> listar(){
	 * 
	 * return cocheDAO.getAll(); }
	 */

	@RequestMapping(value = { "/api/vehiculo/{matricula}" }, method = RequestMethod.GET)
	public ResponseEntity<Vehiculo> detalle(@PathVariable String matricula) {

		ResponseEntity<Vehiculo> response = new ResponseEntity<Vehiculo>(HttpStatus.NOT_FOUND);
		try {
			Vehiculo vehiculo = vehiculoDAO.getByMatricula(matricula);
			if (vehiculo != null) {
				response = new ResponseEntity<Vehiculo>(vehiculo, HttpStatus.OK);
			}
		} catch (NumberFormatException e) {
			response = new ResponseEntity<Vehiculo>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error(e);
			response = new ResponseEntity<Vehiculo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}