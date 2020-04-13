package com.dotslashme.recipe.controllers;

import com.dotslashme.recipe.serializations.v1.IngredientDto;
import com.dotslashme.recipe.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static com.dotslashme.recipe.util.Constants.INGREDIENT_V1_CONTENT_TYPE;

@RestController
@RequestMapping(path = "/ingredient")
public class IngredientController {

  private final IngredientService service;

  @Autowired
  public IngredientController(IngredientService service) {
    this.service = service;
  }

  @PostMapping(
    consumes = {INGREDIENT_V1_CONTENT_TYPE},
    produces = {INGREDIENT_V1_CONTENT_TYPE}
  )
  public ResponseEntity<Object> createIngredient(@RequestBody IngredientDto ingredient) {
    return ResponseEntity.created(URI.create(this.service.createIngredient(ingredient))).build();
  }

  @GetMapping(
    produces = {INGREDIENT_V1_CONTENT_TYPE}
  )
  public ResponseEntity<List<IngredientDto>> readIngredients() {
    return ResponseEntity.ok(this.service.readIngredients());
  }

  @PatchMapping(
    consumes = {INGREDIENT_V1_CONTENT_TYPE},
    produces = {INGREDIENT_V1_CONTENT_TYPE}
  )
  public ResponseEntity<IngredientDto> updateIngredient(@RequestBody IngredientDto ingredient) {
    return ResponseEntity.ok(this.service.updateIngredient(ingredient));
  }

  @GetMapping(
    path = "/{identifier}",
    produces = {INGREDIENT_V1_CONTENT_TYPE})
  public ResponseEntity<IngredientDto> readIngredient(@PathVariable UUID identifier) {
    return ResponseEntity.ok(this.service.readIngredient(identifier));
  }

  @DeleteMapping(path = "/{identifier}")
  public ResponseEntity<Object> deleteIngredient(@PathVariable UUID identifier) {
    this.service.deleteIngredient(identifier);
    return ResponseEntity.ok().build();
  }
}
