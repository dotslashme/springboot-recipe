package com.dotslashme.recipe.controllers;

import com.dotslashme.recipe.serializations.RecipeDto;
import com.dotslashme.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
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
  public ResponseEntity<Object> createRecipe(@RequestBody RecipeDto recipe) {
    return ResponseEntity.created(URI.create(this.service.createRecipe(recipe))).build();
  }

  @GetMapping
  public ResponseEntity<List<RecipeDto>> readRecipes() {
    return ResponseEntity.ok(this.service.readRecipes());
  }

  @GetMapping(path = "/{identifier}")
  public ResponseEntity<RecipeDto> readRecipe(@PathVariable UUID identifier) {
    return ResponseEntity.ok(this.service.readRecipe(identifier));
  }

  @DeleteMapping(path = "/{identifier}")
  public ResponseEntity<Object> deleteRecipe(@PathVariable("identifier") UUID identifier) {
    this.service.deleteRecipe(identifier);
    return ResponseEntity.ok().build();
  }
}
