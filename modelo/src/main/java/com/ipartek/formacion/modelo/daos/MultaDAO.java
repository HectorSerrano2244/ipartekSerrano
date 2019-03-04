package com.ipartek.formacion.modelo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.cm.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Vehiculo;
import com.ipartek.formacion.modelo.pojo.Multa;

public class MultaDAO {
	private final static Logger LOG = Logger.getLogger(MultaDAO.class);
	private static MultaDAO INSTANCE = null;
	private boolean isGetById = false;
	private boolean isBaja = false;
	
	AgenteDAO daoAgente;
	VehiculoDAO daoVehiculo;
	
	private static final String MULTAS_ANULADAS = "baja";
	//private static final String MULTAS_ACTIVAS = "activas";

	private static final String SQL_GETBYID = "{call pa_multa_getById(?)}";
	private static final String SQL_GETALL_BYUSER = "{call pa_multa_getByAgenteId(?,?)}";
	private static final String SQL_INSERT = "{call pa_multa_insert(?,?,?,?,?)}";
	private static final String SQL_UPDATE = "{call pa_multa_update(?,?)}";

	// constructor privado, solo acceso por getInstance()
	private MultaDAO() {
		super();
		daoAgente = daoAgente.getInstance();
		daoVehiculo = daoVehiculo.getInstance();
	}

	public synchronized static MultaDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new MultaDAO();
		}
		return INSTANCE;
	}

	public Multa getById(long id, String opm) {

		Multa m = null;

		isGetById = true;

		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_GETBYID);) {
			cs.setLong(1, id);
			
			if ("baja".equals(opm)) {
				isBaja = true;
			} else {
				isBaja = false;
			}

			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					m = rowMapper(rs);
				}
				LOG.debug("Multa encontrada");
			}
		} catch (Exception e) {			
			LOG.error(e);
		}
		return m;
	}

	public ArrayList<Multa> getAllByUser(long id, String opm) {

		ArrayList<Multa> multas = new ArrayList<Multa>();
		isGetById = false;
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn
						.prepareCall(SQL_GETALL_BYUSER);) {
			if (MULTAS_ANULADAS.equals(opm)) {
				isBaja = true;
			} else {
				isBaja = false;
			}
			cs.setLong(1, id);
			cs.setString(2, opm);
			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					try {
						multas.add(rowMapper(rs));
					} catch (Exception e) {						
						LOG.error(e);
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}

		return multas;
	}

	public boolean insert(Multa m) throws SQLException {

		boolean resul = false;
		isGetById = false;
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_INSERT);) {

			cs.setDouble(1, m.getImporte());
			cs.setString(2, m.getConcepto());
			cs.setLong(3, m.getVehiculo().getId());
			cs.setLong(4, m.getAgente().getId());
			cs.registerOutParameter(5, Types.INTEGER);
			int affectedRows = cs.executeUpdate();
			if (affectedRows == 1) {
				m.setId(cs.getInt(5));
				resul = true;
			}

		}
		return resul;

	}
	
	public Multa multar(int idCoche, int idAgente, String concepto, float importe) throws SQLException {
		//isGetById = false;
		Multa m = new Multa();
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_INSERT);) {
			cs.setDouble(1, importe);
			cs.setString(2, concepto);
			cs.setLong(3, idCoche);
			cs.setLong(4, idAgente);
			cs.registerOutParameter(5, Types.INTEGER);
			if (cs.executeUpdate() == 1) {
				m.setId(cs.getInt(5));
				m.setImporte((double)importe);
				m.setConcepto(concepto);
				m.setAgente(daoAgente.getById(idAgente));
				m.setVehiculo(daoVehiculo.getById(idCoche));
			}
		}
		return m;
	}

	public Multa update(int id, String accion) throws SQLException {

		Multa m = new Multa();
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_UPDATE);) {

			cs.setInt(1, id);
			cs.setString(2, accion);
			if (cs.executeUpdate() == 1) {
				if ("recuperar".equals(accion)) {
					m.setFechaModificacion(new Date());
					m.setFechaBaja(null);
				}
				else {
					m.setFechaBaja(new Date());
				}
			}
		}
		return m;

	}

	private Multa rowMapper(ResultSet rs) throws SQLException {
		Multa m = new Multa();
		Vehiculo v = new Vehiculo();
		Timestamp timestampalta = rs.getTimestamp("fecha_alta");
		m.setFechaAlta(new java.util.Date(timestampalta.getTime()));
		if (isBaja) {
			Timestamp timestampbaja = rs.getTimestamp("fecha_baja");
			if(timestampbaja==null) {
				m.setFechaBaja(null);
			}else {
				m.setFechaBaja(new java.util.Date(timestampbaja.getTime()));
			}
			
		}
		m.setId(rs.getInt("id"));
		v.setMatricula(rs.getString("matricula"));
		if (isGetById) {
			m.setImporte(rs.getDouble("importe"));
			m.setConcepto(rs.getString("concepto"));
			v.setId(rs.getInt("id_coche"));
			v.setModelo(rs.getString("modelo"));
			v.setKm(rs.getInt("km"));
		}
		m.setVehiculo(v);
		return m;
	}

}
