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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MeasurementControllerTests {

  @Test
  public void measurementControllerSanityTest(@Autowired MockMvc mockMvc) throws Exception {
    String inputData = Objects.requireNonNull(File.getResourceAsString("measurement-dl.json"));

    String targetUri = mockMvc.perform(post("/measurement")
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .content(inputData))
      .andExpect(status().isCreated())
      .andExpect(header().exists("Location"))
      .andDo(document("create-measurement"))
      .andReturn()
      .getResponse()
      .getHeader("Location");

    String result = mockMvc.perform(get(Objects.requireNonNull(targetUri)))
      .andExpect(status().isOk())
      .andDo(document("read-measurement"))
      .andReturn()
      .getResponse()
      .getContentAsString();

    assertThat(result).as("Created measurement").contains("\"name\":\"dl\"");

    mockMvc.perform(delete(targetUri))
      .andExpect(status().isOk())
      .andDo(document("delete-measurement"));

    String measurements = mockMvc.perform(get("/measurement"))
      .andExpect(status().isOk())
      .andDo(document("read-measurements"))
      .andReturn()
      .getResponse()
      .getContentAsString();

    assertThat(measurements).as("Returned data").doesNotContain("\"name\":\"dl\"");
  }
}
