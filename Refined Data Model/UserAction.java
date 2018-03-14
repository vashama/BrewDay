package brew_day;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class UserAction {
	public static void main(String[] args) throws Exception {
		User us = new User();
		UserAction ua = new UserAction();
		
		//us.setUserId(7);
		us.setName("John");
		us.setEmail("john@123.com");
		us.setPassword("55555");
		
		ua.createUser(us);
		
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
		File path = null;
		
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"insert into users" + 
					" (name, email, psword, photo)" +
					" values(?, ?, ?, ?)";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//ptmt.setInt(1, user.getUserId());
		ptmt.setString(1, user.getName());
		ptmt.setString(2, user.getEmail());
		ptmt.setString(3, user.getPassword());
		
		FileInputStream fis = new FileInputStream(path);
		ptmt.setBinaryStream(4, fis, fis.available());
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
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("psword"));
			user.setPhoto(rs.getBytes("photo"));
		}
		return user;
	}
	
	// update user
	public void updateUser(User user) throws Exception {
		File path = null;
		
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"update users" + 
					" set name = ?, psword = ?, photo = ?" +
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, user.getName());
		ptmt.setString(2, user.getPassword());
		
		FileInputStream fis = new FileInputStream(path);
		ptmt.setBinaryStream(3, fis, fis.available());
		
		ptmt.setInt(4, user.getUserId());
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
