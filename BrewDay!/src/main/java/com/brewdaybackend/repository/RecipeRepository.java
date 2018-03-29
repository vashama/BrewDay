package com.brewdaybackend.repository;

import com.brewdaybackend.model.*;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
    List<Recipe> findByBrewType(String brewType);

}