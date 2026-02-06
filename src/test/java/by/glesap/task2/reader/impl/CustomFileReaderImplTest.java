package by.glesap.task2.reader.impl;


import by.glesap.task2.reader.CustomFileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomFileReaderImplTest {

  private static final String FILENAME = "data/input2.txt";
  private static final String TEXT = """
          test test test
          test test
          test""";
  private final CustomFileReader customFileReader = new CustomFileReaderImpl();

  @Test
  void givenFile_whenRead_thenCorrect() throws IOException {
    String received = customFileReader.readFile(FILENAME);
    assertEquals(TEXT, received);
  }


}