package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Domanda;
import model.Parametro;
import model.WorkStation;
import model.WorkStationIDMAP;


public class Dao {
	
	private WorkStationIDMAP map;
	
	
	
	public Dao(WorkStationIDMAP map) {
		this.map = map;
	}
	
	

	public List<String> getProdotti() {
		
		final String sql = "SELECT DISTINCT Prodotto FROM venditeprodotti";

		List<String> result = new LinkedList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("Prodotto"));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<Domanda> getDomande(String prodotto){
		
		final String sql = "select Data, Quantita\r\n" + 
				"from venditeprodotti\r\n" + 
				"where Prodotto = ? \r\n" + 
				"order by Data";

		List<Domanda> result = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, prodotto);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Domanda d= new Domanda(res.getDate("Data").toLocalDate(),res.getInt("Quantita"));
				
				result.add(d);
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
		
	}
	
	
	
	public List<WorkStation> getWSs(){
		final String sql = "select *\r\n" + 
				"from workstation";

		List<WorkStation> result = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				WorkStation ws = new WorkStation(res.getString("Nome"), res.getDouble("t0"),res.getDouble("c0"), res.getInt("m"));
				
				if(res.getInt("isGuasti") == 1) {
					ws.setGuasti(true);
					ws.setMf(new Parametro("Mf", res.getDouble("MfMax"), res.getDouble("MfMin"), ws));
					ws.setMr(new Parametro("Mr", res.getDouble("MrMax"), res.getDouble("MrMin"), ws));
					ws.setCf(new Parametro("Cf", res.getDouble("CfMax"), res.getDouble("CfMin"), ws));
					ws.setCr(new Parametro("Cr", res.getDouble("CrMax"), res.getDouble("CrMin"), ws));
				}
				
				if(res.getInt("isSetup") == 1) {
					ws.setSetup(true);
					ws.setNs(new Parametro("Ns", res.getDouble("NsMax"), res.getDouble("NsMin"), ws));
					ws.setTs(new Parametro("Ts", res.getDouble("TsMax"), res.getDouble("TsMin"), ws));
					ws.setCs(new Parametro("Cs", res.getDouble("CsMax"), res.getDouble("CsMin"), ws));
				}
				
				if(res.getInt("isRilavorazioni") == 1) {
					ws.setRilavorazioni(true);
					ws.setP(new Parametro("P", res.getDouble("PMax"), res.getDouble("PMin"), ws));
				}
						
				
				result.add(map.get(ws));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	public boolean  addWS(WorkStation ws) {
		final String sql = "insert into workstation(Nome, t0, m, c0, isGuasti, MfMax, MfMin, MrMax, MrMin, CfMax, CfMin, CrMax, CrMin,\r\n" + 
				" isSetup, NsMax, NsMin, TsMax, TsMin, CsMax, CsMin, isRilavorazioni, PMax, PMin)\r\n" + 
				" values(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, ws.getNome());
			st.setDouble(2, ws.getTe());
			st.setInt(3, ws.getM());
			st.setDouble(4, ws.getCe());
			
			if(ws.isGuasti()) {
				st.setInt(5, 1);
				st.setDouble(6, ws.getMf().getMax());
				st.setDouble(7, ws.getMf().getMin());
				st.setDouble(8, ws.getMr().getMax());
				st.setDouble(9, ws.getMr().getMin());
				st.setDouble(10, ws.getCf().getMax());
				st.setDouble(11, ws.getCf().getMin());
				st.setDouble(12, ws.getCr().getMax());
				st.setDouble(13, ws.getCr().getMin());
			}
			else {
				st.setInt(5, 0);
				st.setDouble(6, 0);
				st.setDouble(7, 0);
				st.setDouble(8, 0);
				st.setDouble(9, 0);
				st.setDouble(10, 0);
				st.setDouble(11, 0);
				st.setDouble(12, 0);
				st.setDouble(13, 0);
			}
			
			if(ws.isSetup()) {
				st.setInt(14, 1);
				st.setDouble(15, ws.getNs().getMax());
				st.setDouble(16, ws.getNs().getMin());
				st.setDouble(17, ws.getTs().getMax());
				st.setDouble(18, ws.getTs().getMin());
				st.setDouble(19, ws.getCs().getMax());
				st.setDouble(20, ws.getCs().getMin());
			}
			else {
				st.setInt(14, 0);
				st.setDouble(15, 0);
				st.setDouble(16, 0);
				st.setDouble(17, 0);
				st.setDouble(18, 0);
				st.setDouble(19, 0);
				st.setDouble(20, 0);		
			}
			
			
			if(ws.isRilavorazioni()) {
				st.setInt(21, 1);
				st.setDouble(22, ws.getP().getMax());
				st.setDouble(23, ws.getP().getMin());
			}
			else {
				st.setInt(21, 0);
				st.setDouble(22, 0);
				st.setDouble(23, 0);
			}
			
			int res = st.executeUpdate();
			conn.close();
			return true;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}



	public void deleteWS(WorkStation ws) {
		final String sql = "delete from workstation\r\n" + 
				"where Nome=?";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, ws.getNome());
		
			int res = st.executeUpdate();
			conn.close();
		
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
	}

}
