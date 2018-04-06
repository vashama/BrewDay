package brewDay;

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
@RequestMapping("/Ingredients")
public class IngredientsController {
    @Autowired
    IngredientsRepository ingredientsRepository;

    @RequestMapping(method=RequestMethod.POST)
     public ResponseEntity<Recipe> save(@RequestParam("GrainMalts") Double grainMalts, @RequestParam("Hops") Double hops, @RequestParam("Sugars") Double sugars, @RequestParam("Yeast") Double yeast,
                               @RequestParam("Additives") Double additives){

        Ingredients ingredients = new Ingredients(grainMalts,hops, sugars, yeast, additives);
        Ingredients insertedIngredients = IngredientsRespository.save(ingredients);

        return new ResponseEntity<>(insertedIngredients, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Ingredients> get(){
        List<Ingredients> ingredients = new ArrayList<>();

        ingredientsRepository.findAll().iterator().forEachRemaining(x -> ingredients.add(x));

        return ingredients;
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
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRecipe(@PathVariable("id") Long id) {
        Recipe recipe = findRecipeById(id);
        if (recipe == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            recipeRepository.delete(recipe);
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }


}
