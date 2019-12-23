package com.dotslashme.recipe.services;

import com.dotslashme.recipe.entities.Measurement;
import com.dotslashme.recipe.repositories.MeasurementRepository;
import com.dotslashme.recipe.serializations.MeasurementDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MeasurementService {

  private final MeasurementRepository repository;
  private final ModelMapper modelMapper;
  private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Autowired
  public MeasurementService(MeasurementRepository repository, ModelMapper modelMapper) {
    this.repository = repository;
    this.modelMapper = modelMapper;
  }

  public String createMeasurement(MeasurementDto measurement) {
    Measurement m = this.saveMeasurement(this.modelMapper.map(measurement, Measurement.class));
    return String.format("/measurement/%s", m.getId());
  }

  Measurement saveMeasurement(Measurement measurement) {
    return this.repository.findByName(measurement.getName()).orElseGet(() -> this.repository.save(measurement));
  }

  public List<MeasurementDto> getMeasurements() {
    return this.repository.findAll()
      .stream()
      .map(measurement -> this.modelMapper.map(measurement, MeasurementDto.class))
      .collect(Collectors.toList());
  }

  public MeasurementDto getMeasurement(UUID identifier) {
    Measurement m = this.repository.getOne(identifier);
    this.logger.info("Got entity: {}", m);
    return this.modelMapper.map(m, MeasurementDto.class);
  }

  public void deleteMeasurement(UUID identifier) {
    this.repository.deleteById(identifier);
  }
}
