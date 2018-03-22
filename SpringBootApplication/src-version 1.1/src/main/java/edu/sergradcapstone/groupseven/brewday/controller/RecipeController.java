package edu.sergradcapstone.groupseven.brewday.controller;


import edu.sergradcapstone.groupseven.brewday.model.Recipe;
import edu.sergradcapstone.groupseven.brewday.model.ResourceNotFoundException;
import edu.sergradcapstone.groupseven.brewday.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Recipe> save(@RequestParam("IBU") Double ibu, @RequestParam("ABV") Double abv, @RequestParam("batchSize") Double batchSize, @RequestParam("Description") String description,
                               @RequestParam("boilTime") Time boilTime, @RequestParam("brewType") String brewType){

        Recipe recipe = new Recipe(batchSize,abv, description, ibu, boilTime, brewType);
        Recipe insertedRecipe = recipeRepository.save(recipe);

        return new ResponseEntity<>(insertedRecipe, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Recipe> get(){
        List<Recipe> recipes = new ArrayList<>();

        recipeRepository.findAll().iterator().forEachRemaining(x -> recipes.add(x));

        return recipes;
    }

    @GetMapping("/{recipeId}")
    public Recipe findRecipeById(@PathVariable Long recipeId){
        return recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));
    }

    @PutMapping
    public ResponseEntity<Recipe> save(@Valid @RequestBody Recipe recipe){

        Recipe targetRecipe = recipeRepository.findById(recipe.getId()).orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipe.getId()));
        targetRecipe.setABV(recipe.getABV());
        targetRecipe.setBatchSize(recipe.getBatchSize());
        //TODO: add remaining fields
        Recipe insertedRecipe = recipeRepository.save(recipe);

        return new ResponseEntity<>(insertedRecipe, HttpStatus.CREATED);
    }


}
