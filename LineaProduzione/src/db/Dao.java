package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Domanda;


public class Dao {
	
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

}
