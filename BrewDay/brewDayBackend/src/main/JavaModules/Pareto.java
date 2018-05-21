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
	static double batchSize = 100.00;
	private static Double ingredientBatchSum;
    static Statement sqlStatement = null;
	private static ArrayList<Double> recipeIngredientsPercentage;
	private static Double ingredientQty;
	private static ArrayList<Double> quantityRequired = new ArrayList<Double>();

	//Constructor to connect to Postgres Database
	public Pareto() throws SQLException, ClassNotFoundException 
	{
	      Connection conn = null;
          Class.forName("org.postgresql.Driver");
          conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

	      sqlStatement = conn.createStatement();
	      ResultSet rs1 = sqlStatement.executeQuery( "SELECT * FROM ingredients;" );
	      
	      
	      
	      while(rs1.next())
	      {
	          ingredientsArrayList.add(rs1.getDouble("extrahops"));
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
	
	
	
	
	public static String whatShouldIBrewToday() throws SQLException
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
		
		ArrayList positions=new ArrayList();
var inPut = 0;
    for(int i=0;i<sufficientIngredients.length;i++)
    {
        if(inPut.equals(sufficientIngredients[i]))
        {
            positions.add(i);
        }
    }
		Random random = new Random();

		int bestRecipeIndex = positions.get(random.nextInt(positions.size()))
		
		for( int i = 0; i < nestedIngredientsArrayList.get(bestRecipeIndex).size(); i++)
			ingredientBatchSum = (nestedIngredientsArrayList.get(bestRecipeIndex).get(i));

		if(ingredientBatchSum > batchSize)
		{
			ResultSet rs3 = sqlStatement.executeQuery( "SELECT * FROM recipeIngredientWeightage WHERE recipe = " + recipes.get(bestRecipeIndex) + ";");
		    while(rs3.next())
		    {     
		    	recipeIngredientsPercentage.add(rs3.getDouble("hops"));
		    	recipeIngredientsPercentage.add(rs3.getDouble("yeast"));
		    	recipeIngredientsPercentage.add(rs3.getDouble("sugar"));
		    	recipeIngredientsPercentage.add(rs3.getDouble("water"));
		    	recipeIngredientsPercentage.add(rs3.getDouble("grains"));
		    }
		    
		    for(int ingredient = 0; ingredient < recipeIngredientsPercentage.size(); ingredient++)
		    {
		    	ingredientQty = recipeIngredientsPercentage.get(ingredient);
		        quantityRequired.add(ingredientQty);
		    }
		    
		}
		
		return recipes.get(bestRecipeIndex);
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
		
		String optimalRecipe;
		
		new Pareto();

		optimalRecipe = whatShouldIBrewToday();
		
		System.out.print("You should brew " + optimalRecipe + " today!");

	}

}
