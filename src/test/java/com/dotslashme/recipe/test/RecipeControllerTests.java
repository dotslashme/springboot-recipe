package com.dotslashme.recipe.test;

import com.dotslashme.recipe.test.utils.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeControllerTests {

  @Test
  public void recipeControllerSanityTest(@Autowired MockMvc mockMvc) throws Exception {
    String inputData = Objects.requireNonNull(File.getResourceAsString("recipe-chicken-curry-stew.json"));

    String targetUri = mockMvc.perform(post("/recipe")
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .content(inputData))
      .andExpect(status().isCreated())
      .andExpect(header().exists("Location"))
      .andDo(document("create-recipe"))
      .andReturn()
      .getResponse()
      .getHeader("Location");

    String result = mockMvc.perform(get(Objects.requireNonNull(targetUri)))
      .andExpect(status().isOk())
      .andDo(document("read-recipe"))
      .andReturn()
      .getResponse()
      .getContentAsString();

    assertThat(result).as("Created recipe").contains("\"name\":\"Chicken curry stew\"");

    mockMvc.perform(delete(targetUri))
      .andExpect(status().isOk())
      .andDo(document("delete-recipe"));

    String measurements = mockMvc.perform(get("/recipe"))
      .andExpect(status().isOk())
      .andDo(document("read-recipes"))
      .andReturn()
      .getResponse()
      .getContentAsString();

    assertThat(measurements).as("Returned data").doesNotContain("\"name\":\"Chicken curry stew\"");
  }
}
