package brew_day;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAction {
	public static void main(String[] args) throws Exception {
		User us = new User();
		UserAction ua = new UserAction();
		
//		us.setUserId(7);
//		us.setFirstname("John");
//		us.setPassword("55555");
		
		//ua.createUser(us);
		
		// test read
		//ua.readUser(1);
		
		// test update
//		us.setUserId(7);
//		us.setFirstname("Kevin");
//		us.setPassword("111");
//		ua.updateUser(us);
		
		// test delete
		ua.deleteUser(7);
	}
	
	// create user
	public void createUser(User user) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"insert into users" + 
					" (id, name, psword)" +
					" values(?, ?, ?)";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, user.getUserId());
		ptmt.setString(2, user.getFirstname());
		ptmt.setString(3, user.getPassword());
		ptmt.execute();
	}
	
	// read user
	public User readUser(int id) throws SQLException {
		User user = null;
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"select *  from users" + 
					" where id = ?";

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
	
	// update user
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
	
	// delete user
	public void deleteUser(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"delete from users" + 
					" where id = ?";

		// Store sql in prepared statement for later execution
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
