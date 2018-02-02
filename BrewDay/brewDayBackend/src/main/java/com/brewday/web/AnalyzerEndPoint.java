package com.brewdaybackend.web;

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
	@RequestMapping(value = "/response", method = RequestMethod.POST)
	@ResponseBody
	public String Submit(@RequestParam("username") String username, @RequestParam("password") String password) {
		//This method can have more arguments depending on the required functoinality
		System.out.println("received username: " + username);
		System.out.println("received password: " + password);
		//Temporary placeholder for the class with Postgres database linking methods
		//DBAccess dbAccess = new DBAccess()
		//Testing connection
		if(!username.equals("testUser@gmail.com") && !password.equals("testPass"))
			returnParameter = "failure";
		
		return returnParameter;
	}
}
