package com.brewdaybackend.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/post")
public class AnalyzerEndPoint {
	String returnParameter = "success";
	private String dbPassword;
	private String dbUsername;
	private String dbEmail;
	static Statement sqlStatement = null;

	@RequestMapping(value = "/response", method = RequestMethod.POST)
	@ResponseBody
	public String Submit(@RequestParam("username") String username, @RequestParam("password") String password)
			throws SQLException, ClassNotFoundException {

		Connection conn = null;
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

		sqlStatement = conn.createStatement();
		ResultSet rs1 = sqlStatement.executeQuery("SELECT password FROM users WHERE username = " + username + ";");

		while (rs1.next()) {

			dbPassword = rs1.getString("username");
			dbUsername = rs1.getString("password");
			dbEmail = rs1.getString("email");

		}

		if (!username.equals(dbUsername) && !password.equals(dbPassword))
			returnParameter = "failure";
		else
			returnParameter = "success";
		return returnParameter;
	}
}
