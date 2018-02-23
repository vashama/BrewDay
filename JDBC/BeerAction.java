package brew_day;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BeerAction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void createBeer(Beer beer) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"insert into beer" + 
					" (id, name, color, brewed_type, brew_note)" +
					" values(?, ?, ?, ?, ?)";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, beer.getId());
		ptmt.setString(2, beer.getName());
		ptmt.setString(3, beer.getColor());
		ptmt.setString(4, beer.getBeerType());
		ptmt.setString(5, beer.getBrewNotes());
		ptmt.execute();
	}
	
	// read Beer
	public Beer readBeer(int id) throws SQLException {
		Beer beer = null;
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"select *  from beer" + 
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		
		while(rs.next()) {
			beer = new Beer();
			beer.setId(rs.getInt("id"));
			beer.setName(rs.getString("name"));
			beer.setBeerType(rs.getString("brew_type"));
			beer.setBrewNotes(rs.getString("brew_note"));
		}
		return beer;
	}
	
	// update beer
	public void updateBeer(Beer beer) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"update beer" + 
					" set name = ?, color = ?, brewed_type = ?, brew_note = ?" +
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		
		ptmt.setString(2, beer.getName());
		ptmt.setString(3, beer.getColor());
		ptmt.setString(4, beer.getBeerType());
		ptmt.setString(5, beer.getBrewNotes());
		ptmt.setInt(1, beer.getId());
		ptmt.execute();
	}
	
	// delete beer
	public void deleteBeer(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"delete from beer" + 
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}

	
	public List<Beer> query() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("");
		
		List<Beer> beers = new ArrayList<Beer>();
		
//		while(rs.next()) {
//			System.out.println(rs.getInt(1) + " " + rs.getString(2));
//		}
		
		return beers;
	}
}
