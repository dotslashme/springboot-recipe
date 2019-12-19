package com.dotslashme.recipe.test.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class File {

  public static String getResourceAsString(String fileName) throws IOException {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    try (InputStream is = classLoader.getResourceAsStream(fileName)) {
      if (is == null) return null;

      try (InputStreamReader streamReader = new InputStreamReader(is);
           BufferedReader bufferedReader = new BufferedReader(streamReader)) {
        return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
      }
    }
  }
}
