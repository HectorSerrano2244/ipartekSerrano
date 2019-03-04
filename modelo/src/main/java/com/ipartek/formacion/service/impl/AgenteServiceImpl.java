package com.ipartek.formacion.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ipartek.formacion.modelo.daos.AgenteDAO;
import com.ipartek.formacion.modelo.daos.MultaDAO;
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Multa;
import com.ipartek.formacion.service.AgenteService;
import com.ipartek.formacion.service.Singleton;

public class AgenteServiceImpl implements AgenteService, Singleton {

	private AgenteDAO daoAgente;
	private MultaDAO daoMulta;
	private static AgenteServiceImpl INSTANCE = null;
	
	private AgenteServiceImpl() {
		super();		
		daoAgente = AgenteDAO.getInstance();
		daoMulta = MultaDAO.getInstance();
	}

	public static synchronized AgenteServiceImpl getInstance() {
        if (INSTANCE == null) {
        	INSTANCE = new AgenteServiceImpl();
        } 
        return INSTANCE;
    }

	@Override
	public Agente existe(String placa, String password) {
		return daoAgente.existe(Integer.parseInt(placa), password);
	}

	@Override
	public Multa multar(int idCoche, int idAgente, String concepto, float importe) throws Exception {
		return daoMulta.multar(idCoche, idAgente, concepto, importe);
	}

	@Override
	public List<Multa> obtenerMultas(int id, String estado) {
		return daoAgente.obtenerMultas(id, estado);
	}

	@Override
	public Multa update(int id, String accion) throws SQLException {
		return daoMulta.update(id, accion);
	}
	
	


}