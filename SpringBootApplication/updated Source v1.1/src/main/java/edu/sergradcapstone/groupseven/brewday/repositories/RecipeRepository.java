package edu.sergradcapstone.groupseven.brewday.repositories;

import com.sun.org.apache.regexp.internal.RE;
import edu.sergradcapstone.groupseven.brewday.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
    List<Recipe> findByBrewType(String brewType);
    List<Recipe> findByEmail(String userName);

}
