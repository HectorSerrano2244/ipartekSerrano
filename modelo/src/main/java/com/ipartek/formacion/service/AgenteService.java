package com.ipartek.formacion.service;

import java.sql.SQLException;
import java.util.List;

import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Multa;

public interface AgenteService {

	/**
	 * comprueba que exista el Agente en la bbdd
	 * 
	 * @param numeroPlaca
	 * @param password
	 * @return Agente si existe, null si no existe
	 */
	Agente existe(String placa, String password);

	/**
	 * Multar un Vehiculo
	 * 
	 * @param idCoche
	 * @param idAgente
	 * @param concepto
	 * @param importe
	 * @return
	 * @throws Exception si el concepto es null, el idAgente o idCoche no existen,
	 *                   importe < 0
	 */
	Multa multar(int idCoche, int idAgente, String concepto, float importe) throws Exception;

	/**
	 * 
	 * @param id
	 * @param accion
	 * @return
	 * @throws SQLException 
	 */
	Multa update(int id, String accion) throws SQLException;
	
	/**
	 * Todas las multas de un Agente
	 * 
	 * @param idAgente
	 * @return listado, si no hay ninguna vacio, no null
	 */
	List<Multa> obtenerMultas(int id, String estado);

}