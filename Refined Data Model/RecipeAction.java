package brew_day;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecipeAction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Recipe recipe = new Recipe();
		recipe.setName("Black Beer");
		recipe.setBatchSize("2 Litter");
		recipe.setABV(5.00);
		recipe.setIBU(1.25);
	}

	public void createRecipe(Recipe recipe) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"insert into recipes" + 
					" (name, created_date, batch_size, abv, ibu, instruction, boil_time, brew_type)" +
					" values(?, ?, ?, ?, ?, ?, ?, ?)";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, recipe.getBeerName());
		ptmt.setDate(2, new Date(recipe.getCreatedDate().getTime()));
		ptmt.setString(3, recipe.getBatchSize());
		ptmt.setDouble(4, recipe.getABV());
		ptmt.setDouble(5, recipe.getIBU());
		ptmt.setString(6, recipe.getInstructions());
		ptmt.setTime(7, recipe.getBoilTime());
		ptmt.setString(8, recipe.getBrewType());
		//ptmt.setString(9, recipe.getBeerName());
		ptmt.execute();
	}
	
	// read recipe
	public Recipe readRecipe(int id) throws SQLException {
		Recipe recipe = null;
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"select *  from recipes" + 
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		
		while(rs.next()) {
			recipe = new Recipe();
			recipe.setName("name");
			recipe.setCreatedDate(rs.getDate("created_date"));
			recipe.setBatchSize(rs.getString("batch_size"));
			recipe.setABV(rs.getDouble("abv"));
			recipe.setIBU(rs.getDouble("ibu"));
			recipe.setInstructions(rs.getString("Instruction"));
			recipe.setBoilTime(rs.getTime("boil_time"));
			recipe.setBrewType(rs.getString("brew_type"));
		}
		return recipe;
	}
	
	// update user
	public void updateRecipe(Recipe recipe) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"update recipes" + 
					" set name = ?, created_date = ?, batch_size = ?, abv = ?, ibu = ?, instruction = ?, boil_time = ?, brew_type = ?" +
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, recipe.getName());
		ptmt.setDate(2, new Date(recipe.getCreatedDate().getTime()));
		ptmt.setString(3, recipe.getBatchSize());
		ptmt.setDouble(4, recipe.getABV());
		ptmt.setDouble(5, recipe.getIBU());
		ptmt.setString(6, recipe.getInstructions());
		ptmt.setTime(7, recipe.getBoilTime());
		ptmt.setString(8, recipe.getBrewType());
		ptmt.setInt(9, recipe.getId());
		ptmt.execute();
	}
	
	// delete recipe
	public void deleteRecipe(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"delete from recipes" + 
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}

	
	public List<Recipe> query() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("");
		
		List<Recipe> recipes = new ArrayList<Recipe>();
		
//		while(rs.next()) {
//			System.out.println(rs.getInt(1) + " " + rs.getString(2));
//		}
		
		return recipes;
	}
}
