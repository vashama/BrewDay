package com.aircrash.web;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aircrash.ontology.QueryOntology;

@Controller
@RequestMapping("/post")
public class AnalyzerEndPoint {

	@RequestMapping(value = "/response", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<String> Submit(@RequestParam("query") String query) {
		QueryOntology queryOnto = new QueryOntology();
		ArrayList<String> resultCollection = queryOnto.queryRunner(query);
		System.out.println("Recieved Query: " + query);
		/*
		for(String entry : resultCollection){
	        System.out.println(entry + "\n");
	    }
		*/
		return resultCollection;
	}
}