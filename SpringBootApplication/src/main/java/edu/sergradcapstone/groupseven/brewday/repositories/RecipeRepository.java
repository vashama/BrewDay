package edu.sergradcapstone.groupseven.brewday.repositories;

import edu.sergradcapstone.groupseven.brewday.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
    List<Recipe> findByBrewType(String brewType);

}
