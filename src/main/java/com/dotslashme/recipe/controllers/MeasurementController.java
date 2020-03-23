package com.dotslashme.recipe.controllers;

import com.dotslashme.recipe.serializations.v1.MeasurementDto;
import com.dotslashme.recipe.services.MeasurementService;
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

import static com.dotslashme.recipe.util.Constants.MEASUREMENT_V1_CONTENT_TYPE;

@RestController
@RequestMapping(path = "/measurement")
public class MeasurementController {

  private final MeasurementService service;

  @Autowired
  public MeasurementController(MeasurementService service) {
    this.service = service;
  }

  @PostMapping(
    consumes = {MEASUREMENT_V1_CONTENT_TYPE},
    produces = {MEASUREMENT_V1_CONTENT_TYPE}
  )
  public ResponseEntity<Object> createMeasurement(@RequestBody MeasurementDto measurement) {
    return ResponseEntity.created(URI.create(this.service.createMeasurement(measurement))).build();
  }

  @GetMapping(
    produces = {MEASUREMENT_V1_CONTENT_TYPE}
  )
  public ResponseEntity<List<MeasurementDto>> getAllMeasurements() {
    return ResponseEntity.ok(this.service.getMeasurements());
  }

  @GetMapping(
    path = "/{identifier}",
    produces = {MEASUREMENT_V1_CONTENT_TYPE})
  public ResponseEntity<MeasurementDto> getMeasurement(@PathVariable UUID identifier) {
    return ResponseEntity.ok(this.service.getMeasurement(identifier));
  }

  @DeleteMapping(path = "/{identifier}")
  public ResponseEntity<Object> deleteMeasurement(@PathVariable UUID identifier) {
    this.service.deleteMeasurement(identifier);
    return ResponseEntity.ok().build();
  }
}
