package com.brewdaybackend.javamodules;

public class BrewToday {

    public static void main(String[] args) throws Exception {
    	
        int ingredientMass[] = {
            69,
            24,
            51,
            13
        };
        
        int ingredientPercentage[] = {
            60,
            34,
            1,
            5
        };
        
        String[] recipeList = {
            "recipe1",
            "recipe2",
            "recipe3",
            "recipe4"
        };
        
        int maxMaterialsForBatch = 80;
        
        //The maxWeight would correspond to the recipeList above
        //int maxWeight = calculateOptimalRecipe(ingredientMass, ingredientPercentage, maxMaterialsForBatch);
        //System.out.println(maxWeight);
        //Sysout recipe
    }

    
    
    public static int calculateOptimalRecipe(int ingredientMass[], int ingredientPercentage[], int maxMaterialsForBatch) {
    	
        int numberOfIngredients = ingredientMass.length;
        
        int[][] weightMatrix = new int[numberOfIngredients + 1][maxMaterialsForBatch + 1];
        
        for (int ingredient = 1; ingredient <= numberOfIngredients; ingredient++) {
        	
            for (int stepIncrement = 0; stepIncrement < maxMaterialsForBatch; stepIncrement++) {
            	
            	//check if still within maxWeight limit
                if (ingredientPercentage[ingredient - 1] <= stepIncrement) {
                	
                	//Check if addition would actually increase gross value
                    weightMatrix[ingredient][stepIncrement] = Math.max(ingredientMass[ingredient - 1] +
                        weightMatrix[ingredient - 1][stepIncrement - ingredientPercentage[ingredient - 1]],
                        weightMatrix[ingredient - 1][stepIncrement]);
                    
                } else {
                	
                    weightMatrix[ingredient][stepIncrement] = weightMatrix[ingredient - 1][stepIncrement];
                    
                }
            }
        }
        return weightMatrix[numberOfIngredients][maxMaterialsForBatch];
    }
}
