package edu.sergradcapstone.groupseven.brewday.controller;


import edu.sergradcapstone.groupseven.brewday.model.Recipe;
import edu.sergradcapstone.groupseven.brewday.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;

@RestController
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;


    @RequestMapping("/recipe/save")
    public String save(){
        //TODO: Replace with form data recieved from UI
        Recipe recipe = new Recipe(10,4.5, "Simple Brew \n Just Drink em", 5.669940, Time.valueOf("00:30:00"), "All-Grain");
        recipeRepository.save(recipe);
        return "Success";
    }

    //TODO: Implement remaining CRUD operations
}
