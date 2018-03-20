package com.brewdaybackend.javamodules;

import java.util.*;
import org.apache.commons.lang3.ArrayUtils;
import java.sql.Connection;
import java.sql.DriverManager;

public class Pareto {
	
	//Constructor to connect to Postgres Database
	public Pareto()
	{
	      Connection conn = null;
	      try 
	      {
	         Class.forName("org.postgresql.Driver");
	         conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "postgres");
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	      }
	}
	
	//All the data used below would be fetched from the homebrewers database
	
	//Ingredients List
	static String[] ingredients = new String[] { "Hops", "Yeast", "Sugar", "Water", "Grains"};
	
	//Recipe List
	static String[] recipes = new String[] { "Cauim", "Millet Beer", "Draught Beer", "Malt Beer", "Pilsner", "Amber Ale"};
	
	//Brewing Equipment List
	static String[] recipes = new String[] { "Grain Mill", "Water Filter", "Hop Socks"};
			
	//Recipe based ingredient requirements
	static Double[][] recipeRequirements = new Double[][]
	{ 
	{ 3.3, 16.3, 14.7, 69.0, 14.5 },
	{ 4.2, 17.5, 16.9, 72.1, 13.4 },
	{ 5.7, 67.4, 12.3, 81.2, 18.8 },
	{ 6.6, 23.6, 15.3, 67.7, 29.6 },
	{ 8.5, 35.7, 28.6, 76.4, 20.2 },
	{ 7.8, 24.1, 22.5, 64.3, 17.1 }
	};
	
	//Data structure to store ordered ingredients based on the availability
	static double optimalityMatrix[][] = new double[recipeRequirements.length][recipeRequirements[0].length];
	
	//Available ingredients with the homebrewer
	static Double[] availableIngredients = new Double[] {3.6, 22.5, 20.0, 65.1, 20.1};
	
	
	public static String whatShouldIBrewToday()
	{
		String optimalRecipe = null;
		ArrayList<Double> ingredientBuffer = new ArrayList<Double>();
		ArrayList<ArrayList> ingredientBufferCollection = new ArrayList<ArrayList>();
		
		for (double ingredientValue: availableIngredients) {
			
			for(Double[] individualRecipeRequirements: recipeRequirements) {
				
				ingredientBuffer.add(individualRecipeRequirements[ArrayUtils.indexOf(availableIngredients, ingredientValue)]);
			}
			
			Collections.sort(ingredientBuffer);

			int i = 0;
			while(i<ingredientBuffer.size()) {
			    if (ingredientBuffer.get(i) > ingredientValue) {
			    	ingredientBuffer.set(i, 999.99); 
			    }
			        i++;
			}
			
			ingredientBufferCollection.add((ArrayList) ingredientBuffer.clone());
			ingredientBuffer.clear();


		}
				
		Integer sufficientIngredients[] = new Integer[] {0, 0, 0, 0, 0};
		
		for(ArrayList<Double> bufferBreakup: ingredientBufferCollection) {
			
			
			
			for(int i = 0; i < recipeRequirements[0].length; i++)
			if(bufferBreakup.get(i) == 999.99) {
				
				sufficientIngredients[i] = 1;
				
			}
			
		}
		
		int bestRecipeIndex = Arrays.asList(sufficientIngredients).lastIndexOf(0);
				
		return recipes[bestRecipeIndex];
	}
	
	
	public static void main(String[] args) {
	
		String optimalRecipe;
		
		optimalRecipe = whatShouldIBrewToday();
		
		System.out.print("You should brew " + optimalRecipe + " today!");

	}

}
