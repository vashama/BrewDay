package com.brewdaybackend.javamodules;


import java.util.*;
import org.apache.commons.lang3.ArrayUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pareto {
	
	static ArrayList<Double> ingredientsArrayList = new ArrayList<Double>();
	static ArrayList<ArrayList<Double>> nestedIngredientsArrayList = new ArrayList<ArrayList<Double>>();
	static ArrayList<String> ingredients = new ArrayList<String>();
	static ArrayList<String> recipes = new ArrayList<String>();
	static ArrayList<String> brewEquipment = new ArrayList<String>();
	static ArrayList<Double> availableIngredients = new ArrayList<Double>();
	
	//Constructor to connect to Postgres Database
	public Pareto() throws SQLException, ClassNotFoundException 
	{
	      Connection conn = null;
	      Statement sqlStatement = null;
          Class.forName("org.postgresql.Driver");
          conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

	      sqlStatement = conn.createStatement();
	      ResultSet rs1 = sqlStatement.executeQuery( "SELECT * FROM ingredients;" );
	      
	      
	      
	      while(rs1.next())
	      {
	          ingredientsArrayList.add(rs1.getDouble("hops"));
	          ingredientsArrayList.add(rs1.getDouble("yeast"));
	          ingredientsArrayList.add(rs1.getDouble("sugar"));
	          ingredientsArrayList.add(rs1.getDouble("water"));
	          ingredientsArrayList.add(rs1.getDouble("grains"));
		      nestedIngredientsArrayList.add((ArrayList<Double>) ingredientsArrayList.clone());
		      ingredientsArrayList.clear();
	      }
	      
	      ResultSet rs2 = sqlStatement.executeQuery( "SELECT * FROM availableingredients;" );
	      while(rs2.next())
	      {
		      
		      availableIngredients.add(rs2.getDouble("hops"));
		      availableIngredients.add(rs2.getDouble("yeast"));
	          availableIngredients.add(rs2.getDouble("sugar"));
	          availableIngredients.add(rs2.getDouble("water"));
	          availableIngredients.add(rs2.getDouble("grains"));
	      }
	      ResultSet rs3 = sqlStatement.executeQuery( "SELECT * FROM recipelist;" );
	      while(rs3.next())
	      {
	          
	          recipes.add(rs3.getString("recipes"));
	      }
	}
	
	
	
	
	public static String whatShouldIBrewToday()
	{
		String optimalRecipe = null;
		ArrayList<Double> ingredientBuffer = new ArrayList<Double>();
		ArrayList<ArrayList> ingredientBufferCollection = new ArrayList<ArrayList>();
		
		for (double ingredientValue: availableIngredients) {
			
			for(ArrayList<Double> individualRecipeRequirements: nestedIngredientsArrayList) {
				
				ingredientBuffer.add(individualRecipeRequirements.get(availableIngredients.indexOf(ingredientValue)));
			}
			
			Collections.sort(ingredientBuffer);

			int i = 0;
			while(i<ingredientBuffer.size()) {
			    if (ingredientBuffer.get(i) > ingredientValue) {
			    	ingredientBuffer.set(i, 999.99); 
			    }
			        i++;
			}
			
			ingredientBufferCollection.add((ArrayList<Double>) ingredientBuffer.clone());
			ingredientBuffer.clear();


		}
				
		Integer sufficientIngredients[] = new Integer[] {0, 0, 0, 0, 0};
		
		for(ArrayList<Double> bufferBreakup: ingredientBufferCollection) {
			
			
			
			for(int i = 0; i < nestedIngredientsArrayList.get(0).size() - 1; i++)
			if(bufferBreakup.get(i) == 999.99) {
				
				sufficientIngredients[i] = 1;
				
			}
			
		}
		
		int bestRecipeIndex = Arrays.asList(sufficientIngredients).lastIndexOf(0);
				
		return recipes.get(bestRecipeIndex);
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
		
		String optimalRecipe;
		
		new Pareto();

		optimalRecipe = whatShouldIBrewToday();
		
		System.out.print("You should brew " + optimalRecipe + " today!");

	}

}
