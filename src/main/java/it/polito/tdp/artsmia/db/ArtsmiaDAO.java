package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.Adiacenze;
import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Artisti;
import it.polito.tdp.artsmia.model.Connessi;
import it.polito.tdp.artsmia.model.Exhibition;

public class ArtsmiaDAO {

	public List<ArtObject> listObjects() {
		
		String sql = "SELECT * from objects";
		List<ArtObject> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				ArtObject artObj = new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				
				result.add(artObj);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Exhibition> listExhibitions() {
		
		String sql = "SELECT * from exhibitions";
		List<Exhibition> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Exhibition exObj = new Exhibition(res.getInt("exhibition_id"), res.getString("exhibition_department"), res.getString("exhibition_title"), 
						res.getInt("begin"), res.getInt("end"));
				
				result.add(exObj);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
public List<String> getRuolo() {
		
		String sql = "SELECT  distinct a.role "
				+ "FROM authorship a";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				result.add(res.getString("a.role"));
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

public List<Artisti> getVertici(String s,Map<Integer, Artisti> idMap) {
	
	String sql = "SELECT distinct art.artist_id as id ,art.name as name "
			+ "FROM authorship a, artists art "
			+ "WHERE a.role = ? "
			+ "AND a.artist_id = art.artist_id";
	List<Artisti> result = new ArrayList<>();
	Connection conn = DBConnect.getConnection();

	try {
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, s);
		ResultSet res = st.executeQuery();
		while (res.next()) {

			Artisti a = new Artisti(res.getInt("id"), res.getString("name"));
			idMap.put(a.getId(), a);
			
			result.add(a);
		}
		conn.close();
		return result;
		
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}
	
public List<Adiacenze> getArchi(String s, Map<Integer, Artisti> idMap) {
	
	String sql = "SELECT DISTINCT a1.artist_id AS art1 ,a2.artist_id  AS art2, COUNT(*) AS peso "
			+ "FROM authorship a1,authorship a2, exhibition_objects eo1, exhibition_objects eo2 "
			+ "WHERE a1.role = ? "
			+ "AND a2.role = ? "
			+ "AND a1.artist_id > a2.artist_id "
			+ "AND eo1.object_id = a1.object_id "
			+ "AND eo2.object_id = a2.object_id "
			+ "AND eo1.exhibition_id = eo2.exhibition_id "
			+ "GROUP BY art1, art2";
	List<Adiacenze> result = new ArrayList<>();
	Connection conn = DBConnect.getConnection();

	try {
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, s);
		st.setString(2, s);
		ResultSet res = st.executeQuery();
		while (res.next()) {

			Adiacenze a = new Adiacenze(idMap.get(res.getInt("art1")),idMap.get(res.getInt("art2")), res.getInt("peso"));
			
			result.add(a);
		}
		conn.close();
		return result;
		
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}
public List<Connessi> getConnessi(String s) {
	
	String sql = "SELECT DISTINCT a1.artist_id AS art1 ,a2.artist_id  AS art2, COUNT(*) AS peso "
			+ "FROM authorship a1,authorship a2, exhibition_objects eo1, exhibition_objects eo2 "
			+ "WHERE a1.role = ? "
			+ "AND a2.role = ? "
			+ "AND a1.artist_id > a2.artist_id "
			+ "AND eo1.object_id = a1.object_id "
			+ "AND eo2.object_id = a2.object_id "
			+ "AND eo1.exhibition_id = eo2.exhibition_id "
			+ "GROUP BY art1, art2";
	List<Connessi> result = new ArrayList<>();
	Connection conn = DBConnect.getConnection();

	try {
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, s);
		st.setString(2, s);
		ResultSet res = st.executeQuery();
		while (res.next()) {

			Connessi a = new Connessi(res.getInt("art1"),res.getInt("art2"), res.getInt("peso"));
			
			result.add(a);
		}
		conn.close();
		return result;
		
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}
	
}
