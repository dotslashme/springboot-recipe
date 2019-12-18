package com.dotslashme.recipe.serializations;

import java.io.Serializable;
import java.util.UUID;

public class MeasurementDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID id;

  private String name;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
