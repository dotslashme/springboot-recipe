package com.dotslashme.recipe.test;

import com.dotslashme.recipe.entities.Ingredient;
import com.dotslashme.recipe.entities.Measurement;
import com.dotslashme.recipe.entities.RecipeIngredient;
import com.dotslashme.recipe.repositories.RecipeIngredientRepository;
import com.dotslashme.recipe.services.RecipeIngredientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeIngredientServiceTests {

  @Test
  public void recipeIngredientSanityTest(@Autowired MockMvc mockMvc, @Autowired RecipeIngredientService service) throws Exception {

    Measurement grams = new Measurement().withName("g");
    Measurement tbsp = new Measurement().withName("tbsp");
    Measurement dl = new Measurement().withName("dl");
    Measurement pcs = new Measurement().withName("pcs");
    Measurement krm = new Measurement().withName("krm");

    Ingredient chicken = new Ingredient().withName("Chicken");
    Ingredient redCapsicum = new Ingredient().withName("Red capsicum");
    Ingredient yellowOnion = new Ingredient().withName("Yellow onion");
    Ingredient butter = new Ingredient().withName("Butter");
    Ingredient curryPowder = new Ingredient().withName("Curry powder");
    Ingredient pepperPowder = new Ingredient().withName("Pepper powder");
    Ingredient whippingCream = new Ingredient().withName("Whipping cream");
    Ingredient cremeFraiche = new Ingredient().withName("Creme fraiche");
    Ingredient orangeJuice = new Ingredient().withName("Orange juice");
    Ingredient soy = new Ingredient().withName("Soy");
    Ingredient blackPepper = new Ingredient().withName("Black pepper");

    List<RecipeIngredient> insert = Arrays.asList(
      new RecipeIngredient().withMeasurement(grams).withIngredient(chicken).withAmount(600F),
      new RecipeIngredient().withMeasurement(pcs).withIngredient(redCapsicum).withAmount(2F),
      new RecipeIngredient().withMeasurement(pcs).withIngredient(yellowOnion).withAmount(2F),
      new RecipeIngredient().withMeasurement(tbsp).withIngredient(butter).withAmount(2F),
      new RecipeIngredient().withMeasurement(tbsp).withIngredient(curryPowder).withAmount(3F),
      new RecipeIngredient().withMeasurement(tbsp).withIngredient(pepperPowder).withAmount(1F),
      new RecipeIngredient().withMeasurement(dl).withIngredient(whippingCream).withAmount(5F),
      new RecipeIngredient().withMeasurement(dl).withIngredient(cremeFraiche).withAmount(2F),
      new RecipeIngredient().withMeasurement(tbsp).withIngredient(orangeJuice).withAmount(2F),
      new RecipeIngredient().withMeasurement(tbsp).withIngredient(soy).withAmount(2F),
      new RecipeIngredient().withMeasurement(krm).withIngredient(blackPepper).withAmount(2F)
    );

    List<RecipeIngredient> data = service.createRecipeIngredients(insert);
    assertThat(data).extracting(RecipeIngredient::getIngredient).isNotNull();
    assertThat(data).extracting(RecipeIngredient::getMeasurement).isNotNull();
    assertThat(data).extracting(RecipeIngredient::getAmount).isNotNull();

    service.deleteRecipeIngredients(data);
    List<RecipeIngredient> newData = service.readRecipeIngredients();
    assertThat(newData).isEmpty();
  }
}
