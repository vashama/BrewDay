package brew_day;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngredientAction {
	public static void main(String args[]) throws Exception {
		Ingredient ingredient = new Ingredient();
		ingredient.setName("Hops");
		ingredient.setType(null);
		ingredient.setMount(5.00);
		
		IngredientAction ia = new IngredientAction();
		ia.createIngredient(ingredient);
	}
	
	public void createIngredient(Ingredient ingredient) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"insert into ingredient" + 
					" (name, type, mounbt, last_purchased_date)" +
					" values(?, ?, ?, ?)";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ingredient.getName());
		ptmt.setString(2, ingredient.getType());
		ptmt.setDouble(3, ingredient.getMount());
		ptmt.setDate(4, new Date(ingredient.getLastPurchasedDate().getTime()));
		ptmt.execute();
	}
	
	// read ingredient
	public Ingredient readIngredient(int id) throws SQLException {
		Ingredient ingredient = null;
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"select *  from ingredient" + 
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		
		while(rs.next()) {
			ingredient = new Ingredient();
			ingredient.setName(rs.getString("name"));
			ingredient.setType(rs.getString("type"));
			ingredient.setMount(rs.getDouble("mount"));
			ingredient.setLastPurchasedDate(rs.getDate("last_purchased_date"));
		}
		return ingredient;
	}
	
	// update ingredient
	public void updateIngredient(Ingredient ingredient) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"update ingredient" + 
					" set name = ?, type = ?, mount = ?,last_purchased_date = ?" +
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, ingredient.getName());
		ptmt.setString(2, ingredient.getType());
		ptmt.setDouble(3, ingredient.getMount());
		ptmt.setDate(4, new Date(ingredient.getLastPurchasedDate().getTime()));
		ptmt.execute();
	}
	
	// delete recipe
	public void deleteIngredient(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"delete from ingredient" + 
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}

	
	public List<Ingredient> query() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("");
		
		List<Ingredient> recipes = new ArrayList<Ingredient>();
		
//		while(rs.next()) {
//			System.out.println(rs.getInt(1) + " " + rs.getString(2));
//		}
		
		return recipes;
	}
	
}
