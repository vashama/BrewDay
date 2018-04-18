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
@RequestMapping("/ingredients")
public class IngredientsController {
    @Autowired
    RecipeRepository recipeRepository;
  //POST METHOD
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

   
}
