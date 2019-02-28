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
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Coche;
import com.ipartek.formacion.modelo.pojo.Multa;

public class AgenteDAO {
	
	CocheDAO daoCoche;
	private final static Logger LOG = Logger.getLogger(AgenteDAO.class);
	
	private static AgenteDAO INSTANCE = null;
	
	private static final String MULTAS_ANULADAS = "baja";
	
	private static final String SQL_LOGIN = "{call pa_login(?, ?)}";
	private static final String SQL_GETALL_BYUSER = "{call pa_multa_getByAgenteId(?,?)}";
	private static final String SQL_GETBYID = "{call pa_agente_getById(?)}";
	private static final String SQL_INSERT = "{call pa_multa_insert(?,?,?,?,?)}";
	
	private boolean isBaja = false;
	private boolean isGetById = false;
	
	// constructor privado, solo acceso por getInstance()
	private AgenteDAO() {
		super();
		daoCoche = CocheDAO.getInstance();
	}

	public synchronized static AgenteDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new AgenteDAO();
		}
		return INSTANCE;
	}

	public Agente existe(int placa, String password) {

		Agente a = null;

		try (Connection conn = ConnectionManager.getConnection(); 
			CallableStatement cs = conn.prepareCall(SQL_LOGIN);) {
			cs.setInt(1, placa);
			cs.setString(2, password);
			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) { // hemos encontrado usuario
					a = rowMapperAgente(rs);
				}
			}
			catch (Exception e) {
				LOG.error(e);
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return a;
	}
	
	public Agente getById(int id) {

		Agente a = null;

		try (Connection conn = ConnectionManager.getConnection(); 
			CallableStatement cs = conn.prepareCall(SQL_GETBYID);) {
			cs.setInt(1, id);
			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) { // hemos encontrado usuario
					a = rowMapperAgente(rs);
				}
			}
			catch (Exception e) {
				LOG.error(e);
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return a;
	}
	
	public ArrayList<Multa> obtenerMultas(long id/*, String opm*/) {

		ArrayList<Multa> multas = new ArrayList<Multa>();
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn
						.prepareCall(SQL_GETALL_BYUSER);) {
			if (MULTAS_ANULADAS.equals("nobaja")) {
				isBaja = true;
			} else {
				isBaja = false;
			}
			cs.setLong(1, id);
			cs.setString(2, "nobaja");
			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					try {
						multas.add(rowMapperMulta(rs));
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
				m.setAgente(getById(idAgente));
				m.setCoche(daoCoche.getById(idCoche));
			}
		}
		return m;
	}
	
	private Agente rowMapperAgente(ResultSet rs) throws SQLException {
		Agente a = new Agente();
		a.setId(rs.getInt("id"));
		a.setNombre(rs.getString("nombre"));
		a.setPlaca(String.valueOf(rs.getInt("placa")));
		a.setPassword(rs.getString("password"));
		return a;
	}
	
	private Multa rowMapperMulta(ResultSet rs) throws SQLException {
		Multa m = new Multa();
		Coche c = new Coche();
		//Timestamp timestampalta = rs.getTimestamp("fecha_alta");
		//m.setFechaAlta(new java.util.Date(timestampalta.getTime()));
		//Timestamp timestampalta = rs.getTimestamp("fecha_alta");
		m.setFechaAlta(rs.getDate("fecha_alta"));
		/*
		if (isBaja) {
			Timestamp timestampbaja = rs.getTimestamp("fecha_baja");
			if(timestampbaja==null) {
				m.setFechaBaja(null);
			}else {
				m.setFechaBaja(new Date(timestampbaja.getTime()));
			}
			
		}*/
		m.setId(rs.getInt("id"));
		c.setMatricula(rs.getString("matricula"));
		m.setImporte(rs.getDouble("importe"));
		m.setConcepto(rs.getString("concepto"));
		//c.setId(rs.getInt("id_coche"));
		c.setModelo(rs.getString("modelo"));
		c.setKm(rs.getInt("km"));
		m.setCoche(c);
		return m;
	}
}