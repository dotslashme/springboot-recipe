package com.dotslashme.recipe.test;

import com.dotslashme.recipe.serializations.v1.IngredientDto;
import com.dotslashme.recipe.test.utils.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;
import java.util.UUID;

import static com.dotslashme.recipe.util.Constants.INGREDIENT_V1_CONTENT_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IngredientControllerTests {

  @Test
  public void ingredientControllerSanityTest(@Autowired MockMvc mockMvc) throws Exception {
    String inputData = Objects.requireNonNull(File.getResourceAsString("ingredient-potato.json"));

    String targetUri = mockMvc.perform(post("/ingredient")
      .contentType(INGREDIENT_V1_CONTENT_TYPE)
      .content(inputData))
      .andExpect(status().isCreated())
      .andExpect(header().exists("Location"))
      .andDo(document("create-ingredient"))
      .andReturn()
      .getResponse()
      .getHeader("Location");

    String result = mockMvc.perform(get(Objects.requireNonNull(targetUri)))
      .andExpect(status().isOk())
      .andDo(document("read-ingredient"))
      .andReturn()
      .getResponse()
      .getContentAsString();

    assertThat(result).as("Created ingredient").contains("\"name\":\"Potato\"");

    mockMvc.perform(delete(targetUri))
      .andExpect(status().isOk())
      .andDo(document("delete-ingredient"));

    String measurements = mockMvc.perform(get("/ingredient"))
      .andExpect(status().isOk())
      .andDo(document("read-ingredients"))
      .andReturn()
      .getResponse()
      .getContentAsString();

    assertThat(measurements).as("Returned data").doesNotContain("\"name\":\"Potato\"");

  }

  @Test
  public void deleteNonExistentIngredient(@Autowired MockMvc mockMvc) {
    final UUID usedId = UUID.randomUUID();
    final String expectedMessage = String.format("No class com.dotslashme.recipe.entities.Ingredient entity with id %s exists!", usedId);

    assertThatThrownBy(() -> mockMvc.perform(delete(String.format("/ingredient/%s", usedId)))).hasMessageEndingWith(expectedMessage);
  }

  @Test
  public void updatePotato(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) throws Exception {
    String inputData = Objects.requireNonNull(File.getResourceAsString("ingredient-potato.json"));

    String targetUri = mockMvc.perform(post("/ingredient")
      .contentType(INGREDIENT_V1_CONTENT_TYPE)
      .content(inputData))
      .andExpect(status().isCreated())
      .andExpect(header().exists("Location"))
      .andReturn()
      .getResponse()
      .getHeader("Location");

    IngredientDto potato = objectMapper.readValue(mockMvc.perform(get(Objects.requireNonNull(targetUri)))
      .andExpect(status().isOk())
      .andReturn()
      .getResponse()
      .getContentAsString(), IngredientDto.class);

    assertThat(potato.getCarbohydratesPer100Grams()).as("Carbohydrates per 100 grams").isEqualTo(17.0F);
    assertThat(potato.getFatsPer100Grams()).as("Fats per 100 grams").isEqualTo(0.1F);
    assertThat(potato.getKcalPer100Grams()).as("KCal per 100 grams").isEqualTo(77);
    assertThat(potato.getProteinsPer100Grams()).as("Proteins per 100 grams").isEqualTo(2.0F);

    potato.setCarbohydratesPer100Grams(18.0F);
    potato.setFatsPer100Grams(0.2F);
    potato.setKcalPer100Grams(78);
    potato.setProteinsPer100Grams(3.0F);

    IngredientDto updatedPotato = objectMapper.readValue(mockMvc.perform(patch("/ingredient")
      .contentType(INGREDIENT_V1_CONTENT_TYPE)
      .content(objectMapper.writeValueAsString(potato)))
      .andDo(document("patch-ingredient"))
      .andExpect(status().isOk())
      .andReturn()
      .getResponse()
      .getContentAsString(), IngredientDto.class);

    assertThat(updatedPotato.getCarbohydratesPer100Grams()).as("Carbohydrates per 100 grams").isEqualTo(18.0F);
    assertThat(updatedPotato.getFatsPer100Grams()).as("Fats per 100 grams").isEqualTo(0.2F);
    assertThat(updatedPotato.getKcalPer100Grams()).as("KCal per 100 grams").isEqualTo(78);
    assertThat(updatedPotato.getProteinsPer100Grams()).as("Proteins per 100 grams").isEqualTo(3.0F);

  }
}
