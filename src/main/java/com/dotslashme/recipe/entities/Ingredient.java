package com.dotslashme.recipe.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Ingredient implements Serializable {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Column(length = 200, nullable = false, unique = true)
  private String name;

  @Column(name = "kcal_per_100_grams")
  private Integer kcalPer100Grams;

  @Column(name = "carbohydrates_per_100_grams", precision = 8, scale = 2)
  private Float carbohydratesPer100Grams;

  @Column(name = "fats_per_100_grams", precision = 8, scale = 2)
  private Float fatsPer100Grams;

  @Column(name = "proteins_per_100_grams", precision = 8, scale = 2)
  private Float proteinsPer100Grams;

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

  public Integer getKcalPer100Grams() {
    return kcalPer100Grams;
  }

  public void setKcalPer100Grams(Integer kcalPer100Grams) {
    this.kcalPer100Grams = kcalPer100Grams;
  }

  public Float getCarbohydratesPer100Grams() {
    return carbohydratesPer100Grams;
  }

  public void setCarbohydratesPer100Grams(Float carbohydratesPer100Grams) {
    this.carbohydratesPer100Grams = carbohydratesPer100Grams;
  }

  public Float getFatsPer100Grams() {
    return fatsPer100Grams;
  }

  public void setFatsPer100Grams(Float fatsPer100Grams) {
    this.fatsPer100Grams = fatsPer100Grams;
  }

  public Float getProteinsPer100Grams() {
    return proteinsPer100Grams;
  }

  public void setProteinsPer100Grams(Float proteinsPer100Grams) {
    this.proteinsPer100Grams = proteinsPer100Grams;
  }
}
