package com.dotslashme.recipe.controllers;

import com.dotslashme.recipe.serializations.IngredientDto;
import com.dotslashme.recipe.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/ingredient")
public class IngredientController {

  private final IngredientService service;

  @Autowired
  public IngredientController(IngredientService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Object> createIngredient(@RequestBody IngredientDto ingredient) {
    return ResponseEntity.created(URI.create(this.service.createIngredient(ingredient))).build();
  }

  @GetMapping
  public ResponseEntity<List<IngredientDto>> readIngredients() {
    return ResponseEntity.ok(this.service.readIngredients());
  }

  @GetMapping(path = "/{identifier}")
  public ResponseEntity<IngredientDto> readIngredient(@PathVariable UUID identifier) {
    return ResponseEntity.ok(this.service.readIngredient(identifier));
  }

  @DeleteMapping(path = "/{identifier}")
  public ResponseEntity<Object> deleteIngredient(@PathVariable UUID identifier) {
    this.service.deleteIngredient(identifier);
    return ResponseEntity.ok().build();
  }
}
