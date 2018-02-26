package com.brewdaybackend.javamodules;

public class Pareto {
	
	//All the data used below would be fetched from the homebrewers database
	
	//Ingredients List
	String[] ingredients = new String[] { "Hops", "Yeast", "Sugar", "Water", "Grains"};
	
	//Recipe List
	String[] recipes = new String[] { "Cauim", "Millet Beer", "Draught Beer", "Malt Beer", "Pilsner", "Amber Ale"}; 
			
	//Recipe based ingredient requirements
	Double[][] recipeRequirements = new Double[][]
	{ 
	{ 3.3, 16.3, 14.7, 69.0, 14.5 },
	{ 4.2, 17.5, 16.9, 72.1, 13.4 },
	{ 5.7, 67.4, 12.3, 81.2, 18.8 },
	{ 6.6, 23.6, 15.3, 67.7, 29.6 },
	{ 8.5, 35.7, 28.6, 76.4, 20.2 },
	{ 7.8, 24.1, 22.5, 64.3, 17.1 }
	};
	
	//Available ingredients with the homebrewer
	Double[] availableIngredients = new Double[] {3.6, 22.5, 20.0, 65.1, 20.1};
	
	
	public static String whatShouldIBrewToday()
	{
		String optimalRecipe = "Optimal Recipe Here, Testing...";
		
		//Process Optimization
		
		return optimalRecipe;
	}
	
	
	public static void main(String[] args) {
	
		String optimalRecipe;
		
		optimalRecipe = whatShouldIBrewToday();
		
		System.out.print("You should brew " + optimalRecipe + " today!");

	}

}
