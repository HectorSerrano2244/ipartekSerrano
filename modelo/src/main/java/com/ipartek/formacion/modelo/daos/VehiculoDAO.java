package com.ipartek.formacion.modelo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.cm.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Vehiculo;

public class VehiculoDAO {

	private final static Logger LOG = Logger.getLogger(VehiculoDAO.class);
	private static VehiculoDAO INSTANCE = null;

	private static final String SQL_GETMATRICULA = "{call pa_coche_getByMatricula(?)}";
	private static final String SQL_GETBYID = "SELECT id, matricula, modelo, km FROM coche WHERE id = ?";

	// constructor privado, solo acceso por getInstance()
	private VehiculoDAO() {
		super();
	}

	public synchronized static VehiculoDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new VehiculoDAO();
		}
		return INSTANCE;
	}

	private Vehiculo rowMapper(ResultSet rs) throws SQLException {
		Vehiculo v = new Vehiculo();
		v.setId(rs.getInt("id"));
		v.setMatricula(rs.getString("matricula"));
		v.setModelo(rs.getString("modelo"));
		v.setKm(rs.getInt("km"));
		return v;
	}

	public Vehiculo getByMatricula(String matricula) {
		Vehiculo c = null;
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_GETMATRICULA);) {

			cs.setString(1, matricula);

			try (ResultSet rs = cs.executeQuery()) {
				try {
					while (rs.next()) {
						c = rowMapper(rs);
					}
				} catch (Exception e) {
					LOG.warn(e);
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}
		return c;
	}
	
	public Vehiculo getById(int id) {
		Vehiculo c = null;
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_GETBYID);) {

			cs.setInt(1, id);

			try (ResultSet rs = cs.executeQuery()) {
				try {
					while (rs.next()) {
						c = rowMapper(rs);
					}
				} catch (Exception e) {
					LOG.warn(e);
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}
		return c;
	}
}