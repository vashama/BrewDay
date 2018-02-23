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
		

	}

	public void createRecipe(Recipe recipe) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"insert into recipes" + 
					" (id, created_date, batch_size, abv, instruction, boil_time, brew_type)" +
					" values(?, ?, ?, ?, ?, ?, ?)";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, recipe.getId());
		ptmt.setDate(2, new Date(recipe.getCreatedDate().getTime()));
		ptmt.setDouble(3, recipe.getBatchSize());
		ptmt.setDouble(4, recipe.getABV());
		ptmt.setString(5, recipe.getInstructions());
		ptmt.setTime(6, recipe.getBoilTime());
		ptmt.setString(7, recipe.getBrewType());
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
			recipe.setId(rs.getInt("id"));
			recipe.setCreatedDate(rs.getDate("created_date"));
			recipe.setBatchSize(rs.getDouble("batch_size"));
			recipe.setABV(rs.getDouble("abv"));
			recipe.setInstructions(rs.getString("Instruction"));
			recipe.setBoilTime(rs.getTime("boil_time"));
			recipe.setBrewType(rs.getString("brew_type"));
		}
		return recipe;
	}
	
	// update Recipe
	public void updateRecipe(Recipe recipe) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"update recipes" + 
					" set created_date = ?, batch_size = ?, abv = ?, instruction = ?, boil_time = ?, brew_type = ?" +
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		
		ptmt.setDate(1, new Date(recipe.getCreatedDate().getTime()));
		ptmt.setDouble(2, recipe.getBatchSize());
		ptmt.setDouble(3, recipe.getABV());
		ptmt.setString(4, recipe.getInstructions());
		ptmt.setTime(5, recipe.getBoilTime());
		ptmt.setString(6, recipe.getBrewType());
		ptmt.setInt(7, recipe.getId());
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

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		
		while(rs.next()) {
			user = new User();
			user.setUserId(rs.getInt("id"));
			user.setFirstname(rs.getString("name"));
			user.setPassword(rs.getString("psword"));
		}
		return user;
	}
	
	// update recipe
	public void updateUser(User user) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"update users" + 
					" set name = ?, psword = ?" +
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, user.getFirstname());
		ptmt.setString(2, user.getPassword());
		ptmt.setInt(3, user.getUserId());
		ptmt.execute();
	}
	
	// delete recipe
	public void deleteUser(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"delete from users" + 
					" where id = ?";

		// Store  in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}

	public List<User> query() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("");
		
		List<User> users = new ArrayList<User>();
		
//		while(rs.next()) {
//			System.out.println(rs.getInt(1) + " " + rs.getString(2));
//		}
		
		return users;
	}
}
