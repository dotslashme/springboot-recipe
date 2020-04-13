package com.dotslashme.recipe.controllers;

import com.dotslashme.recipe.serializations.v1.RecipeDto;
import com.dotslashme.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static com.dotslashme.recipe.util.Constants.RECIPE_V1_CONTENT_TYPE;

@RestController
@RequestMapping(path = "/recipe")
public class RecipeController {

  private final RecipeService service;

  @Autowired
  public RecipeController(RecipeService service) {
    this.service = service;
  }

  @PostMapping(
    consumes = {RECIPE_V1_CONTENT_TYPE},
    produces = {RECIPE_V1_CONTENT_TYPE}
  )
  public ResponseEntity<Object> createRecipe(@RequestBody RecipeDto recipe) {
    return ResponseEntity.created(URI.create(this.service.createRecipe(recipe))).build();
  }

  @GetMapping(
    produces = {RECIPE_V1_CONTENT_TYPE}
  )
  public ResponseEntity<List<RecipeDto>> readRecipes() {
    return ResponseEntity.ok(this.service.readRecipes());
  }

  @GetMapping(
    path = "/{identifier}",
    produces = {RECIPE_V1_CONTENT_TYPE})
  public ResponseEntity<RecipeDto> readRecipe(@PathVariable UUID identifier) {
    return ResponseEntity.ok(this.service.readRecipe(identifier));
  }

  @DeleteMapping(path = "/{identifier}")
  public ResponseEntity<Object> deleteRecipe(@PathVariable("identifier") UUID identifier) {
    this.service.deleteRecipe(identifier);
    return ResponseEntity.ok().build();
  }
}
