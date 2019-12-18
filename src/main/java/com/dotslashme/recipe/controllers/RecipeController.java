package com.dotslashme.recipe.controllers;

import com.dotslashme.recipe.serializations.RecipeDto;
import com.dotslashme.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/recipe")
public class RecipeController {

  private final RecipeService service;

  @Autowired
  public RecipeController(RecipeService service) {
    this.service = service;
  }

  @PostMapping
  public void createRecipe(@RequestBody RecipeDto recipe) {
    this.service.createRecipe(recipe);
  }

  @GetMapping
  public List<RecipeDto> readAllRecipes() {
    return this.service.readAllRecipes();
  }

  @GetMapping(path = "/{identifier}")
  public RecipeDto readRecipe(@PathVariable("identifier") UUID identifier) {
    return this.service.readRecipe(identifier);
  }

  @PatchMapping(path = "/{identifier}")
  public void updateRecipe(@PathVariable("identifier") UUID identifier, @RequestBody RecipeDto recipe) {
    this.service.updateRecipe(identifier, recipe);
  }

  @DeleteMapping(path = "/{identifier}")
  public void deleteRecipe(@PathVariable("identifier") UUID identifier) {
    this.service.deleteRecipe(identifier);
  }
}
