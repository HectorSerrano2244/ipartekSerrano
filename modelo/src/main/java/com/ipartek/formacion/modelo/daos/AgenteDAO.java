package com.ipartek.formacion.modelo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.cm.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Coche;
import com.mysql.jdbc.PreparedStatement;

public class AgenteDAO {

	private final static Logger LOG = Logger.getLogger(AgenteDAO.class);
	private static AgenteDAO INSTANCE = null;

	private static final String SQL_GETAGENTE = "";

	// constructor privado, solo acceso por getInstance()
	private AgenteDAO() {
		super();
	}

	public synchronized static AgenteDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new AgenteDAO();
		}
		return INSTANCE;
	}

	private Agente rowMapper(ResultSet rs) throws SQLException {
		Agente a = new Agente();
		return a;
	}

	public Coche getByMatricula(String matricula) {
		Coche c = null;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GETAGENTE);) {

			pst.setString(1, matricula);

			try (ResultSet rs = pst.executeQuery()) {
				try {
					while (rs.next()) {
						a = rowMapper(rs);
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