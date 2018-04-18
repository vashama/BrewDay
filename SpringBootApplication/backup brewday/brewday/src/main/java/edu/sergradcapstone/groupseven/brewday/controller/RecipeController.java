package edu.sergradcapstone.groupseven.brewday.controller;


import edu.sergradcapstone.groupseven.brewday.model.Recipe;
import edu.sergradcapstone.groupseven.brewday.model.ResourceNotFoundException;
import edu.sergradcapstone.groupseven.brewday.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
// controller 
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;

    @RequestMapping(value="/newrecipe",method=RequestMethod.POST)
    public ResponseEntity save(HttpSession httpSession,@RequestParam("recipename") String recipename, @RequestParam("ibu") Double ibu, @RequestParam("abv") Double abv, @RequestParam("batchSize") Double batchSize, @RequestParam("Description") String description,
                                       @RequestParam("boilTime") Time boilTime, @RequestParam("brewType") String brewType, @RequestParam("optradio") String optradio){
        System.out.println("before getting username");
        String username = (String) httpSession.getAttribute("username");
        System.out.println("inside post method of recipe");
        if(username == null){
            return ResponseEntity.status(401).body("Operation Unauthorized. Please Log In");
        }
        Recipe recipe;
        if(optradio.contentEquals("PUBLIC")) {
            recipe = new Recipe(recipename,batchSize, abv, description, ibu, boilTime, brewType, "PUBLIC");
        }else{
            recipe = new Recipe(recipename,batchSize, abv, description, ibu, boilTime, brewType, username);
        }
        Recipe insertedRecipe = recipeRepository.save(recipe);

        System.out.println("post method of recipe processed");

        return ResponseEntity.ok(201);
        //return new ResponseEntity<>(insertedRecipe, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Recipe> get(HttpSession httpSession){
        List<Recipe> recipes = new ArrayList<>();

        recipeRepository.findByEmail("PUBLIC").iterator().forEachRemaining(x -> recipes.add(x));
        String username = (String) httpSession.getAttribute("username");

        if(username != null){
            recipeRepository.findByEmail(username).iterator().forEachRemaining(x -> recipes.add(x));
        }
        return recipes;
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity findRecipeById(HttpSession httpSession, @PathVariable Long recipeId){

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));
        if(recipe.getEmail().equals("PUBLIC")){
            return ResponseEntity.ok(recipe);
        }
        String username = (String) httpSession.getAttribute("username");
        if(username == null || !username.contentEquals(recipe.getEmail())){
            return ResponseEntity.status(401).body("Operation Unauthorized");
        }
        return ResponseEntity.ok(recipe);
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRecipe(HttpSession httpSession, @PathVariable("id") Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        String username = (String) httpSession.getAttribute("username");

        if (!recipe.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            if(username == null || !username.contentEquals(recipe.get().getEmail())){
                return ResponseEntity.status(401).body("Operation Unauthorized");
            }
            recipeRepository.delete(recipe.get());
            return new ResponseEntity(HttpStatus.GONE);
        }
    }
    //delete the recipe --> User can delete his recipe
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    // fetch the id of the recipe which needs to be deleted and access from backend and delete it
    public ResponseEntity deleteRecipe(HttpSession httpSession, @PathVariable("id") Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        String username = (String) httpSession.getAttribute("username");

        if (!recipe.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            if(username == null || !username.contentEquals(recipe.get().getEmail())){
                return ResponseEntity.status(401).body("Operation Unauthorized");
            }
            recipeRepository.delete(recipe.get());
            return new ResponseEntity(HttpStatus.GONE);
        }
    }

}
